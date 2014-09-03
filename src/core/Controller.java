package core;

import gui.CharacterCreationGUI;
import gui.CharacterSelectionGUI;
import gui.CharacterSheetGUI;
import gui.CharacterVitalStatisticsPanel;
import gui.CombatGUI;
import gui.MapScreenGUI;
import item.Item;
import item.ItemGenerator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import path.Skill;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import unit.Unit;
import unit.character.Character;
import unit.monster.Monster;
import world.CombatSquare;
import world.Encounter;



public class Controller {

	//CharXMLReader charParser;
	//GUI instances
	private CharacterSelectionGUI csGUI;
	private CharacterCreationGUI ccGUI;
	private MapScreenGUI msGUI;
	private CombatGUI cGUI;
	
	//Logic instances
	private CharacterCreationLogic ccLogic;
	private CombatLogic combatLogic;
	
	//extra needed variables
	private DataStore data;
	private Character currChar;
	private JToggleButton previousCombatActionButton;
	
	
	public Controller(DataStore data) {
		this.data = data;
	}
	
	public void begin(){
		csGUI = new CharacterSelectionGUI(data.getCharacters(), new CharSelectListListener(), new CharSelectPlayListener(), new CharSelectDeleteListener(), new CharSelectCreateListener());
		csGUI.setVisible(true);
	}
	
	private void showMapScreen(){
		msGUI = new MapScreenGUI(currChar, new MapCharSheetListener(), new MapTestCombatListener());
		msGUI.setVisible(true);
	}
	
	public void saveCharacter(Character character){
		CharXMLWriter writer = new CharXMLWriter(character);
		if(writer.outputCharacter()){
			System.out.println("'" + character.getName() + "' saved");
		}
		else{
			System.out.println("Error '" + character.getName() + "' not saved");
		}
		
		ExclusionStrategy strat = new CharacterExclusionStrategy(CharacterVitalStatisticsPanel.class);
		Gson gsonWriter = new GsonBuilder()
			.serializeNulls()
			.setExclusionStrategies(strat)
			.registerTypeAdapter(Item.class, new ItemAdapter())
			.registerTypeAdapter(Unit.class, new UnitAdapter())
			.setPrettyPrinting()
			.create();
		
		
		String json = gsonWriter.toJson(character);
		//System.out.println(json);
		try{
			FileWriter fWriter = new FileWriter("characters" + File.separator + character.getName()+".json");
			fWriter.write(json);
			fWriter.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

//character selection screen	
	class CharSelectListListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton temp = (JButton) arg0.getSource();
			int index = (int) temp.getClientProperty("index");
			currChar = data.getCharacters().get(index);
			csGUI.updateCharInfo(currChar);
			csGUI.showCharOptionButtons();
		}
	}
	
	class CharSelectPlayListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			showMapScreen();
			csGUI.dispose();
		}
	}
	
	class CharSelectDeleteListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(JOptionPane.showConfirmDialog(csGUI, "Are you sure you want to delete " + currChar.getName() + "?", "Delete Character", JOptionPane.YES_NO_OPTION) == 0){
				try {
					File file = new File("characters" + File.separator + currChar.getName()+".xml");
					Files.delete(file.toPath());
					data.getCharacters().remove(currChar);
					csGUI.dispose();
					begin();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class CharSelectCreateListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ccLogic = new CharacterCreationLogic();
			ccGUI = new CharacterCreationGUI(data.getPaths(), new CharCreateStatIncrementListener(), new CharCreateStatDecrementListener(), new CharCreateCreateListener()); 
			ccGUI.setVisible(true);
		}
	}
	
