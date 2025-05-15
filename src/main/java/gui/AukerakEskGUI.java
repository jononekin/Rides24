package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bidaiari;
import domain.Car;
import domain.Driver;
import domain.Eskaera;
import domain.Eskaera.EskaeraEgoera;
import domain.Ride;
import exceptions.NotEnoughMoneyException;
import exceptions.NotEnoughPlacesException;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class AukerakEskGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jLabelMsg = new JLabel();
	private JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { OnartuGUI frame = new OnartuGUI();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public AukerakEskGUI(Ride ride) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Title"));
		setBounds(100, 100, 610, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox eskaerak = new JComboBox();
		eskaerak.setBounds(10, 78, 576, 22);
		BLFacade facade = MainGUI.getBusinessLogic();
		//List<Bidaiari> bidaiariList = facade.getAllBidaiari();
		eskaerak.removeAllItems();
	
		List<Eskaera> eskList = facade.getEskaerakRide(ride);
		for (Eskaera esk : eskList) {
			if(esk.getEgoera()==EskaeraEgoera.FINISHED) {
				eskaerak.addItem(esk);
			}
		}
		
		contentPane.add(eskaerak);

		jLabelMsg.setBounds(new Rectangle(147, 137, 305, 20));
		jLabelMsg.setForeground(Color.red);
		contentPane.add(jLabelMsg);

		JLabel lbl_Title = new JLabel("k"/*ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Title")*/);
		lbl_Title.setBounds(49, 35, 335, 32);
		contentPane.add(lbl_Title);

		JButton erreklamatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Accept"));
		erreklamatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eskaera selectedEskaera = (Eskaera) eskaerak.getSelectedItem();
				JFrame a = new JarriErreklamGUI(ride.getDriver(), selectedEskaera.getBidaiari() ,selectedEskaera);
				a.setVisible(true);
			 
			}
		});
		erreklamatu.setBounds(156, 180, 89, 23);
		contentPane.add(erreklamatu);
		
		JButton baloratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Reject"));
		baloratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eskaera selectedEskaera = (Eskaera) eskaerak.getSelectedItem();
				JFrame a = new BalorazioaGUI(ride.getDriver(), selectedEskaera.getBidaiari() , selectedEskaera);
				a.setVisible(true);
			}
		});
		baloratu.setBounds(363, 180, 89, 23);
		contentPane.add(baloratu);
		
		JButton itxi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.See")); //$NON-NLS-1$ //$NON-NLS-2$
		itxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		itxi.setBounds(255, 214, 89, 23);
		contentPane.add(itxi);
	}
}