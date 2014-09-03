package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import path.Skill;
import unit.character.Character;
import unit.monster.Monster;
import world.CombatSquare;
import world.Encounter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Enumeration;

public class CombatGUI extends JFrame {

	private JPanel contentPane;
	private JPanel panelEnemyList;
	private JPanel panelBoard;
	private JPanel panelActions;
	private ButtonGroup actionGroup;

	/**
	 * Create the frame.
	 */
	public CombatGUI(Character currChar, Encounter currEncounter, Monster currMonster, 
			ActionListener charActionL, MouseListener gridMouseL, ActionListener passTurnL) {
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
		
		JPanel panelCharVitalStats = currChar.getVSPanel();
		panelCharVitalStats.setBounds(0, 0, 225, 119);
		panelPlayer.add(panelCharVitalStats);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 130, 205, 2);
		panelPlayer.add(separator);
		
		panelActions = new JPanel();
		panelActions.setBounds(0, 143, 225, 370);
		panelPlayer.add(panelActions);
		panelActions.setLayout(new GridLayout(10, 1, 5, 0));
		
		
		fillActionPanel(currChar, charActionL);
		
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
		
		panelEnemyList = new JPanel();
		panelEnemies.setViewportView(panelEnemyList);
		panelEnemyList.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		fillEnemyList(currMonster);
		
		panelBoard = new JPanel();
		panelBoard.setBounds(240, 30, 608, 608);
		contentPane.add(panelBoard);
		panelBoard.setLayout(new GridLayout(19, 19, 0, 0));
		
		drawGrid(currEncounter.getGrid(), gridMouseL);
		
		JLabel lblTitle = new JLabel("- Title -");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(519, 5, 46, 14);
		contentPane.add(lblTitle);
		
		JButton btnCharacterSheet = new JButton("Character Sheet");
		btnCharacterSheet.setBounds(240, 648, 130, 23);
		contentPane.add(btnCharacterSheet);
		
		JLabel lblTurnNumber = new JLabel("- Turn Number -");
		lblTurnNumber.setBounds(496, 658, 91, 14);
		contentPane.add(lblTurnNumber);
		
		JButton btnQuestLog = new JButton("Quest Log");
		btnQuestLog.setBounds(240, 677, 98, 23);
		contentPane.add(btnQuestLog);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(230, 11, 2, 690);
		contentPane.add(separator_1);
		
		JButton btnPassTurn = new JButton("Pass Turn");
		btnPassTurn.setBounds(750, 648, 97, 23);
		btnPassTurn.addActionListener(passTurnL);
		contentPane.add(btnPassTurn);
	}
	
	private void fillEnemyList(Monster currMonster){
		//panelEnemyList.add(new EnemyVitalStatisticsPanel(currMonster));
		panelEnemyList.add(currMonster.getVSPanel());
	}
	
	private void drawGrid(CombatSquare[][] grid, MouseListener gridMouseL){
		for(int i = 0; i < 19; i++){
			for(int j = 0; j < 19; j++){
				grid[i][j].setPreferredSize(new Dimension(32, 32));
				grid[i][j].addMouseListener(gridMouseL);
				panelBoard.add(grid[i][j]);
			}
		}
	}
	
	private void fillActionPanel(Character currChar, ActionListener listener){
		//ArrayList<Skill>
		actionGroup = new ButtonGroup();
		JToggleButton temp;
		int i = 0;
		for(Skill s : currChar.getActiveSkills()){
			temp = new JToggleButton(s.getName());
			temp.putClientProperty("index", i);
			temp.addActionListener(listener);
			actionGroup.add(temp);
			panelActions.add(temp);
			i++;
			
			if(i == 10) return;
		}
	}
	
	public void resetActionButtons(){
		actionGroup.clearSelection();
	}
}
