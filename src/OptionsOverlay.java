import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class OptionsOverlay extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionsOverlay frame = new OptionsOverlay();
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
	public OptionsOverlay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOptions.setBounds(50, 11, 83, 25);
		contentPane.add(lblOptions);
		
		JButton btn1 = new JButton("Option 1");
		btn1.setBounds(47, 49, 89, 23);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("Option 2");
		btn2.setBounds(47, 83, 89, 23);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("Option 3");
		btn3.setBounds(47, 117, 89, 23);
		contentPane.add(btn3);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(47, 151, 89, 23);
		contentPane.add(btnBack);
	}

}
