package core;

import gui.CharacterCreationGUI;
import gui.CharacterSelectionGUI;
import gui.CharacterSheetGUI;
import gui.MapScreenGUI;
import gui.StartScreenGUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import character.Character;

public class Controller {

	//CharXMLReader charParser;
	//GUI instances
	CharacterSelectionGUI csGUI;
	CharacterCreationGUI ccGUI;
	MapScreenGUI msGUI;
	
	//Logic instances
	CharacterCreationLogic ccLogic;
	
	//extra needed variables
	private ArrayList<Character> characters;
	private Character currChar;

	
	
	public Controller(ArrayList<Character> characters) {
		this.characters = characters;
		/*StartScreenGUI ssGUI = new StartScreenGUI(new StartScreenButtonListener());
		ssGUI.setVisible(true);*/
	}
	
	public void begin(){
		csGUI = new CharacterSelectionGUI(characters, new CharSelectListListener(), new CharSelectPlayListener(), new CharSelectDeleteListener(), new CharSelectCreateListener());
		csGUI.setVisible(true);
	}

//character selection screen	
	class CharSelectListListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton temp = (JButton) arg0.getSource();
			int index = (int) temp.getClientProperty("index");
			currChar = characters.get(index);
			csGUI.updateCharInfo(currChar);
			csGUI.showCharOptionButtons();
		}
	}
	
	class CharSelectPlayListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			msGUI = new MapScreenGUI(currChar, new MapCharSheetListener());
			csGUI.dispose();
			msGUI.setVisible(true);
		}
	}
	
	class CharSelectDeleteListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(JOptionPane.showConfirmDialog(csGUI, "Are you sure you want to delete " + currChar.getName() + "?", "Delete Character", JOptionPane.YES_NO_OPTION) == 0){
				try {
					File file = new File("characters" + File.separator + currChar.getName()+".xml");
					Files.delete(file.toPath());
					characters.remove(currChar);
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
			ccGUI = new CharacterCreationGUI(new CharCreateStatIncrementListener(), new CharCreateStatDecrementListener(), new CharCreateCreateListener()); 
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
			ccLogic.setPath(ccGUI.getPath() + "temp");
			
			if(ccLogic.isReady()){
				currChar = ccLogic.getCharacter();
				//save character to xml file
				CharXMLWriter writer = new CharXMLWriter(currChar);
				if(writer.outputCharacter()){
					System.out.println("'" + currChar.getName() + "' saved");
				}
				else{
					System.out.println("Error '" + currChar.getName() + "' not saved");
				}
				
				if(goal.equals("create")){
					characters.add(currChar);
					csGUI.dispose();
					ccGUI.dispose();
					begin();
				}
				if(goal.equals("play")){
					
					msGUI = new MapScreenGUI(currChar, new MapCharSheetListener());
					csGUI.dispose();
					ccGUI.dispose();
					msGUI.setVisible(true);
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

}