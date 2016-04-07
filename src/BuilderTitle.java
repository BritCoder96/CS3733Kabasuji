import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuilderTitle extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuilderTitle frame = new BuilderTitle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the panel.
	 */
	public BuilderTitle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Title.windowSize);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKabuildsuji = new JLabel("Ka-Build-Suji");
		lblKabuildsuji.setFont(new Font("Tahoma", Font.PLAIN, 44));
		lblKabuildsuji.setBounds(267, 109, 262, 50);
		contentPane.add(lblKabuildsuji);
		
		JLabel lblALevelEditor = new JLabel("A level editor for Kabasuji");
		lblALevelEditor.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblALevelEditor.setBounds(214, 166, 368, 50);
		contentPane.add(lblALevelEditor);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(338, 305, 120, 45);
		contentPane.add(btnEdit);

		
	}
}
