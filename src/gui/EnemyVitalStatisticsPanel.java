package gui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import unit.monster.Monster;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class EnemyVitalStatisticsPanel extends JPanel {
	
	private Monster currMonster;
	
	
	private JLabel lblName;
	private JLabel lblLevel;
	private JLabel lblCurrentHP;

	/**
	 * Create the panel.
	 */
	public EnemyVitalStatisticsPanel(Monster monster) {
		this.currMonster = monster;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{206, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblName = new JLabel(" Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		add(lblName, gbc_lblName);
		
		lblLevel = new JLabel(" Level");
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.insets = new Insets(0, 0, 5, 0);
		gbc_lblLevel.anchor = GridBagConstraints.WEST;
		gbc_lblLevel.gridx = 0;
		gbc_lblLevel.gridy = 1;
		add(lblLevel, gbc_lblLevel);
		
		lblCurrentHP = new JLabel("Current HP:  /");
		GridBagConstraints gbc_lblCurrentHp = new GridBagConstraints();
		gbc_lblCurrentHp.gridx = 0;
		gbc_lblCurrentHp.gridy = 2;
		add(lblCurrentHP, gbc_lblCurrentHp);
		
		updateEnemyPane();

	}
	
	public void updateEnemyPane(){
		lblName.setText(" " + currMonster.getName());
		lblLevel.setText(" Level " + currMonster.getModifiedStats().getLevel());
		lblCurrentHP.setText("Current HP: " + currMonster.getModifiedStats().getCurrentHealth() + " / "  + currMonster.getModifiedStats().getTotalHealth());
		//lblCurrentFocus.setText("Current Focus: " + currChar.getModifiedStats().getCurrentFocus() + " / "  + currChar.getModifiedStats().getTotalFocus());
		//lblCurrentMana.setText("Current Mana: " + currChar.getModifiedStats().getCurrentMana() + " / "  + currChar.getModifiedStats().getTotalMana());
		//lblCurrentExp.setText("Current EXP: " + currChar.getModifiedStats().getExperience() + " / "  + currChar.getModifiedStats().getLevelBoundry());
	}

}
