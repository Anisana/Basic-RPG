package core;

import gui.CharacterSelectionGUI;
import gui.StartScreenGUI;

import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

import character.Character;

public class StartUp {
	
	private Controller controller;
	
	//TODO load initial assets here to pass to controller?
	private ArrayList<Character> characters = new ArrayList<Character>();
	
	private StartScreenGUI ssGUI;
	
	private JButton currHoveredButton;

	public static void main(String[] args) {
		ToolTipManager.sharedInstance().setInitialDelay(3);
		new StartUp();
	}
	
	public StartUp() {
		
		ssGUI = new StartScreenGUI(new StartScreenButtonListener(), new StartScreenMouseListener(), new StartScreenKeyListener());
		ssGUI.setVisible(true);
		
		loadAssets();
		
		
	}
	
	private void loadAssets(){
		Thread newThread = new Thread(new Runnable(){
			@Override
			public void run(){
				System.out.println("Loading assets...");
				try{
					File dir = new File("characters");
					
					CharXMLReader parser = new CharXMLReader();
					
					File[] chars = dir.listFiles();
					for(File file : chars){
						if(file.getName().endsWith(".xml")){
							Character temp = parser.parse(file.getAbsolutePath());
							characters.add(temp);
						}
					}
					
					//progress bar?
					//Thread.sleep(2000);
					
					System.out.println("Loading finished...\n\t...Initialising the console and controller");
					controller = new Controller(characters);
					ssGUI.showStartBtn();
					new Console(characters);
					
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
	//NB: ToolTip timer changed in StartScreenGUI.java at line 67
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
