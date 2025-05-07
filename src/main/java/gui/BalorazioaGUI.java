package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Balorazio;
import domain.Bidaiari;
import domain.Driver;
import domain.User;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class BalorazioaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jLabelMsg = new JLabel(); 
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public BalorazioaGUI(User userJarri, User userJaso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton a1 = new JRadioButton("1");
		a1.setBounds(53, 94, 53, 23);
		contentPane.add(a1);
		
		JRadioButton a2 = new JRadioButton("2");
		a2.setBounds(118, 94, 53, 23);
		contentPane.add(a2);
		
		JLabel Baloratu = new JLabel("New label");
		Baloratu.setBounds(132, 48, 115, 14);
		contentPane.add(Baloratu);
		
		JRadioButton a3 = new JRadioButton("3");
		a3.setBounds(183, 94, 53, 23);
		contentPane.add(a3);
		
		JRadioButton a4 = new JRadioButton("4");
		a4.setBounds(238, 94, 53, 23);
		contentPane.add(a4);
		
		JRadioButton a5 = new JRadioButton("5");
		a5.setBounds(293, 94, 53, 23);
		contentPane.add(a5);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(90, 163, 179, 50);
		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(132, 138, 49, 14);
		contentPane.add(lblNewLabel);
		
		jLabelMsg.setBounds(new Rectangle(34, 73, 305, 20));
		jLabelMsg.setForeground(Color.red);
		
		this.getContentPane().add(jLabelMsg, null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				String deskribapena = textArea.getText();
				int nota = 0;
				if(a1.isSelected()) {
					nota = 1;
				}else if(a2.isSelected()){
					nota = 2;
				}else if(a3.isSelected()){
					nota = 3;
				}else if(a4.isSelected()){
					nota = 4;
				}else {
					nota=5;
				}
				if(!a1.isSelected() || !a2.isSelected() || !a3.isSelected() || !a4.isSelected()|| !a5.isSelected()) {
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error"));
				}else {
					Balorazio balorazio = new Balorazio(userJarri, userJaso, deskribapena, nota);
					if (userJarri instanceof Driver) {
						facade.addBalorazioa(balorazio);
						JFrame a = new MainGidariGUI((Driver) userJarri);
						a.setVisible(true);
					}else {
						JFrame a = new MainBidaiariGUI((Bidaiari) userJarri);
						a.setVisible(true);
					}	
				}
				
				
			}
		});
		btnNewButton.setBounds(133, 229, 89, 23);
		contentPane.add(btnNewButton);
	}
}