//character creation	
	class CharCreateStatIncrementListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton temp = (JButton) arg0.getSource();
			String stat = (String) temp.getClientProperty("stat");
			
			if(!ccLogic.incrementStat(stat)){
				ccGUI.disableIncrementButtons();
			}
			ccGUI.enableDecrementButton(stat);
			ccGUI.setRemainingPoints(ccLogic.getRemainingPoints());
			ccGUI.setStatTotal(stat, ccLogic.getStat(stat));
		}
	}
	
	class CharCreateStatDecrementListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton temp = (JButton) arg0.getSource();
			String stat = (String) temp.getClientProperty("stat");
			
			if(!ccLogic.decrementStat(stat)){
				ccGUI.disableDecrementButton(stat);
			}
			ccGUI.enableIncrementButtons();
			ccGUI.setRemainingPoints(ccLogic.getRemainingPoints());
			ccGUI.setStatTotal(stat, ccLogic.getStat(stat));
		}
	}
	
	class CharCreateCreateListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton temp = (JButton) arg0.getSource();
			String goal = (String) temp.getClientProperty("goal");
			
			ccLogic.setName(ccGUI.getName());
			ccLogic.setPath(ccGUI.getPath());
			
			if(ccLogic.isReady()){
				currChar = ccLogic.getCharacter();
				//save character to xml file
				saveCharacter(currChar);
				
				if(goal.equals("create")){
					data.getCharacters().add(currChar);
					csGUI.dispose();
					ccGUI.dispose();
					begin();
				}
				if(goal.equals("play")){
					
					showMapScreen();
					csGUI.dispose();
					ccGUI.dispose();
					
				}
			}
			else{
				JOptionPane.showMessageDialog(ccGUI, "Error: Incomplete form", "Incomplete form", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	

//Map screen listeners
	class MapCharSheetListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			CharacterSheetGUI charSheet  = new CharacterSheetGUI(currChar);
			charSheet.setVisible(true);
		}
	}
	
	class MapTestCombatListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			/*System.out.println(mob);
			System.out.println(temp+"\n");
			System.out.println("current char = \n" + currChar);*/
			
			
			
			Encounter tempEncounter = new Encounter(currChar);
			
			combatLogic = new CombatLogic(currChar, tempEncounter);
			
			cGUI = new CombatGUI(currChar, tempEncounter, (Monster) tempEncounter.getMobs().get(0), 
					new CombatCharActionListener(), new CombatGridMouseListener(), new CombatPassTurnListener());
			cGUI.setVisible(true);
			msGUI.setVisible(false);
		}
	}
	
	
//Combat screen listeners
	private void combatFinished(String message){
		JOptionPane.showMessageDialog(cGUI, message);
		//find a better way to do this
		if(message.equals("Victory!")){
			if(currChar.giveExperience(combatLogic.getCombatExp())){
				System.out.println("\tready to level up");
				//do level up things
				//grab new stat block
				//update modified stats
				currChar.updateModifiedStats();
			}
			//System.out.println(currChar.getName() + "'s new exp = " + currChar.getBaseStats().getExperience());
		}
		
		saveCharacter(currChar);
		
		cGUI.dispose();
		msGUI.updateCharSheet();
		msGUI.setVisible(true);
	}
	
	
	class CombatCharActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JToggleButton temp = (JToggleButton) arg0.getSource();
			
			if(temp == previousCombatActionButton){
				System.out.println("jtb already selected");
				cGUI.resetActionButtons();
				combatLogic.deselectAction();
				previousCombatActionButton = null;
				return;
			}
			
			previousCombatActionButton = temp;
			
			if(!combatLogic.setCurrentAction((int) temp.getClientProperty("index"))){
				cGUI.resetActionButtons();
				previousCombatActionButton = null;
			}
			
			
			//technically should be in a grid listener
			/*combatLogic.attack();
			
			try {
				cGUI.resetActionButtons();
				previous = new JToggleButton();
				combatLogic.passTurn();
			} catch (CombatFinishedEvent e) {
				combatFinished(e.getMessage());
			}*/
			
		}
	}
	
	class CombatGridMouseListener implements MouseListener{
		
		CombatSquare currSquare;

		@Override
		public void mouseClicked(MouseEvent e) {
			currSquare = (CombatSquare) e.getSource();
			try {
				combatLogic.doAction(currSquare.getRow(), currSquare.getCol());
				cGUI.resetActionButtons();
				previousCombatActionButton = null;
			} catch (CombatFinishedEvent cfe) {
				combatFinished(cfe.getMessage());
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			currSquare = (CombatSquare) e.getSource();
			combatLogic.highlightMovementPath(currSquare.getRow(), currSquare.getCol());
			combatLogic.highlightAttackArea(currSquare.getRow(), currSquare.getCol());
		}

		@Override
		public void mouseExited(MouseEvent e) {
			combatLogic.resetHighlighting();
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}
	
	class CombatPassTurnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				cGUI.resetActionButtons();
				previousCombatActionButton = null;
				combatLogic.passTurn();
			} catch (CombatFinishedEvent cfe) {
				combatFinished(cfe.getMessage());
			}
		}
	}

}
