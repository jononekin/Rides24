package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bidaiari;
import domain.Driver;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane = null;
	private JTextField textField_Email;
	private JTextField textFiel_Password;
	private JButton btn_LogIn;
	private JLabel jLabelMsg = new JLabel();
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.Title"));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_Title = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.Title"));
		lbl_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title.setBounds(147, 11, 103, 20);
		lbl_Title.setFont(new Font("SansSerif", Font.BOLD, 15));
		contentPane.add(lbl_Title);
		
		JLabel lbl_Email = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.Email"));
		lbl_Email.setBounds(50, 95, 106, 20);
		contentPane.add(lbl_Email);
		
		textField_Email = new JTextField();
		textField_Email.setText("d1@gmail.com");
		textField_Email.setBounds(194, 95, 96, 20);
		contentPane.add(textField_Email);
		textField_Email.setColumns(10);
		
		JLabel lbl_Password = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SignUpGUI.Password"));
		lbl_Password.setBounds(50, 135, 106, 20);
		contentPane.add(lbl_Password);
		
		JFormattedTextField Password = new JFormattedTextField();
		Password.setBounds(194, 135, 96, 20);
		contentPane.add(Password);
		
		jLabelMsg.setBounds(new Rectangle(300, 40, 325, 86));
		jLabelMsg.setForeground(Color.red);
		this.getContentPane().add(jLabelMsg, null);
		
		JButton btn_LogIn =new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.BtnLogin"));
		btn_LogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jLabelMsg.setText("");
				BLFacade facade = MainGUI.getBusinessLogic();
				String email = textField_Email.getText().trim();
				String password = Password.getText().trim();
				
				if (email.isEmpty() || password.isEmpty()) {
		            jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error.FillAllGaps"));
		            return;
		        }
				
				User u = facade.isRegistered(email, password);
				if(u == null) {//null itzuli du ez erregistratuta edo pasahitzak ez badu koinziditzen
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error.Login"));
				}else {
					if(u instanceof Bidaiari) {
						JFrame d = new MainBidaiariGUI((Bidaiari)u);
			        	d.setVisible(true);
			        	((JFrame) SwingUtilities.getWindowAncestor(btn_LogIn)).dispose();
					}else {
						JFrame d = new MainGidariGUI((Driver)u);
			        	d.setVisible(true);
			        	((JFrame) SwingUtilities.getWindowAncestor(btn_LogIn)).dispose();
					}
				}
				
				
			}
		});
		btn_LogIn.setBounds(147, 200, 89, 23);
		contentPane.add(btn_LogIn);
		
		
		
		
	}
}
