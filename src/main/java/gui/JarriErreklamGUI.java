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
	public JarriErreklamGUI(User userJarri) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		Erreklamazioa errekJarri;
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(85, 101, 249, 67);
		contentPane.add(textArea);
		String sartutakoTxt = textArea.getText();
		
		JRadioButton leve = new JRadioButton("New radio button");
		leve.setBounds(29, 179, 111, 23);
		contentPane.add(leve);
		
		JRadioButton intermedio = new JRadioButton("New radio button");
		intermedio.setBounds(169, 179, 111, 23);
		contentPane.add(intermedio);
		
		JRadioButton grave = new JRadioButton("New radio button");
		grave.setBounds(308, 179, 111, 23);
		contentPane.add(grave);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(29, 68, 390, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(93, 29, 281, 14);
		contentPane.add(lblNewLabel);
		
		Eskaera eskSelect = (Eskaera) comboBox.getSelectedItem();
		
		if (userJarri instanceof Bidaiari) {
			leve.setVisible(false);
			intermedio.setVisible(false);
			grave.setVisible(false);
			List<Eskaera> eskList = facade.getEskaerakBidaiari((Bidaiari) userJarri);
			for (Eskaera esk : eskList) {
				if(esk.getEgoera().equals(EskaeraEgoera.FINISHED)) {
					comboBox.addItem(esk); 
				}	
			}
			
			 errekJarri = new Erreklamazioa(userJarri, eskSelect.getRide().getDriver(), eskSelect, sartutakoTxt, eskSelect.getPrez());
		}else {
			leve.setVisible(true);
			intermedio.setVisible(true);
			grave.setVisible(true);
			List<Ride> rideList = facade.getDriverRides((Driver) userJarri);
			for (Ride rid : rideList) {
				List<Eskaera> eskList = rid.getEskaerenList();
				for (Eskaera esk : eskList) {
					if(esk.getEgoera().equals(EskaeraEgoera.CONFIRMED)) {
						comboBox.addItem(esk); 
					}	
				}
			}
			if(leve.isSelected()) {
				 errekJarri = new Erreklamazioa(userJarri, eskSelect.getRide().getDriver(), eskSelect, sartutakoTxt, eskSelect.getPrez(), ErrekLarri.TXIKIA);
			}else if(intermedio.isSelected()) {
				 errekJarri = new Erreklamazioa(userJarri, eskSelect.getRide().getDriver(), eskSelect, sartutakoTxt, eskSelect.getPrez(), ErrekLarri.ERTAINA);
			}else {
				 errekJarri = new Erreklamazioa(userJarri, eskSelect.getRide().getDriver(), eskSelect, sartutakoTxt, eskSelect.getPrez(), ErrekLarri.HANDIA);
			}
			
		}
		JButton btnNewButton = new JButton("Boton");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.addErreklamazio(errekJarri);
			}
		});
		btnNewButton.setBounds(183, 216, 61, 23);
		contentPane.add(btnNewButton);
		
	}
}
