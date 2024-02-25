package gui;

import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Driver;
import domain.User;
import domain.Traveler;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textEmail;
	private JTextField textName;
	private JPasswordField textPassword;
	private JButton jButtonRegister = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.Register"));
	private JRadioButton jButtonTraveler = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.Traveler"));
	private JRadioButton jButtonDriver = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.Driver"));
	private JLabel jLabelEnterPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.EnterPassword"));
	private JLabel jLabelEnterName = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.EnterName"));
	private JLabel jLabelEnterEmail = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.EnterEmail"));
	private JLabel jLabelRegister = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.Register"));
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	String email;
	String name;
	String password;
	String type = null;
	private JTextArea textArea;
	
	private BLFacade facade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		
		facade = MainGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		jLabelRegister.setHorizontalAlignment(SwingConstants.CENTER);
		
		jLabelRegister.setBounds(166, 28, 106, 13);
		contentPane.add(jLabelRegister);
		
		jLabelEnterEmail.setBounds(31, 69, 137, 13);
		contentPane.add(jLabelEnterEmail);
		
		jLabelEnterName.setBounds(31, 108, 137, 13);
		contentPane.add(jLabelEnterName);
		
		jLabelEnterPassword.setBounds(31, 147, 137, 13);
		contentPane.add(jLabelEnterPassword);
		buttonGroup.add(jButtonDriver);
		jButtonDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "Driver";
			}
		});
		
		jButtonDriver.setBounds(82, 186, 103, 21);
		contentPane.add(jButtonDriver);
		buttonGroup.add(jButtonTraveler);
		jButtonTraveler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "Traveler";
			}
		});
		
		jButtonTraveler.setBounds(241, 186, 103, 21);
		contentPane.add(jButtonTraveler);
		buttonGroup.add(jButtonDriver);
		
		textEmail = new JTextField();
		textEmail.setBounds(200, 66, 193, 19);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(200, 105, 193, 19);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(200, 144, 193, 19);
		contentPane.add(textPassword);
		jButtonRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				email = textEmail.getText();
				name = textName.getText();
				password = textPassword.getText();
				if(email!=null && !email.isBlank() && name!=null && !name.isBlank() && password!=null && !password.isBlank() && type != null) {
					User u = facade.register(email, name, password, 0.0, type);
					if(u!=null) {
						if(u instanceof Driver) {
							Driver d = (Driver) u;
							JFrame a = new DriverMainGUI(d);
							a.setVisible(true);
						} else if(u instanceof Traveler) {
							
						}
					} else {
						textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.UserAlreadyExist"));
					}
				} else {
					textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.IncorrectData"));
				}
			}
		});
		
		jButtonRegister.setBounds(153, 213, 106, 23);
		contentPane.add(jButtonRegister);
		
		textArea = new JTextArea();
		textArea.setBounds(134, 241, 145, 22);
		contentPane.add(textArea);
	}
}
