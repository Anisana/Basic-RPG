package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import character.Character;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JToolBar;

public class CombatGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CombatGUI frame = new CombatGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CombatGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPlayer = new JPanel();
		panelPlayer.setBounds(0, 0, 225, 712);
		contentPane.add(panelPlayer);
		panelPlayer.setLayout(null);
		
		JPanel panelCharVitalStats = new CharacterVitalStatisticsPanel(new Character());
		panelCharVitalStats.setBounds(0, 0, 225, 119);
		panelPlayer.add(panelCharVitalStats);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 130, 205, 2);
		panelPlayer.add(separator);
		
		JPanel panelActions = new JPanel();
		panelActions.setBounds(0, 143, 225, 370);
		panelPlayer.add(panelActions);
		panelActions.setLayout(new GridLayout(10, 1, 5, 0));
		
		JButton btnBasicAttack = new JButton("Basic Attack");
		panelActions.add(btnBasicAttack);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 524, 205, 2);
		panelPlayer.add(separator_2);
		
		JPanel panelUsableItems = new JPanel();
		panelUsableItems.setBounds(0, 537, 225, 175);
		panelPlayer.add(panelUsableItems);
		
		JLabel lblUsableItemTab = new JLabel("Usable Item tab, current target or both");
		panelUsableItems.add(lblUsableItemTab);
		
		JScrollPane panelEnemies = new JScrollPane();
		panelEnemies.setBounds(859, 0, 225, 712);
		contentPane.add(panelEnemies);
		
		JPanel panelBoard = new JPanel();
		panelBoard.setBounds(237, 30, 608, 608);
		contentPane.add(panelBoard);
		
		JLabel lblPlayGrid = new JLabel("Play grid");
		panelBoard.add(lblPlayGrid);
		
		JLabel lblTitle = new JLabel("- Title -");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(519, 5, 46, 14);
		contentPane.add(lblTitle);
		
		JButton btnCharacterSheet = new JButton("Character Sheet");
		btnCharacterSheet.setBounds(235, 649, 130, 23);
		contentPane.add(btnCharacterSheet);
		
		JLabel lblTurnNumber = new JLabel("- Turn Number -");
		lblTurnNumber.setBounds(496, 658, 91, 14);
		contentPane.add(lblTurnNumber);
		
		JButton btnQuestLog = new JButton("Quest Log");
		btnQuestLog.setBounds(235, 678, 98, 23);
		contentPane.add(btnQuestLog);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(230, 11, 2, 690);
		contentPane.add(separator_1);
	}
}
