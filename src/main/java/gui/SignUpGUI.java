package gui;

import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

public class SignUpGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane = null;
	private JButton jButtomSingUp=null;
	private JRadioButton rdbtnDriver=null;
	private JRadioButton rdbtnPassenger=null;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_NameSur;
	private JTextField textField;
	private JTextField textField_Tlf;
	private JTextField textField_Email;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpGUI frame = new SignUpGUI();
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
	public SignUpGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.Title"));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_Title = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.Title"));
		lbl_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title.setBounds(175, 11, 103, 20);
		lbl_Title.setFont(new Font("SansSerif", Font.BOLD, 15));
		contentPane.add(lbl_Title);
		
		JLabel lbl_NameSur =  new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.NameSur"));
		lbl_NameSur.setBounds(50, 42, 106, 20);
		contentPane.add(lbl_NameSur);
		
		textField_NameSur = new JTextField();
		textField_NameSur.setBounds(194, 42, 96, 20);
		contentPane.add(textField_NameSur);
		textField_NameSur.setColumns(10);
		
		JLabel lbl_IDNum = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.IDNum"));
		lbl_IDNum.setBounds(50, 74, 106, 20);
		contentPane.add(lbl_IDNum);
		
		textField = new JTextField();
		textField.setBounds(194, 74, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lbl_Tlf = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.Tlf"));
		lbl_Tlf.setBounds(50, 106, 106, 20);
		contentPane.add(lbl_Tlf);
		
		textField_Tlf = new JTextField();
		textField_Tlf.setBounds(194, 106, 96, 20);
		contentPane.add(textField_Tlf);
		textField_Tlf.setColumns(10);
		
		JLabel lbl_Email = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.Email"));
		lbl_Email.setBounds(50, 138, 106, 20);
		contentPane.add(lbl_Email);
		
		textField_Email = new JTextField();
		textField_Email.setBounds(194, 138, 96, 20);
		contentPane.add(textField_Email);
		textField_Email.setColumns(10);
		
		JLabel lbl_Password = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.Password"));
		lbl_Password.setBounds(50, 170, 106, 20);
		contentPane.add(lbl_Password);
		
		JFormattedTextField Password = new JFormattedTextField();
		Password.setBounds(194, 170, 96, 20);
		contentPane.add(Password);
		
		JLabel lbl_Text = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.Text"));
		lbl_Text.setBounds(50, 202, 106, 20);
		contentPane.add(lbl_Text);
		
		JRadioButton rdbtn_Driver = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.BtnDriver"));
		rdbtn_Driver.setBounds(194, 202, 111, 23);
		buttonGroup_1.add(rdbtn_Driver);
		contentPane.add(rdbtn_Driver);
		
		JRadioButton rdbtn_Passenger = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.BtnPassenger"));
		rdbtn_Passenger.setBounds(305, 202, 111, 23);
		buttonGroup_1.add(rdbtn_Passenger);
		contentPane.add(rdbtn_Passenger);
		
		JButton btn_SignUp =new JButton(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.BtnSignUp"));
		btn_SignUp.setBounds(175, 230, 89, 23);
		contentPane.add(btn_SignUp);
	}
}
