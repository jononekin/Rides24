package gui;

import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import domain.Driver;
import domain.Traveler;

public class LoginGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel JLabelEnterUser = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.EnterUser"));
	private JLabel JLabelEnterPass  = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.EnterPass"));
	private JButton JButtonLogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.Login"));
	private JTextField textFieldUser;	
	private final JLabel JLabelLogin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.Login"));
	private JPasswordField passwordField;
	private JTextArea textArea;
	
	String user;
	String password;
	User u;

	private BLFacade facade;

	
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
		
		facade = MainGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabelEnterUser.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabelEnterUser.setBounds(42, 74, 144, 14);
		contentPane.add(JLabelEnterUser);
		JLabelEnterPass.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabelEnterPass.setBounds(42, 126, 144, 14);
		contentPane.add(JLabelEnterPass);
		JButtonLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(textFieldUser.getText() != null && passwordField.getText() != null) {
					user = textFieldUser.getText();
					password = passwordField.getText();
					u = facade.isLogged(user, password);
					if(u != null) {
						if(u instanceof Driver) {
							Driver d = (Driver) u;
							JFrame a = new DriverMainGUI(d);
							a.setVisible(true);
						} else if(u instanceof Traveler) {
							
						}
					} else {
						textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.WrongUserPass"));
					}
				} else {
					textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.WrongWrited"));
				}
			}				
		});
		
		JButtonLogin.setBounds(155, 170, 106, 23);
		contentPane.add(JButtonLogin);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(196, 72, 193, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(196, 124, 193, 20);
		contentPane.add(passwordField);
		
		textArea = new JTextArea();
		textArea.setBounds(93, 216, 255, 22);
		contentPane.add(textArea);
		JLabelLogin.setBounds(155, 23, 125, 23);
		JLabelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.add(JLabelLogin);
	}
}
