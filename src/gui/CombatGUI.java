package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

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
		panelPlayer.setBounds(0, 0, 225, 705);
		contentPane.add(panelPlayer);
		
		JPanel panelEnemies = new JPanel();
		panelEnemies.setBounds(857, 0, 225, 705);
		contentPane.add(panelEnemies);
		
		JPanel panel = new JPanel();
		panel.setBounds(237, 13, 608, 608);
		contentPane.add(panel);
		
		JLabel lblPlayGrid = new JLabel("Play grid");
		panel.add(lblPlayGrid);
	}

}
