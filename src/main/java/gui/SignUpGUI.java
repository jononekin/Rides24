package gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import dataAccess.DataAccess;
import domain.Driver;
import domain.Bidaiari;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JLabel jLabelMsg = new JLabel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
		
		

	}*/

	/**
	 * Create the frame.
	 */
	public SignUpGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.Title"));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 300);
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
		textField_NameSur.setText("a");
		contentPane.add(textField_NameSur);
		textField_NameSur.setColumns(10);
		
		JLabel lbl_IDNum = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.IDNum"));
		lbl_IDNum.setBounds(50, 74, 106, 20);
		contentPane.add(lbl_IDNum);
		
		textField = new JTextField();
		textField.setBounds(194, 74, 96, 20);
		textField.setText("1");
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lbl_Tlf = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.Tlf"));
		lbl_Tlf.setBounds(50, 106, 106, 20);
		contentPane.add(lbl_Tlf);
		
		textField_Tlf = new JTextField();
		textField_Tlf.setBounds(194, 106, 96, 20);
		textField_Tlf.setText("1");
		contentPane.add(textField_Tlf);
		textField_Tlf.setColumns(10);
		
		JLabel lbl_Email = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.Email"));
		lbl_Email.setBounds(50, 138, 106, 20);
		contentPane.add(lbl_Email);
		
		textField_Email = new JTextField();
		textField_Email.setBounds(194, 138, 96, 20);
		textField_Email.setText("@gmail.com");
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
		
		jLabelMsg.setBounds(new Rectangle(300, 40, 325, 86));
		jLabelMsg.setForeground(Color.red);
		this.getContentPane().add(jLabelMsg, null);
		
		JButton btn_SignUp =new JButton(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.BtnSignUp"));
		btn_SignUp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				jLabelMsg.setText("");
				BLFacade facade = MainGUI.getBusinessLogic();
				String nameSur = textField_NameSur.getText().trim();
				String NAN = textField.getText().trim();
				String tlf = textField_Tlf.getText().trim();
				String email = textField_Email.getText().trim();
				String password = Password.getText().trim();
				
				if (nameSur.isEmpty() || NAN.isEmpty() || tlf.isEmpty() || email.isEmpty() || password.isEmpty()) {
		            jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error.FillAllGaps"));
		            return;
		        }
				
				if (!email.contains("@") || !email.contains(".")) {
					 jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error.EmailFormat"));
			         return;
			    }
				 
				if (!tlf.matches("\\d+")) {
					 jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error.Tlf"));
			         return;
			    }
				boolean isOndo=true;
				try {
					if (rdbtn_Driver.isSelected()) {
				        Driver driver = new Driver(nameSur, password, email, NAN);
				        driver.setNanZbk(NAN);
				        driver.setPasahitza(password);
				        driver.setTlf(tlf);
				        isOndo=facade.storeUser(driver);
				        if(isOndo) {
				        	System.out.println("Driver registrado correctamente.");
				        	JFrame d = new MainGidariGUI(driver);
				        	d.setVisible(true);
				        	((JFrame) SwingUtilities.getWindowAncestor(btn_SignUp)).dispose(); //Botoia pultsatzean lehio hori itxiko du
				        } else {
				        	jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error.EmailFormat"));
				        }
				    } else if (rdbtn_Passenger.isSelected()) {
				        Bidaiari bidaiari = new Bidaiari(nameSur, password, email, NAN);
				        bidaiari.setNanZbk(NAN);
				        bidaiari.setPasahitza(password);
				        bidaiari.setTlf(tlf);
				        bidaiari.setDirua(0);
				        isOndo=facade.storeUser(bidaiari);
				        if (isOndo){
				        	System.out.println("Rider registrado correctamente.");
				        	JFrame r = new MainBidaiariGUI(bidaiari);
				        	r.setVisible(true);
				        	((JFrame) SwingUtilities.getWindowAncestor(btn_SignUp)).dispose(); //Botoia pultsatzean lehio hori itxiko du
				        } else {
				        	jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error.EmailFormat"));
				        }
				    } else {
				        jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error.Click"));
				    }
		            
		        } catch (Exception ex) {
		            jLabelMsg.setText("Error!");
		        }
			
			}
		});
		
		btn_SignUp.setBounds(175, 230, 89, 23);
		contentPane.add(btn_SignUp);
	}
}

