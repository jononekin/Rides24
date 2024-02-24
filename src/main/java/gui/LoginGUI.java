package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JLabel JLabelEnterUser = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.EnterUser"));
	private JLabel JLabelEnterPass  = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.EnterPass"));
	private JButton JButtonLogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.Login"));
	private JTextField textFieldUser;	
	private final JLabel JLabelLogin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.Login"));
	private JPasswordField passwordField;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabelEnterUser.setBounds(56, 75, 60, 14);
		contentPane.add(JLabelEnterUser);
		
		JLabelEnterPass.setBounds(56, 127, 60, 14);
		contentPane.add(JLabelEnterPass);
		
		JButtonLogin.setBounds(172, 170, 89, 23);
		contentPane.add(JButtonLogin);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(155, 72, 193, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(155, 124, 193, 20);
		contentPane.add(passwordField);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(93, 216, 255, 22);
		contentPane.add(textArea);
		JLabelLogin.setBounds(172, 22, 125, 23);
		
		contentPane.add(JLabelLogin);
	}
}
