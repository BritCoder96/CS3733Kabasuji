import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class PauseOverlay extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PauseOverlay frame = new PauseOverlay();
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
	public PauseOverlay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPaused = new JLabel("Paused");
		lblPaused.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPaused.setBounds(67, 28, 99, 34);
		contentPane.add(lblPaused);
		
		JButton btnResume = new JButton("Resume");
		btnResume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnResume.setBounds(57, 100, 120, 45);
		contentPane.add(btnResume);
		
		JButton btnTitle = new JButton("Title");
		btnTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTitle.setBounds(57, 163, 120, 45);
		contentPane.add(btnTitle);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuit.setBounds(57, 230, 120, 45);
		contentPane.add(btnQuit);
	}

}
