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

import unit.character.Character;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MapScreenGUI extends JFrame {
	
	

	private JPanel contentPane;
	private JLayeredPane panelCharSheet;
	private CharacterVitalStatisticsPanel panelVitalStats;
	private JScrollPane scrollPaneQuests;

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
	public MapScreenGUI(Character currChar, ActionListener charSheetAL, ActionListener testCombatL) {
		//this.currChar = currChar;
		
		
		setTitle("World Map");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane panel = new JLayeredPane();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 1084, 712);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMap = new JLabel("Map with buttons");
		lblMap.setBounds(513, 282, 98, 16);
		panel.add(lblMap);
		
		JButton btnTestCombat = new JButton("Test combat");
		btnTestCombat.addActionListener(testCombatL);
		btnTestCombat.setBounds(494, 432, 117, 25);
		panel.add(btnTestCombat);
		
		panelCharSheet = new JLayeredPane();
		panelCharSheet.setBounds(0, 25, 192, 157);
		panel.add(panelCharSheet);
		GridBagLayout gbl_panelCharSheet = new GridBagLayout();
		gbl_panelCharSheet.columnWidths = new int[] {191, 0};
		gbl_panelCharSheet.rowHeights = new int[]{0, 0, 0};
		gbl_panelCharSheet.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelCharSheet.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelCharSheet.setVisible(false);
		panelCharSheet.setLayout(gbl_panelCharSheet);
		
		panelVitalStats = new CharacterVitalStatisticsPanel(currChar);
		GridBagConstraints gbc_panelVitalStats = new GridBagConstraints();
		gbc_panelVitalStats.insets = new Insets(0, 0, 5, 0);
		gbc_panelVitalStats.fill = GridBagConstraints.BOTH;
		gbc_panelVitalStats.gridx = 0;
		gbc_panelVitalStats.gridy = 0;
		panelCharSheet.add(panelVitalStats, gbc_panelVitalStats);
		
		JButton btnShowDetailedSheet = new JButton("Show Detailed Sheet");
		btnShowDetailedSheet.addActionListener(charSheetAL);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridx = 0;
		gbc_button.gridy = 1;
		panelCharSheet.add(btnShowDetailedSheet, gbc_button);
		
		scrollPaneQuests = new JScrollPane();
		scrollPaneQuests.setBounds(192, 25, 187, 257);
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
		btnSettings.setBounds(987, 0, 97, 25);
		panel.add(btnSettings);
	}
	
	private void updateCharSheet(){
		panelVitalStats.updateCharSheet();
	}
	
}
