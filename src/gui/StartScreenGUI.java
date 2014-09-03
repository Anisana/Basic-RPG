package gui;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;

public class StartScreenGUI extends JFrame{

	private JPanel contentPane;
	private JButton btnStart;
	private JLabel lblLoading;
	
	
	/*public static void open(final ActionListener listener){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = initFrame(listener);
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
	public StartScreenGUI(ActionListener listener, MouseListener mlistener, KeyListener klistener) {
		//JFrame frame = new JFrame();
		setTitle("Basic RPG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLoading = new JLabel("Loading Assets...");
		lblLoading.setBounds(167, 158, 97, 25);
		contentPane.add(lblLoading);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(167, 158, 97, 25);
		btnStart.addActionListener(listener);
		btnStart.addMouseListener(mlistener);
		btnStart.addKeyListener(klistener);
		btnStart.setVisible(false);
		btnStart.setToolTipText("ctrl is not down");
		contentPane.add(btnStart);
		
		JLabel lblBasicRpg = new JLabel("Basic RPG");
		lblBasicRpg.setHorizontalAlignment(SwingConstants.CENTER);
		lblBasicRpg.setBounds(167, 42, 97, 16);
		contentPane.add(lblBasicRpg);
	}
	
	public void showStartBtn(){
		this.lblLoading.setVisible(false);
		this.btnStart.setVisible(true);
	}
	
}
