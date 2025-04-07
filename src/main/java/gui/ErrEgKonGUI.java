package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bidaiari;
import domain.Car;
import domain.Eskaera;
import domain.Ride;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrEgKonGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JLabel jLabelMsg = new JLabel(); 

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ErrEgKon frame = new ErrEgKon();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public ErrEgKonGUI(Bidaiari bidaiari) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErrEgKon.Confirm"));
		btnNewButton.setVisible(false);
		
		JComboBox Erreserbak = new JComboBox();
		Erreserbak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eskaera eskaera = (Eskaera) Erreserbak.getSelectedItem();
				Calendar gaur = Calendar.getInstance();
				gaur.set(Calendar.HOUR_OF_DAY, 0);
				gaur.set(Calendar.MINUTE, 0);
				gaur.set(Calendar.SECOND, 0);
			    gaur.set(Calendar.MILLISECOND, 0);
				Calendar fechaRide = Calendar.getInstance();
				fechaRide.setTime(eskaera.getDate());
				fechaRide.set(Calendar.HOUR_OF_DAY, 0);
			    fechaRide.set(Calendar.MINUTE, 0);
			    fechaRide.set(Calendar.SECOND, 0);
			    fechaRide.set(Calendar.MILLISECOND, 0);
				if (gaur.equals(fechaRide)||gaur.after(fechaRide)) {
					btnNewButton.setVisible(true);
				}
			}
		});
		jLabelMsg.setBounds(new Rectangle(57, 154, 305, 20));
		jLabelMsg.setForeground(Color.red);
		
		this.getContentPane().add(jLabelMsg, null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jLabelMsg.setText("");
				BLFacade facade = MainGUI.getBusinessLogic();
				Eskaera eskaera = (Eskaera) Erreserbak.getSelectedItem();
				float prez = eskaera.getPrez();
				facade.ezabatuEskaera(eskaera);
				List<Ride> ridesList = facade.getAllRides();
				Ride ride = null;
				for (Ride r : ridesList) {
					if (r.getFrom().equals(eskaera.getFrom()) && r.getTo().equals(eskaera.getTo())
							&& r.getDate().equals(eskaera.getDate())) {
						ride = r;
						break;
					}
				}
				if (ride != null) {
					facade.diruaSartu(ride.getDriver(), prez);
					facade.addMovement(ride.getDriver().getEmail(), prez, "+", ride.getDriver());
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrEgKon.Accepted"));
				}
				
			}
		});

		Erreserbak.setBounds(10, 85, 416, 33);
		BLFacade facade = MainGUI.getBusinessLogic();
		List<Eskaera> eskaerak = facade.getAllEskaera();
		for (Eskaera esk : eskaerak) {
			if (esk.isBaieztatuta()) {
				Erreserbak.addItem(esk);
			}
		}

		/*
		 * List<Bidaiari> bidaiariList = facade.getAllBidaiari();
		 * JcomboBox.removeAllItems(); for (Bidaiari bidaiari : bidaiariList) { for
		 * (Eskaera eskaera : bidaiari.getEskaerak()) { comboBox.addItem(eskaera); } }
		 */
		contentPane.add(Erreserbak);

		JLabel Erreserbak_txt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErrEgKon.Title"));
		Erreserbak_txt.setBounds(82, 36, 282, 14);
		contentPane.add(Erreserbak_txt);

		btnNewButton.setBounds(57, 201, 292, 23);
		contentPane.add(btnNewButton);
	}
}
