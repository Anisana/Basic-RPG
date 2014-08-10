package gui;

import item.WieldableItem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import unit.character.Character;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CharacterSheetGUI extends JFrame{

	private JLabel lblHealth_overview;
	private JLabel lblStrength;
	private JLabel lblHealthRegenRate_overview;
	private JLabel lblStamina;
	private JLabel lblFocus;
	private JLabel lblConstitution;
	private JLabel lblFocusRegenRate;
	private JLabel lblIntelligence;
	private JLabel lblMana;
	private JLabel lblSpirit;
	private JLabel lblManaRegenRate;
	private JLabel lblImageWithStuff;
	private JLabel lblImage;
	private JLabel lblName;
	private JLabel lblLevel;
	private JLabel lblStats;
	private JLabel lblWeight;
	private JLabel lblType;
	private JLabel lblHandsRequired;
	private JLabel lblDamageType;
	private JLabel lblMinDamage;
	private JLabel lblMaxDamage;
	private JLabel lblHealth_defence;
	private JLabel lblResists;
	private JLabel lblHealthRegenRate_defence;
	private JLabel lblArmourValue;

	//Launch the application.
	 
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CharacterSheetGUI window = new CharacterSheetGUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/*
	 * Create the application.
	 
	public CharacterSheetGUI() {
		initialize();
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	public CharacterSheetGUI(Character character) {
		//frmCharacterSheet = new JFrame();
		setTitle("Character Sheet - " + character.getName());
		setBounds(100, 100, 430, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		JPanel overviewTab = new JPanel();
		tabbedPane.addTab("Overview", null, overviewTab, null);
		overviewTab.setLayout(null);
		
		JPanel overviewStatsPanel = new JPanel();
		overviewStatsPanel.setBounds(0, 348, 407, 127);
		overviewTab.add(overviewStatsPanel);
		GridBagLayout gbl_overviewStatsPanel = new GridBagLayout();
		gbl_overviewStatsPanel.columnWidths = new int[]{207, 200, 0};
		gbl_overviewStatsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_overviewStatsPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_overviewStatsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		overviewStatsPanel.setLayout(gbl_overviewStatsPanel);
		
		lblHealth_overview = new JLabel("Health:");
		GridBagConstraints gbc_lblHealth_overview = new GridBagConstraints();
		gbc_lblHealth_overview.anchor = GridBagConstraints.WEST;
		gbc_lblHealth_overview.insets = new Insets(0, 0, 5, 5);
		gbc_lblHealth_overview.gridx = 0;
		gbc_lblHealth_overview.gridy = 0;
		overviewStatsPanel.add(lblHealth_overview, gbc_lblHealth_overview);
		
		lblStrength = new JLabel("Strength:");
		GridBagConstraints gbc_lblStrength = new GridBagConstraints();
		gbc_lblStrength.anchor = GridBagConstraints.WEST;
		gbc_lblStrength.insets = new Insets(0, 0, 5, 0);
		gbc_lblStrength.gridx = 1;
		gbc_lblStrength.gridy = 0;
		overviewStatsPanel.add(lblStrength, gbc_lblStrength);
		
		lblHealthRegenRate_overview = new JLabel("Health Regen Rate:");
		GridBagConstraints gbc_lblHealthRegenRate_overview = new GridBagConstraints();
		gbc_lblHealthRegenRate_overview.anchor = GridBagConstraints.WEST;
		gbc_lblHealthRegenRate_overview.insets = new Insets(0, 0, 5, 5);
		gbc_lblHealthRegenRate_overview.gridx = 0;
		gbc_lblHealthRegenRate_overview.gridy = 1;
		overviewStatsPanel.add(lblHealthRegenRate_overview, gbc_lblHealthRegenRate_overview);
		
		lblStamina = new JLabel("Stamina:");
		GridBagConstraints gbc_lblStamina = new GridBagConstraints();
		gbc_lblStamina.anchor = GridBagConstraints.WEST;
		gbc_lblStamina.insets = new Insets(0, 0, 5, 0);
		gbc_lblStamina.gridx = 1;
		gbc_lblStamina.gridy = 1;
		overviewStatsPanel.add(lblStamina, gbc_lblStamina);
		
		lblFocus = new JLabel("Focus:");
		GridBagConstraints gbc_lblFocus = new GridBagConstraints();
		gbc_lblFocus.anchor = GridBagConstraints.WEST;
		gbc_lblFocus.insets = new Insets(0, 0, 5, 5);
		gbc_lblFocus.gridx = 0;
		gbc_lblFocus.gridy = 2;
		overviewStatsPanel.add(lblFocus, gbc_lblFocus);
		
		lblConstitution = new JLabel("Constitution:");
		GridBagConstraints gbc_lblConstitution = new GridBagConstraints();
		gbc_lblConstitution.anchor = GridBagConstraints.WEST;
		gbc_lblConstitution.insets = new Insets(0, 0, 5, 0);
		gbc_lblConstitution.gridx = 1;
		gbc_lblConstitution.gridy = 2;
		overviewStatsPanel.add(lblConstitution, gbc_lblConstitution);
		
		lblFocusRegenRate = new JLabel("Focus Regen Rate:");
		GridBagConstraints gbc_lblFocusRegenRate = new GridBagConstraints();
		gbc_lblFocusRegenRate.anchor = GridBagConstraints.WEST;
		gbc_lblFocusRegenRate.insets = new Insets(0, 0, 5, 5);
		gbc_lblFocusRegenRate.gridx = 0;
		gbc_lblFocusRegenRate.gridy = 3;
		overviewStatsPanel.add(lblFocusRegenRate, gbc_lblFocusRegenRate);
		
		lblIntelligence = new JLabel("Intelligence:");
		GridBagConstraints gbc_lblIntelligence = new GridBagConstraints();
		gbc_lblIntelligence.anchor = GridBagConstraints.WEST;
		gbc_lblIntelligence.insets = new Insets(0, 0, 5, 0);
		gbc_lblIntelligence.gridx = 1;
		gbc_lblIntelligence.gridy = 3;
		overviewStatsPanel.add(lblIntelligence, gbc_lblIntelligence);
		
		lblMana = new JLabel("Mana:");
		GridBagConstraints gbc_lblMana = new GridBagConstraints();
		gbc_lblMana.anchor = GridBagConstraints.WEST;
		gbc_lblMana.insets = new Insets(0, 0, 5, 5);
		gbc_lblMana.gridx = 0;
		gbc_lblMana.gridy = 4;
		overviewStatsPanel.add(lblMana, gbc_lblMana);
		
		lblSpirit = new JLabel("Spirit:");
		GridBagConstraints gbc_lblSpirit = new GridBagConstraints();
		gbc_lblSpirit.anchor = GridBagConstraints.WEST;
		gbc_lblSpirit.insets = new Insets(0, 0, 5, 0);
		gbc_lblSpirit.gridx = 1;
		gbc_lblSpirit.gridy = 4;
		overviewStatsPanel.add(lblSpirit, gbc_lblSpirit);
		
		lblManaRegenRate = new JLabel("Mana Regen Rate:");
		GridBagConstraints gbc_lblManaRegenRate = new GridBagConstraints();
		gbc_lblManaRegenRate.anchor = GridBagConstraints.WEST;
		gbc_lblManaRegenRate.insets = new Insets(0, 0, 0, 5);
		gbc_lblManaRegenRate.gridx = 0;
		gbc_lblManaRegenRate.gridy = 5;
		overviewStatsPanel.add(lblManaRegenRate, gbc_lblManaRegenRate);
		
		JPanel overviewImagePanel = new JPanel();
		overviewImagePanel.setBounds(0, 0, 407, 349);
		overviewTab.add(overviewImagePanel);
		
		lblImageWithStuff = new JLabel("Image with stuff");
		overviewImagePanel.add(lblImageWithStuff);
		
		JPanel offenceTab = new JPanel();
		tabbedPane.addTab("Offence", null, offenceTab, null);
		offenceTab.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel weaponImagePanel = new JPanel();
		offenceTab.add(weaponImagePanel);
		
		lblImage = new JLabel("Image");
		weaponImagePanel.add(lblImage);
		
		JPanel weaponStatsPanel = new JPanel();
		offenceTab.add(weaponStatsPanel);
		GridBagLayout gbl_weaponStatsPanel = new GridBagLayout();
		gbl_weaponStatsPanel.columnWidths = new int[]{224, 183, 0};
		gbl_weaponStatsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_weaponStatsPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_weaponStatsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		weaponStatsPanel.setLayout(gbl_weaponStatsPanel);
		
		lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblname = new GridBagConstraints();
		gbc_lblname.anchor = GridBagConstraints.WEST;
		gbc_lblname.insets = new Insets(0, 0, 5, 5);
		gbc_lblname.gridx = 0;
		gbc_lblname.gridy = 0;
		weaponStatsPanel.add(lblName, gbc_lblname);
		
		lblLevel = new JLabel("Level:");
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.insets = new Insets(0, 0, 5, 0);
		gbc_lblLevel.anchor = GridBagConstraints.WEST;
		gbc_lblLevel.gridx = 1;
		gbc_lblLevel.gridy = 0;
		weaponStatsPanel.add(lblLevel, gbc_lblLevel);
		
		lblStats = new JLabel("Stats:");
		GridBagConstraints gbc_lblStats = new GridBagConstraints();
		gbc_lblStats.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblStats.gridheight = 5;
		gbc_lblStats.insets = new Insets(0, 0, 5, 5);
		gbc_lblStats.gridx = 0;
		gbc_lblStats.gridy = 1;
		weaponStatsPanel.add(lblStats, gbc_lblStats);
		
		lblWeight = new JLabel("Weight:");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.anchor = GridBagConstraints.WEST;
		gbc_lblWeight.insets = new Insets(0, 0, 5, 0);
		gbc_lblWeight.gridx = 1;
		gbc_lblWeight.gridy = 1;
		weaponStatsPanel.add(lblWeight, gbc_lblWeight);
		
		lblType = new JLabel("Type:");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.WEST;
		gbc_lblType.insets = new Insets(0, 0, 5, 0);
		gbc_lblType.gridx = 1;
		gbc_lblType.gridy = 2;
		weaponStatsPanel.add(lblType, gbc_lblType);
		
		lblHandsRequired = new JLabel("Hands Required:");
		GridBagConstraints gbc_lblHandsRequired = new GridBagConstraints();
		gbc_lblHandsRequired.anchor = GridBagConstraints.WEST;
		gbc_lblHandsRequired.insets = new Insets(0, 0, 5, 0);
		gbc_lblHandsRequired.gridx = 1;
		gbc_lblHandsRequired.gridy = 3;
		weaponStatsPanel.add(lblHandsRequired, gbc_lblHandsRequired);
		
		lblDamageType = new JLabel("Damage Type");
		GridBagConstraints gbc_lblDamageType = new GridBagConstraints();
		gbc_lblDamageType.anchor = GridBagConstraints.WEST;
		gbc_lblDamageType.insets = new Insets(0, 0, 5, 0);
		gbc_lblDamageType.gridx = 1;
		gbc_lblDamageType.gridy = 4;
		weaponStatsPanel.add(lblDamageType, gbc_lblDamageType);
		
		lblMinDamage = new JLabel("Min Damage:");
		GridBagConstraints gbc_lblMinDamage = new GridBagConstraints();
		gbc_lblMinDamage.anchor = GridBagConstraints.WEST;
		gbc_lblMinDamage.insets = new Insets(0, 0, 5, 0);
		gbc_lblMinDamage.gridx = 1;
		gbc_lblMinDamage.gridy = 5;
		weaponStatsPanel.add(lblMinDamage, gbc_lblMinDamage);
		
		lblMaxDamage = new JLabel("Max Damage:");
		GridBagConstraints gbc_lblMaxDamage = new GridBagConstraints();
		gbc_lblMaxDamage.anchor = GridBagConstraints.WEST;
		gbc_lblMaxDamage.gridx = 1;
		gbc_lblMaxDamage.gridy = 6;
		weaponStatsPanel.add(lblMaxDamage, gbc_lblMaxDamage);
		
		JPanel defenceTab = new JPanel();
		tabbedPane.addTab("Defence", null, defenceTab, null);
		GridBagLayout gbl_defenceTab = new GridBagLayout();
		gbl_defenceTab.columnWidths = new int[]{402, 0};
		gbl_defenceTab.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_defenceTab.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_defenceTab.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		defenceTab.setLayout(gbl_defenceTab);
		
		lblHealth_defence = new JLabel("Health:");
		GridBagConstraints gbc_lblHealth_defence = new GridBagConstraints();
		gbc_lblHealth_defence.anchor = GridBagConstraints.WEST;
		gbc_lblHealth_defence.insets = new Insets(0, 0, 5, 0);
		gbc_lblHealth_defence.gridx = 0;
		gbc_lblHealth_defence.gridy = 0;
		defenceTab.add(lblHealth_defence, gbc_lblHealth_defence);
		
		lblHealthRegenRate_defence = new JLabel("Health Regen Rate:");
		GridBagConstraints gbc_lblHealthRegenRate_defence = new GridBagConstraints();
		gbc_lblHealthRegenRate_defence.anchor = GridBagConstraints.WEST;
		gbc_lblHealthRegenRate_defence.insets = new Insets(0, 0, 5, 0);
		gbc_lblHealthRegenRate_defence.gridx = 0;
		gbc_lblHealthRegenRate_defence.gridy = 1;
		defenceTab.add(lblHealthRegenRate_defence, gbc_lblHealthRegenRate_defence);
		
		lblArmourValue = new JLabel("Armour Value:");
		GridBagConstraints gbc_lblArmourValue = new GridBagConstraints();
		gbc_lblArmourValue.anchor = GridBagConstraints.WEST;
		gbc_lblArmourValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblArmourValue.gridx = 0;
		gbc_lblArmourValue.gridy = 3;
		defenceTab.add(lblArmourValue, gbc_lblArmourValue);
		
		lblResists = new JLabel("Resists:");
		GridBagConstraints gbc_lblResists = new GridBagConstraints();
		gbc_lblResists.anchor = GridBagConstraints.WEST;
		gbc_lblResists.gridx = 0;
		gbc_lblResists.gridy = 4;
		defenceTab.add(lblResists, gbc_lblResists);
		
		JPanel bagTab = new JPanel();
		tabbedPane.addTab("Bag", null, bagTab, null);
		
		updateLabels(character);
	}
	
	private void updateLabels(Character character){
		lblHealth_overview.setText("Total Health: " + character.getModifiedStats().getTotalHealth());
		lblStrength.setText("Strength: " + character.getBaseStats().getStrength() + " (" + character.getModifiedStats().getStrength() + ")");
		lblHealthRegenRate_overview.setText("Health Regen Rate: " + character.getModifiedStats().getHealthRegenRate());
		lblStamina.setText("Stamina: " + character.getBaseStats().getStamina() + " (" + character.getModifiedStats().getStamina() + ")");
		lblFocus.setText("Total Focus: " + character.getModifiedStats().getTotalFocus());
		lblConstitution.setText("Constitution: " + character.getBaseStats().getConstitution() + " (" + character.getModifiedStats().getConstitution() + ")");
		lblFocusRegenRate.setText("Focus Regen Rate: " + character.getModifiedStats().getFocusRegenRate());
		lblIntelligence.setText("Intelligence: " + character.getBaseStats().getIntelligence() + " (" + character.getModifiedStats().getIntelligence() + ")");
		lblMana.setText("Total Mana: " + character.getModifiedStats().getTotalMana());
		lblSpirit.setText("Spirit: " + character.getBaseStats().getSpirit() + " (" + character.getModifiedStats().getSpirit() + ")");
		lblManaRegenRate.setText("Mana Regen Rate: " + character.getModifiedStats().getManaRegenRate());
		//lblImageWithStuff;
		//lblImage;
		
		WieldableItem temp = character.getEquipment().getWeapon();
		if(temp != null){
			lblName.setText("Name: " + temp.getName());
			lblLevel.setText("Level: " + temp.getStats().getLevel());
			lblStats.setText("<html>Stats:" + temp.getStats().getGUIStatString().replace("\n", "<br>"));
			lblWeight.setText("Weight: " + temp.getStats().getWeight());
			lblType.setText("Type: " + temp.getStats().getType());
			lblHandsRequired.setText("Hands Required: " + temp.getStats().getHandsRequired());
			lblDamageType.setText("Damage Type: " + temp.getStats().getDamageType());
			lblMinDamage.setText("Min Damage: " + temp.getStats().getMinDamage());
			lblMaxDamage.setText("Max Damage: " + temp.getStats().getMaxDamage());
		}
		
		lblHealth_defence.setText("Total Health: " + character.getModifiedStats().getTotalHealth());
		lblResists.setText("<html>Resists:<br>" + character.getEquipment().getDefenceBlock().getGUIResistString().replace("\n", "<br>"));
		lblHealthRegenRate_defence.setText("Health Regen Rate: " + character.getModifiedStats().getHealthRegenRate());
		lblArmourValue.setText("Armour Value: " + character.getEquipment().getDefenceBlock().getArmourValue());
	}
}
