package core;

import enums.SkillType;
import gui.CharacterSelectionGUI;
import gui.CharacterVitalStatisticsPanel;
import gui.StartScreenGUI;
import item.Item;

import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

import path.Skill;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import path.Path;
import unit.Unit;
import unit.character.Character;

public class StartUp {

	private Controller controller;

	//TODO load initial assets here to pass to controller?
	//private ArrayList<Character> characters = new ArrayList<Character>();
	private DataStore data;

	private StartScreenGUI ssGUI;

	//for testing only, remove later
	private JButton currHoveredButton;

	public static void main(String[] args) {
		ToolTipManager.sharedInstance().setInitialDelay(3);
		new StartUp();
	}

	public StartUp() {

		ssGUI = new StartScreenGUI(new StartScreenButtonListener(), new StartScreenMouseListener(), new StartScreenKeyListener());
		ssGUI.setVisible(true);
		
		data = new DataStore();

		loadAssets();


	}

	private void loadAssets(){
		Thread newThread = new Thread(new Runnable(){
			@Override
			public void run(){
				System.out.println("Loading assets...");
				try{
					File dir = new File("characters");

					/*CharXMLReader parser = new CharXMLReader();

					File[] chars = dir.listFiles();
					for(File file : chars){
						if(file.getName().endsWith(".xml")){
							Character temp = parser.parse(file.getAbsolutePath());
							data.addCharacter(temp);
						}
					}*/

					ExclusionStrategy strat = new CharacterExclusionStrategy(CharacterVitalStatisticsPanel.class);
					Gson gsonReader = new GsonBuilder()
						.serializeNulls()
						.setExclusionStrategies(strat)
						.registerTypeAdapter(Item.class, new ItemAdapter())
						.registerTypeAdapter(Unit.class, new UnitAdapter())
						.create();

					File[] files = dir.listFiles();
					BufferedReader br;
					for(File file : files){
						if(file.getName().endsWith(".json")){
							br = new BufferedReader(new FileReader(file.getAbsolutePath()));
							Character temp = gsonReader.fromJson(br, Character.class);
							data.addCharacter(temp);
						}
					}

					//progress bar?

					//convenient place to create some assets prior to adding them to the file directly
					/*Path path = new Path("Test Path", 5, "all");
					Skill skill = new Skill();
					Skill skill2 = new Skill();
					skill2.setName("Another basic attack");
					path.addSkill(skill);
					path.addSkill(skill2);

					Gson gsonWriter = new GsonBuilder()
						.serializeNulls()
						.setExclusionStrategies(strat)
						.registerTypeAdapter(Item.class, new ItemAdapter())
						.setPrettyPrinting()
						.create();

					String json = gsonWriter.toJson(path);
					//System.out.println(json);
					try{
						FileWriter fWriter = new FileWriter("paths" + File.separator + path.getName()+".json");
						fWriter.write(json);
						fWriter.close();
					} catch(IOException e){
						e.printStackTrace();
					}*/


					//end temp asset creation

					dir = new File("paths");

					//ExclusionStrategy strat = new CharacterExclusionStrategy(CharacterVitalStatisticsPanel.class);
					gsonReader = new GsonBuilder()
						.serializeNulls()
						.create();

					files = dir.listFiles();
					//BufferedReader br;
					for(File file : files){
						if(file.getName().endsWith(".json")){
							br = new BufferedReader(new FileReader(file.getAbsolutePath()));
							Path temp = gsonReader.fromJson(br, Path.class);
							data.addPath(temp);
						}
					}
					
					
					
					System.out.println("Applying any updates...");
					for(Character c : data.getCharacters()){
						c.updatePath(data);
						c.setActiveSkills();
					}
					
					
					System.out.println("Loading finished...\n\t...Initialising the console and controller");
					controller = new Controller(data);
					ssGUI.showStartBtn();
					new Console(data);

				} catch (Exception e){
					e.printStackTrace();
				}
				System.out.println("\t...finished loading. Force charSelectGUI repaint?");
			}
		});
		newThread.start();
	}

	class StartScreenButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ssGUI.dispose();
			controller.begin();
		}

	}

	//pointless listeners simply for testing purposes
	class StartScreenMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			currHoveredButton = (JButton) arg0.getSource();
			currHoveredButton.requestFocusInWindow();

			if(arg0.isControlDown()){
				currHoveredButton.setToolTipText("ctrl is down");
			}
			else{
				currHoveredButton.setToolTipText("ctrl is not down");
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			currHoveredButton = null;
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			if(arg0.isControlDown()){
				System.out.println("CTRL down, click modifier = " + arg0.getModifiersEx());
			}

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	class StartScreenKeyListener implements KeyListener{
		//ctrl extended key code = 17
		@Override
		public void keyPressed(KeyEvent arg0) {

			int extID = arg0.getExtendedKeyCode();
			if(currHoveredButton == arg0.getSource()){
				if(extID == KeyEvent.VK_CONTROL){
					currHoveredButton.setToolTipText("ctrl is down");
					Point locationOnScreen = MouseInfo.getPointerInfo().getLocation();
					Point locationOnComponent = new Point(locationOnScreen);
					SwingUtilities.convertPointFromScreen(locationOnComponent, currHoveredButton);
					if (currHoveredButton.contains(locationOnComponent)) {
						ToolTipManager.sharedInstance().mouseMoved(new MouseEvent(currHoveredButton, -1, System.currentTimeMillis(), 
								0, locationOnComponent.x, locationOnComponent.y, locationOnScreen.x, locationOnScreen.y, 0, false, 0));
					}
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			int extID = arg0.getExtendedKeyCode();
			if(currHoveredButton == arg0.getSource()){
				if(extID == KeyEvent.VK_CONTROL){
					currHoveredButton.setToolTipText("ctrl is not down");
					Point locationOnScreen = MouseInfo.getPointerInfo().getLocation();
					Point locationOnComponent = new Point(locationOnScreen);
					SwingUtilities.convertPointFromScreen(locationOnComponent, currHoveredButton);
					if (currHoveredButton.contains(locationOnComponent)) {
						ToolTipManager.sharedInstance().mouseMoved(new MouseEvent(currHoveredButton, -1, System.currentTimeMillis(), 
								0, locationOnComponent.x, locationOnComponent.y, locationOnScreen.x, locationOnScreen.y, 0, false, 0));
					}
				}
			}

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

	}

}
