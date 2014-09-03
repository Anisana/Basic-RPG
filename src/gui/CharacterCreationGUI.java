package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import path.Path;

public class CharacterCreationGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JComboBox<Path> cbPath;
	private JButton strUP;
	private JButton strDOWN;
	private JLabel lblStrValue;
	private JButton staUP;
	private JButton staDOWN;
	private JLabel lblStaValue;
	private JButton conUP;
	private JButton conDOWN;
	private JLabel lblConValue;
	private JButton intUP;
	private JButton intDOWN;
	private JLabel lblIntValue;
	private JButton spiUP;
	private JButton spiDOWN;
	private JLabel lblSpiValue;
	private JLabel lblPointsRemaining;

	/**
	 * Create the frame.
	 */
	public CharacterCreationGUI(ArrayList<Path> paths, ActionListener statUpL, ActionListener statDownL, ActionListener createL) {
		setTitle("Character Creation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTop = new JPanel();
		panelTop.setBounds(12, 13, 358, 49);
		contentPane.add(panelTop);
		GridBagLayout gbl_panelTop = new GridBagLayout();
		gbl_panelTop.columnWidths = new int[]{0, 0, 0};
		gbl_panelTop.rowHeights = new int[]{0, 0, 0};
		gbl_panelTop.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelTop.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelTop.setLayout(gbl_panelTop);

		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		panelTop.add(lblName, gbc_lblName);

		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 0;
		panelTop.add(txtName, gbc_txtName);
		txtName.setColumns(10);

		JLabel lblPath = new JLabel("Path:");
		GridBagConstraints gbc_lblPath = new GridBagConstraints();
		gbc_lblPath.anchor = GridBagConstraints.EAST;
		gbc_lblPath.insets = new Insets(0, 0, 0, 5);
		gbc_lblPath.gridx = 0;
		gbc_lblPath.gridy = 1;
		panelTop.add(lblPath, gbc_lblPath);

		cbPath = prepareComboBox(paths);
		GridBagConstraints gbc_cbPath = new GridBagConstraints();
		gbc_cbPath.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbPath.gridx = 1;
		gbc_cbPath.gridy = 1;
		panelTop.add(cbPath, gbc_cbPath);

		JPanel panelStats = new JPanel();
		panelStats.setBounds(12, 104, 358, 157);
		contentPane.add(panelStats);
		GridBagLayout gbl_panelStats = new GridBagLayout();
		gbl_panelStats.columnWidths = new int[]{0, 0, 0, 23, 0};
		gbl_panelStats.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelStats.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelStats.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelStats.setLayout(gbl_panelStats);

		JLabel lblStrength = new JLabel("Strength:");
		GridBagConstraints gbc_lblStrength = new GridBagConstraints();
		gbc_lblStrength.anchor = GridBagConstraints.WEST;
		gbc_lblStrength.insets = new Insets(0, 0, 5, 5);
		gbc_lblStrength.gridx = 0;
		gbc_lblStrength.gridy = 0;
		panelStats.add(lblStrength, gbc_lblStrength);

		strUP = new JButton("+");
		strUP.putClientProperty("stat", "strength");
		strUP.addActionListener(statUpL);
		GridBagConstraints gbc_strUP = new GridBagConstraints();
		gbc_strUP.insets = new Insets(0, 0, 5, 5);
		gbc_strUP.gridx = 1;
		gbc_strUP.gridy = 0;
		panelStats.add(strUP, gbc_strUP);

		strDOWN = new JButton("-");
		strDOWN.putClientProperty("stat", "strength");
		strDOWN.addActionListener(statDownL);
		strDOWN.setEnabled(false);
		GridBagConstraints gbc_strDOWN = new GridBagConstraints();
		gbc_strDOWN.insets = new Insets(0, 0, 5, 5);
		gbc_strDOWN.gridx = 2;
		gbc_strDOWN.gridy = 0;
		panelStats.add(strDOWN, gbc_strDOWN);

		lblStrValue = new JLabel("0.0");
		GridBagConstraints gbc_lblStrValue = new GridBagConstraints();
		gbc_lblStrValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblStrValue.gridx = 3;
		gbc_lblStrValue.gridy = 0;
		panelStats.add(lblStrValue, gbc_lblStrValue);

		JLabel lblStamina = new JLabel("Stamina:");
		GridBagConstraints gbc_lblStamina = new GridBagConstraints();
		gbc_lblStamina.anchor = GridBagConstraints.WEST;
		gbc_lblStamina.insets = new Insets(0, 0, 5, 5);
		gbc_lblStamina.gridx = 0;
		gbc_lblStamina.gridy = 1;
		panelStats.add(lblStamina, gbc_lblStamina);

		staUP = new JButton("+");
		staUP.putClientProperty("stat", "stamina");
		staUP.addActionListener(statUpL);
		GridBagConstraints gbc_staUP = new GridBagConstraints();
		gbc_staUP.insets = new Insets(0, 0, 5, 5);
		gbc_staUP.gridx = 1;
		gbc_staUP.gridy = 1;
		panelStats.add(staUP, gbc_staUP);

		staDOWN = new JButton("-");
		staDOWN.putClientProperty("stat", "stamina");
		staDOWN.addActionListener(statDownL);
		staDOWN.setEnabled(false);
		GridBagConstraints gbc_staDOWN = new GridBagConstraints();
		gbc_staDOWN.insets = new Insets(0, 0, 5, 5);
		gbc_staDOWN.gridx = 2;
		gbc_staDOWN.gridy = 1;
		panelStats.add(staDOWN, gbc_staDOWN);

		lblStaValue = new JLabel("0.0");
		GridBagConstraints gbc_lblStaValue = new GridBagConstraints();
		gbc_lblStaValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblStaValue.gridx = 3;
		gbc_lblStaValue.gridy = 1;
		panelStats.add(lblStaValue, gbc_lblStaValue);

		JLabel lblConstitution = new JLabel("Constitution:");
		GridBagConstraints gbc_lblConstitution = new GridBagConstraints();
		gbc_lblConstitution.anchor = GridBagConstraints.WEST;
		gbc_lblConstitution.insets = new Insets(0, 0, 5, 5);
		gbc_lblConstitution.gridx = 0;
		gbc_lblConstitution.gridy = 2;
		panelStats.add(lblConstitution, gbc_lblConstitution);

		conUP = new JButton("+");
		conUP.putClientProperty("stat", "constitution");
		conUP.addActionListener(statUpL);
		GridBagConstraints gbc_conUP = new GridBagConstraints();
		gbc_conUP.insets = new Insets(0, 0, 5, 5);
		gbc_conUP.gridx = 1;
		gbc_conUP.gridy = 2;
		panelStats.add(conUP, gbc_conUP);

		conDOWN = new JButton("-");
		conDOWN.putClientProperty("stat", "constitution");
		conDOWN.addActionListener(statDownL);
		conDOWN.setEnabled(false);
		GridBagConstraints gbc_conDOWN = new GridBagConstraints();
		gbc_conDOWN.insets = new Insets(0, 0, 5, 5);
		gbc_conDOWN.gridx = 2;
		gbc_conDOWN.gridy = 2;
		panelStats.add(conDOWN, gbc_conDOWN);

		lblConValue = new JLabel("0.0");
		GridBagConstraints gbc_lblConValue = new GridBagConstraints();
		gbc_lblConValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblConValue.gridx = 3;
		gbc_lblConValue.gridy = 2;
		panelStats.add(lblConValue, gbc_lblConValue);

		JLabel lblIntelligence = new JLabel("Intelligence:");
		GridBagConstraints gbc_lblIntelligence = new GridBagConstraints();
		gbc_lblIntelligence.insets = new Insets(0, 0, 5, 5);
		gbc_lblIntelligence.anchor = GridBagConstraints.WEST;
		gbc_lblIntelligence.gridx = 0;
		gbc_lblIntelligence.gridy = 3;
		panelStats.add(lblIntelligence, gbc_lblIntelligence);

		intUP = new JButton("+");
		intUP.putClientProperty("stat", "intelligence");
		intUP.addActionListener(statUpL);
		GridBagConstraints gbc_intUP = new GridBagConstraints();
		gbc_intUP.insets = new Insets(0, 0, 5, 5);
		gbc_intUP.gridx = 1;
		gbc_intUP.gridy = 3;
		panelStats.add(intUP, gbc_intUP);

		intDOWN = new JButton("-");
		intDOWN.putClientProperty("stat", "intelligence");
		intDOWN.addActionListener(statDownL);
		intDOWN.setEnabled(false);
		GridBagConstraints gbc_intDOWN = new GridBagConstraints();
		gbc_intDOWN.insets = new Insets(0, 0, 5, 5);
		gbc_intDOWN.gridx = 2;
		gbc_intDOWN.gridy = 3;
		panelStats.add(intDOWN, gbc_intDOWN);

		lblIntValue = new JLabel("0.0");
		GridBagConstraints gbc_lblIntValue = new GridBagConstraints();
		gbc_lblIntValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblIntValue.gridx = 3;
		gbc_lblIntValue.gridy = 3;
		panelStats.add(lblIntValue, gbc_lblIntValue);

		JLabel lblSpirit = new JLabel("Spirit:");
		GridBagConstraints gbc_lblSpirit = new GridBagConstraints();
		gbc_lblSpirit.insets = new Insets(0, 0, 0, 5);
		gbc_lblSpirit.anchor = GridBagConstraints.WEST;
		gbc_lblSpirit.gridx = 0;
		gbc_lblSpirit.gridy = 4;
		panelStats.add(lblSpirit, gbc_lblSpirit);

		spiUP = new JButton("+");
		spiUP.putClientProperty("stat", "spirit");
		spiUP.addActionListener(statUpL);
		GridBagConstraints gbc_spiUP = new GridBagConstraints();
		gbc_spiUP.insets = new Insets(0, 0, 0, 5);
		gbc_spiUP.gridx = 1;
		gbc_spiUP.gridy = 4;
		panelStats.add(spiUP, gbc_spiUP);

		spiDOWN = new JButton("-");
		spiDOWN.putClientProperty("stat", "spirit");
		spiDOWN.addActionListener(statDownL);
		spiDOWN.setEnabled(false);
		GridBagConstraints gbc_spiDOWN = new GridBagConstraints();
		gbc_spiDOWN.insets = new Insets(0, 0, 0, 5);
		gbc_spiDOWN.gridx = 2;
		gbc_spiDOWN.gridy = 4;
		panelStats.add(spiDOWN, gbc_spiDOWN);

		lblSpiValue = new JLabel("0.0");
		GridBagConstraints gbc_lblSpiValue = new GridBagConstraints();
		gbc_lblSpiValue.gridx = 3;
		gbc_lblSpiValue.gridy = 4;
		panelStats.add(lblSpiValue, gbc_lblSpiValue);

		lblPointsRemaining = new JLabel("Points Remaining: 15");
		lblPointsRemaining.setBounds(12, 75, 138, 16);
		contentPane.add(lblPointsRemaining);

		JButton btnCreateAndPlay = new JButton("Create and Play");
		btnCreateAndPlay.putClientProperty("goal", "play");
		btnCreateAndPlay.addActionListener(createL);
		btnCreateAndPlay.setBounds(247, 377, 123, 25);
		contentPane.add(btnCreateAndPlay);

		JButton btnCreate = new JButton("Create");
		btnCreate.putClientProperty("goal", "create");
		btnCreate.addActionListener(createL);
		btnCreate.setBounds(138, 377, 97, 25);
		contentPane.add(btnCreate);
	}

	private JComboBox<Path> prepareComboBox(ArrayList<Path> paths){
		JComboBox<Path> result = new JComboBox<Path>();

		for(Path p : paths){
			if(p != null){
				if(p.getOwner().equals("all") || p.getOwner().equals("player")){
					result.addItem(p);
				}
			}
		}

		return result;
	}

	public void disableIncrementButtons(){
		strUP.setEnabled(false);
		staUP.setEnabled(false);
		conUP.setEnabled(false);
		intUP.setEnabled(false);
		spiUP.setEnabled(false);
	}

	public void enableIncrementButtons(){
		strUP.setEnabled(true);
		staUP.setEnabled(true);
		conUP.setEnabled(true);
		intUP.setEnabled(true);
		spiUP.setEnabled(true);
	}

	public void disableDecrementButton(String stat){
		switch(stat){
		case "strength": strDOWN.setEnabled(false); return;
		case "stamina": staDOWN.setEnabled(false); return;
		case "constitution": conDOWN.setEnabled(false); return;
		case "intelligence": intDOWN.setEnabled(false); return;
		case "spirit": spiDOWN.setEnabled(false); return;
		}
	}

	public void enableDecrementButton(String stat){
		switch(stat){
		case "strength": strDOWN.setEnabled(true); return;
		case "stamina": staDOWN.setEnabled(true); return;
		case "constitution": conDOWN.setEnabled(true); return;
		case "intelligence": intDOWN.setEnabled(true); return;
		case "spirit": spiDOWN.setEnabled(true); return;
		}
	}

	public void setRemainingPoints(int points){
		this.lblPointsRemaining.setText("Points remaining: " + points);
	}

	public void setStatTotal(String stat, double total){
		switch(stat){
		case "strength": lblStrValue.setText(total+""); return;
		case "stamina": lblStaValue.setText(total+""); return;
		case "constitution": lblConValue.setText(total+""); return;
		case "intelligence": lblIntValue.setText(total+""); return;
		case "spirit": lblSpiValue.setText(total+""); return;
		}
	}

	public String getName(){
		return this.txtName.getText();
	}

	public Path getPath(){
		return (Path) cbPath.getSelectedItem();
	}
}
