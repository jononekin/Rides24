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
import domain.Eskaera;
import domain.Eskaera.EskaeraEgoera;
import domain.Ride;
import domain.User;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

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
	public BalorazioaGUI(User userJarri, User userJaso, Ride ride) {
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
		
		JLabel Baloratu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Baloratu.Baloratu"));
		Baloratu.setBounds(132, 33, 115, 14);
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
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Baloratu.Idatzi"));
		lblNewLabel.setBounds(90, 138, 179, 14);
		contentPane.add(lblNewLabel);
		
		jLabelMsg.setBounds(new Rectangle(41, 118, 305, 20));
		jLabelMsg.setForeground(Color.red);
		
		this.getContentPane().add(jLabelMsg, null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(36, 58, 310, 22);
		contentPane.add(comboBox);
		if(userJarri instanceof Driver) {
			comboBox.setVisible(true);
		}else{
			comboBox.setVisible(false);
		}
		
		
		
		JButton baloratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Baloratu.Baloratu"));
		baloratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Eskaera> eskList = ride.getEskaerenList();
				for(Eskaera eska:eskList) {
					if(eska.getEgoera() != EskaeraEgoera.VALUED) {
						comboBox.addItem(eska);
					}
					
				}
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
				if(!a1.isSelected() || !a2.isSelected() || !a3.isSelected() || !a4.isSelected()|| !a5.isSelected() || (comboBox.getSelectedItem() == null)) {                         
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error"));
				}else {
					Balorazio balorazio = new Balorazio(userJarri, userJaso, deskribapena, nota, (Eskaera) comboBox.getSelectedItem());
					if (userJarri instanceof Driver) {
						facade.addBalorazioa(balorazio);
					}else {
						facade.addBalorazioa(balorazio);
						JFrame a = new MainBidaiariGUI((Bidaiari) userJarri);
						a.setVisible(true);
					}	
				}
				
				
			}
		});
		baloratu.setBounds(257, 229, 89, 23);
		contentPane.add(baloratu);
		
		JButton itxi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		itxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		itxi.setBounds(53, 229, 89, 23);
		contentPane.add(itxi);
		
		
		
		
		
	}
}
