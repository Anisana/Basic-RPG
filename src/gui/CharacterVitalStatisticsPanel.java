package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import unit.character.Character;

public class CharacterVitalStatisticsPanel extends JPanel {
	
	//pointer to have a reference for showing character stats etc
	private Character currChar;
	
	//private JLayeredPane panelCharSheet;
	private JLabel lblName;
	private JLabel lblPathLine;
	private JLabel lblCurrentHP;
	private JLabel lblCurrentFocus;
	private JLabel lblCurrentMana;
	private JLabel lblCurrentExp;

	/**
	 * Create the panel.
	 */
	public CharacterVitalStatisticsPanel(Character currChar) {
		this.currChar = currChar;
		
		
		//panelCharSheet = new JLayeredPane();
		setBounds(0, 25, 191, 115);
		GridBagLayout gbl_panelCharSheet = new GridBagLayout();
		gbl_panelCharSheet.columnWidths = new int[] {190, 0};
		gbl_panelCharSheet.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelCharSheet.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelCharSheet.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		//setVisible(false);
		setLayout(gbl_panelCharSheet);
		
		lblName = new JLabel(" Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		add(lblName, gbc_lblName);
		
		lblPathLine = new JLabel(" Level Path line");
		GridBagConstraints gbc_lblPathLine = new GridBagConstraints();
		gbc_lblPathLine.anchor = GridBagConstraints.WEST;
		gbc_lblPathLine.insets = new Insets(0, 0, 5, 0);
		gbc_lblPathLine.gridx = 0;
		gbc_lblPathLine.gridy = 1;
		add(lblPathLine, gbc_lblPathLine);
		
		lblCurrentHP = new JLabel("current HP");
		GridBagConstraints gbc_lblCurrentHP = new GridBagConstraints();
		gbc_lblCurrentHP.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentHP.gridx = 0;
		gbc_lblCurrentHP.gridy = 2;
		add(lblCurrentHP, gbc_lblCurrentHP);
		
		lblCurrentFocus = new JLabel("current Focus");
		GridBagConstraints gbc_lblCurrentFocus = new GridBagConstraints();
		gbc_lblCurrentFocus.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentFocus.gridx = 0;
		gbc_lblCurrentFocus.gridy = 3;
		add(lblCurrentFocus, gbc_lblCurrentFocus);
		
		lblCurrentMana = new JLabel("current Mana");
		GridBagConstraints gbc_lblCurrentMana = new GridBagConstraints();
		gbc_lblCurrentMana.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentMana.gridx = 0;
		gbc_lblCurrentMana.gridy = 4;
		add(lblCurrentMana, gbc_lblCurrentMana);
		
		lblCurrentExp = new JLabel("current exp");
		GridBagConstraints gbc_lblCurrentExp = new GridBagConstraints();
		gbc_lblCurrentExp.gridx = 0;
		gbc_lblCurrentExp.gridy = 5;
		add(lblCurrentExp, gbc_lblCurrentExp);
		
		updateCharSheet();
	}
	
	public void updateCharSheet(){
		lblName.setText(" " + currChar.getName());
		lblPathLine.setText(" Level " + currChar.getModifiedStats().getLevel() + " blah");
		lblCurrentHP.setText("Current HP: " + currChar.getModifiedStats().getCurrentHealth() + " / "  + currChar.getModifiedStats().getTotalHealth());
		lblCurrentFocus.setText("Current Focus: " + currChar.getModifiedStats().getCurrentFocus() + " / "  + currChar.getModifiedStats().getTotalFocus());
		lblCurrentMana.setText("Current Mana: " + currChar.getModifiedStats().getCurrentMana() + " / "  + currChar.getModifiedStats().getTotalMana());
		lblCurrentExp.setText("Current EXP: " + currChar.getModifiedStats().getExperience() + " / "  + currChar.getModifiedStats().getLevelBoundry());
	}

}
