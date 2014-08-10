package gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import unit.character.Character;

public class CharacterSelectionGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnPlay;
	private JButton btnDelete;
	private JLabel lblCharInfo;

	/**
	 * Create the frame.
	 */
	public CharacterSelectionGUI(ArrayList<Character> characters, ActionListener charSelectL, ActionListener charPlayL, ActionListener charDelL, ActionListener charCreateL) {
		setTitle("Character Selection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane charPane = new JScrollPane(makeCharList(characters, charSelectL));
		charPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		charPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		charPane.setBounds(497, 1, 185, 400);
		contentPane.add(charPane);
		
		JButton btnCreateNewCharacter = new JButton("Create New Character");
		btnCreateNewCharacter.setBounds(507, 413, 163, 29);
		btnCreateNewCharacter.addActionListener(charCreateL);
		contentPane.add(btnCreateNewCharacter);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(490, 10, 2, 435);
		contentPane.add(separator);
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(143, 413, 97, 29);
		btnPlay.setVisible(false);
		btnPlay.addActionListener(charPlayL);
		contentPane.add(btnPlay);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(charDelL);
		btnDelete.setBounds(252, 413, 97, 29);
		btnDelete.setVisible(false);
		contentPane.add(btnDelete);
		
		lblCharInfo = new JLabel();
		lblCharInfo.setBounds(167, 168, 200, 16);
		contentPane.add(lblCharInfo);
		
		//setResizable(false);
	}
	
	private JPanel makeCharList(ArrayList<Character> chars, ActionListener listener){
		JPanel panel = new JPanel();
		//FlowLayout layout = new FlowLayout();
		//layout.setAlignOnBaseline(true);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//panel.setPreferredSize(new Dimension(165, 400));
		//panel.setBounds(0, 0, 165, 400);
		
		JButton temp;
		int i = 0;
		for(Character c : chars){
			temp = new JButton("<html>" + c.getName() + "<br>Level: " + c.getBaseStats().getLevel() + "</html>");
			temp.putClientProperty("index", i++);
			temp.addActionListener(listener);
			//temp.setText();
			temp.setPreferredSize(new Dimension(150, 40));
			panel.add(temp);
		}
		
		return panel;
	}
	
	public void showCharOptionButtons(){
		btnPlay.setVisible(true);
		btnDelete.setVisible(true);
	}
	
	public void updateCharInfo(Character c){
		lblCharInfo.setText("Current Character: " + c.getName());
	}
}
