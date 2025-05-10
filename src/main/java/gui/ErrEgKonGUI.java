package gui;

import java.awt.Color;
import domain.Eskaera.EskaeraEgoera;
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
import domain.Driver;
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
		setBounds(100, 100, 604, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton konfirmatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErrEgKon.Confirm"));
		konfirmatu.setVisible(false);
		JButton kantzelatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErrEgKonGUI.Cancel")); 
		kantzelatu.setVisible(false);
		
		JComboBox Erreserbak = new JComboBox();
		Erreserbak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eskaera selectedEskaera = (Eskaera) Erreserbak.getSelectedItem();
				Calendar gaur = Calendar.getInstance();
				gaur.set(Calendar.HOUR_OF_DAY, 0);
				gaur.set(Calendar.MINUTE, 0);
				gaur.set(Calendar.SECOND, 0);
			    gaur.set(Calendar.MILLISECOND, 0);
				Calendar fechaRide = Calendar.getInstance();
				fechaRide.setTime(selectedEskaera.getRide().getDate());
				fechaRide.set(Calendar.HOUR_OF_DAY, 0);
			    fechaRide.set(Calendar.MINUTE, 0);
			    fechaRide.set(Calendar.SECOND, 0);
			    fechaRide.set(Calendar.MILLISECOND, 0);
				if (gaur.after(fechaRide) && (selectedEskaera.getEgoera() == EskaeraEgoera.FINISHED)) { //gaur.equals(fechaRide)||
					konfirmatu.setVisible(true);
				}else {
					konfirmatu.setVisible(false);
				}
				if ((selectedEskaera.getEgoera() == EskaeraEgoera.PENDING) || (selectedEskaera.getEgoera() == EskaeraEgoera.ACCEPTED)) {
					kantzelatu.setVisible(true);
				}else {
					kantzelatu.setVisible(false);
				}
			}
		});
		jLabelMsg.setBounds(new Rectangle(57, 154, 305, 20));
		jLabelMsg.setForeground(Color.red);
		
		this.getContentPane().add(jLabelMsg, null);
		konfirmatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jLabelMsg.setText("");
				BLFacade facade = MainGUI.getBusinessLogic();
				Eskaera selectedEskaera = (Eskaera) Erreserbak.getSelectedItem();
				facade.konfirmatuEskaera(selectedEskaera);
				JFrame a = new BalorazioaGUI(bidaiari, selectedEskaera.getRide().getDriver(), selectedEskaera.getRide());
				a.setVisible(true);
			}
		});

		Erreserbak.setBounds(10, 85, 570, 28);
		BLFacade facade = MainGUI.getBusinessLogic();
		List<Eskaera> eskaerak = facade.getEskaerakBidaiari(bidaiari);
		for (Eskaera esk : eskaerak) {
				Erreserbak.addItem(esk);
		}

		/*
		 * List<Bidaiari> bidaiariList = facade.getAllBidaiari();
		 * JcomboBox.removeAllItems(); for (Bidaiari bidaiari : bidaiariList) { for
		 * (Eskaera eskaera : bidaiari.getEskaerak()) { comboBox.addItem(eskaera); } }
		 */
		contentPane.add(Erreserbak);

		JLabel Erreserbak_txt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErrEgKon.Title"));
		Erreserbak_txt.setBounds(140, 36, 282, 14);
		contentPane.add(Erreserbak_txt);

		konfirmatu.setBounds(163, 185, 292, 23);
		contentPane.add(konfirmatu);
		
		kantzelatu.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			BLFacade facade = MainGUI.getBusinessLogic();
			Eskaera selectedEskaera = (Eskaera) Erreserbak.getSelectedItem();
			facade.kantzelatuEskaera(selectedEskaera);
		}
		});
		kantzelatu.setBounds(163, 229, 292, 23);
		contentPane.add(kantzelatu);
	}
}
