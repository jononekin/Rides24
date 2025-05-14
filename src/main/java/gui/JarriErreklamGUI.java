package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bidaiari;
import domain.Driver;
import domain.Erreklamazioa;
import domain.User;
import domain.Erreklamazioa.ErrekLarri;
import domain.Erreklamazioa.ErrekMota;
import domain.Eskaera;
import domain.Eskaera.EskaeraEgoera;
import domain.Ride;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class JarriErreklamGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JarriErreklamGUI(User userJarri, User userJaso, Eskaera eskaera) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		Erreklamazioa errekJarri;
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(82, 64, 249, 67);
		contentPane.add(textArea);
		String sartutakoTxt = textArea.getText();
		
		JRadioButton leve = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("AWC.Leve"));
		leve.setBounds(29, 179, 111, 23);
		contentPane.add(leve);
		
		JRadioButton intermedio = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("AWC.Mediano"));
		intermedio.setBounds(169, 179, 111, 23);
		contentPane.add(intermedio);
		
		JRadioButton grave = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("AWC.Grave"));
		grave.setBounds(308, 179, 111, 23);
		contentPane.add(grave);
		
		
		
		
		
		if (userJarri instanceof Bidaiari) {
			leve.setVisible(false);
			intermedio.setVisible(false);
			grave.setVisible(false);
		}else {
			leve.setVisible(true);
			intermedio.setVisible(true);
			grave.setVisible(true);
		}
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Erreklamazioa errekJarri;
				 
				if (userJarri instanceof Bidaiari) {
					facade.addErreklamazio(userJarri, userJaso, eskaera, sartutakoTxt, eskaera.getPrez(), null);
				}else {
					if(leve.isSelected()) {
						facade.addErreklamazio(userJarri, userJaso, eskaera, sartutakoTxt, eskaera.getPrez(), ErrekLarri.TXIKIA);
					}else if(intermedio.isSelected()) {
						facade.addErreklamazio(userJarri, userJaso, eskaera, sartutakoTxt, eskaera.getPrez(), ErrekLarri.ERTAINA);
						
					}else {
						facade.addErreklamazio(userJarri,userJaso, eskaera, sartutakoTxt, eskaera.getPrez(), ErrekLarri.HANDIA);
						
					}
					
				}
				
				dispose();
			}
		});
		btnNewButton.setBounds(169, 216, 89, 23);
		contentPane.add(btnNewButton);
		
	}
}