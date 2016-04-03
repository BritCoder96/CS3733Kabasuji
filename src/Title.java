import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;

public class Title {

	private JFrame frame;
	public static Rectangle windowSize = new Rectangle(100, 100, 800, 650);

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
		frame.setBounds(windowSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblKabasuji = new JLabel("Kabasuji");
		lblKabasuji.setFont(new Font("Tahoma", Font.PLAIN, 44));
		lblKabasuji.setBounds(307, 58, 169, 63);
		frame.getContentPane().add(lblKabasuji);
		
		JLabel lblATileLaying = new JLabel("A tile laying game");
		lblATileLaying.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblATileLaying.setBounds(265, 163, 253, 39);
		frame.getContentPane().add(lblATileLaying);
		
		JButton btnNewGame = new JButton("Play");
		btnNewGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewGame.setBounds(332, 244, 120, 45);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOptions.setBounds(332, 331, 120, 45);
		frame.getContentPane().add(btnOptions);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBounds(332, 418, 120, 45);
		frame.getContentPane().add(btnExit);
	}
}
