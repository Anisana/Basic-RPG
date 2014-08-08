package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import character.Character;

public class MapScreenGUI extends JFrame {
	
	//pointer to have a reference for showing character stats etc
	private Character currChar;

	private JPanel contentPane;
	private JLayeredPane panelCharSheet;
	private JScrollPane scrollPaneQuests;
	private JLabel lblName;
	private JLabel lblPathLine;
	private JLabel lblCurrentHP;
	private JLabel lblCurrentFocus;
	private JLabel lblCurrentMana;
	private JLabel lblCurrentExp;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapScreenGUI frame = new MapScreenGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public MapScreenGUI(Character currChar, ActionListener charSheetAL) {
		this.currChar = currChar;
		
		
		setTitle("World Map");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane panel = new JLayeredPane();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 982, 655);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMap = new JLabel("Map with buttons");
		lblMap.setBounds(513, 282, 98, 16);
		panel.add(lblMap);
		
		JButton btnTestCombat = new JButton("Test combat");
		btnTestCombat.setBounds(494, 432, 117, 25);
		panel.add(btnTestCombat);
		
		panelCharSheet = new JLayeredPane();
		panelCharSheet.setBounds(0, 25, 193, 185);
		panel.add(panelCharSheet);
		GridBagLayout gbl_panelCharSheet = new GridBagLayout();
		gbl_panelCharSheet.columnWidths = new int[] {192, 0};
		gbl_panelCharSheet.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCharSheet.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelCharSheet.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCharSheet.setVisible(false);
		panelCharSheet.setLayout(gbl_panelCharSheet);
		
		lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		panelCharSheet.add(lblName, gbc_lblName);
		
		lblPathLine = new JLabel("Level Path line");
		GridBagConstraints gbc_lblPathLine = new GridBagConstraints();
		gbc_lblPathLine.anchor = GridBagConstraints.WEST;
		gbc_lblPathLine.insets = new Insets(0, 0, 5, 0);
		gbc_lblPathLine.gridx = 0;
		gbc_lblPathLine.gridy = 1;
		panelCharSheet.add(lblPathLine, gbc_lblPathLine);
		
		lblCurrentHP = new JLabel("current HP");
		GridBagConstraints gbc_lblCurrentHP = new GridBagConstraints();
		gbc_lblCurrentHP.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentHP.gridx = 0;
		gbc_lblCurrentHP.gridy = 2;
		panelCharSheet.add(lblCurrentHP, gbc_lblCurrentHP);
		
		lblCurrentFocus = new JLabel("current Focus");
		GridBagConstraints gbc_lblCurrentFocus = new GridBagConstraints();
		gbc_lblCurrentFocus.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentFocus.gridx = 0;
		gbc_lblCurrentFocus.gridy = 3;
		panelCharSheet.add(lblCurrentFocus, gbc_lblCurrentFocus);
		
		lblCurrentMana = new JLabel("current Mana");
		GridBagConstraints gbc_lblCurrentMana = new GridBagConstraints();
		gbc_lblCurrentMana.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentMana.gridx = 0;
		gbc_lblCurrentMana.gridy = 4;
		panelCharSheet.add(lblCurrentMana, gbc_lblCurrentMana);
		
		lblCurrentExp = new JLabel("current exp");
		GridBagConstraints gbc_lblCurrentExp = new GridBagConstraints();
		gbc_lblCurrentExp.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentExp.gridx = 0;
		gbc_lblCurrentExp.gridy = 5;
		panelCharSheet.add(lblCurrentExp, gbc_lblCurrentExp);
		
		JButton btnShowDetailedSheet = new JButton("Show Detailed Sheet");
		btnShowDetailedSheet.addActionListener(charSheetAL);
		GridBagConstraints gbc_btnShowDetailedSheet = new GridBagConstraints();
		gbc_btnShowDetailedSheet.gridx = 0;
		gbc_btnShowDetailedSheet.gridy = 6;
		panelCharSheet.add(btnShowDetailedSheet, gbc_btnShowDetailedSheet);
		
		scrollPaneQuests = new JScrollPane();
		scrollPaneQuests.setBounds(192, 25, 186, 257);
		scrollPaneQuests.setVisible(false);
		panel.add(scrollPaneQuests);
		
		JPanel panelQuests = new JPanel();
		scrollPaneQuests.setColumnHeaderView(panelQuests);
		panelQuests.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblQuestPanel = new JLabel("Quest Panel");
		panelQuests.add(lblQuestPanel);
		
		JToggleButton tglbtnCharacterSheet = new JToggleButton("Character Sheet");
		tglbtnCharacterSheet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!panelCharSheet.isVisible()){
					updateCharSheet();
				}
				panelCharSheet.setVisible(!panelCharSheet.isVisible());
			}
		});
		tglbtnCharacterSheet.setBounds(0, 0, 193, 25);
		panel.add(tglbtnCharacterSheet);
		
		JToggleButton tglbtnQuestLog = new JToggleButton("Quest Log");
		tglbtnQuestLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scrollPaneQuests.setVisible(!scrollPaneQuests.isVisible());
			}
		});
		tglbtnQuestLog.setBounds(192, 0, 186, 25);
		panel.add(tglbtnQuestLog);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.setBounds(885, 0, 97, 25);
		panel.add(btnSettings);
	}
	
	
	public void updateCharSheet(){
		lblName.setText(currChar.getName());
		lblPathLine.setText("Level " + currChar.getModifiedStats().getLevel() + " blah");
		lblCurrentHP.setText("Current HP: " + currChar.getModifiedStats().getCurrentHealth() + " / "  + currChar.getModifiedStats().getTotalHealth());
		lblCurrentFocus.setText("Current Focus: " + currChar.getModifiedStats().getCurrentFocus() + " / "  + currChar.getModifiedStats().getTotalFocus());
		lblCurrentMana.setText("Current Mana: " + currChar.getModifiedStats().getCurrentMana() + " / "  + currChar.getModifiedStats().getTotalMana());
		lblCurrentExp.setText("Current EXP: " + currChar.getModifiedStats().getExperience() + " / "  + currChar.getModifiedStats().getLevelBoundry());
	}
}
