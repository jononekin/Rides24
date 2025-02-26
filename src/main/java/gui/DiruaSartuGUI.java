package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DiruaSartuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiruaSartuGUI frame = new DiruaSartuGUI();
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
	public DiruaSartuGUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.Title"));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.Deposit"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(174, 200, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(269, 117, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lbl_Title = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.Title"));
		lbl_Title.setBounds(77, 120, 49, 14);
		contentPane.add(lbl_Title);
		
		JLabel lbl_Text= new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.Text"));
		lbl_Text.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Text.setBounds(160, 36, 89, 23);
		contentPane.add(lbl_Text);
	}
}
