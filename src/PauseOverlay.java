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
		setBounds(100, 100, 200, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPaused = new JLabel("Paused");
		lblPaused.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPaused.setBounds(53, 11, 77, 25);
		contentPane.add(lblPaused);
		
		JButton btnResume = new JButton("Resume");
		btnResume.setBounds(47, 49, 89, 23);
		contentPane.add(btnResume);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(47, 83, 89, 23);
		contentPane.add(btnOptions);
		
		JButton btnTitle = new JButton("Title");
		btnTitle.setBounds(47, 117, 89, 23);
		contentPane.add(btnTitle);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(47, 151, 89, 23);
		contentPane.add(btnQuit);
	}

}
