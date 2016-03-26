import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Title {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Title window = new Title();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Title() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblKabasuji = new JLabel("Kabasuji");
		lblKabasuji.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblKabasuji.setBounds(171, 20, 95, 44);
		frame.getContentPane().add(lblKabasuji);
		
		JLabel lblATileLaying = new JLabel("A tile laying game");
		lblATileLaying.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblATileLaying.setBounds(154, 84, 128, 27);
		frame.getContentPane().add(lblATileLaying);
		
		JButton btnNewGame = new JButton("Play");
		btnNewGame.setBounds(174, 131, 89, 23);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(174, 174, 89, 23);
		frame.getContentPane().add(btnOptions);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(174, 217, 89, 23);
		frame.getContentPane().add(btnExit);
	}
}
