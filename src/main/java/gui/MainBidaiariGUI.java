package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bidaiari;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import domain.Rider;

public class MainBidaiariGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    //private Rider rider;
	protected JLabel jLabelSelectOption;
	private JButton jButtonWallet = null;
	private JButton jButtonRequestRide = null;

    private static BLFacade appFacadeInterface;
    private JButton mugimendu;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}

	public MainBidaiariGUI(Bidaiari bidaiari) {
		super();
		//this.rider = r;
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.Title"));
	    setBounds(100, 100, 495, 290);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle(bidaiari.getEmail());
		this.setSize(495, 617);
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jLabelSelectOption.setBounds(0, 11, 481, 60);
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		
		jButtonWallet = new JButton();
		jButtonWallet.setBounds(0, 68, 481, 60);
		jButtonWallet.setText(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.Wallet"));
		jButtonWallet.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new DiruaSartuGUI(bidaiari);
				a.setVisible(true);
			}
		});
		
		jButtonRequestRide = new JButton();
		jButtonRequestRide.setBounds(0, 128, 481, 60);
		jButtonRequestRide.setText(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.RequestRide"));
		jButtonRequestRide.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new RequestRideGUI(bidaiari);
				a.setVisible(true);
			}
		});
	    
	    contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.add(jLabelSelectOption);
		contentPane.add(jButtonWallet);
		contentPane.add(jButtonRequestRide);
		
		setContentPane(contentPane);
		
		JButton ErrEgKon = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErrEgKon.Title")); //$NON-NLS-1$ //$NON-NLS-2$
		ErrEgKon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ErrEgKonGUI(bidaiari);
				a.setVisible(true);
			}
		});
		ErrEgKon.setBounds(0, 188, 481, 60);
		contentPane.add(ErrEgKon);
		
		mugimendu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.SeeMovements")); //$NON-NLS-1$ //$NON-NLS-2$
		mugimendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new MugimenduakGUI(bidaiari);
				a.setVisible(true);
			}
		});
		mugimendu.setBounds(0, 248, 481, 60);
		contentPane.add(mugimendu);
		
		JButton deletUser = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.DeleteUser"));
		deletUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new DeleteUserGUI(bidaiari);   
				a.setVisible(true);
			}
		});
		deletUser.setBounds(0, 308, 481, 60);
		contentPane.add(deletUser);
		
		JButton notifikazioak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Notifications") +" " + bidaiari.getAlertak().size());
		notifikazioak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new AlertaGUI(bidaiari);
				a.setVisible(true);
			}
		});
		notifikazioak.setBounds(0, 368, 481, 60);
		contentPane.add(notifikazioak);
		
		JButton kontsultatuErrek = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Kontsultatu"));
		kontsultatuErrek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new KontsultatuErrekGUI(bidaiari);
				a.setVisible(true);
			}
		});
		kontsultatuErrek.setBounds(241, 428, 240, 60);
		contentPane.add(kontsultatuErrek);
		
		JButton nireBalor = new JButton(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.See"));
		nireBalor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ProfilaGUI(bidaiari);
				a.setVisible(true);
			}
		});
		nireBalor.setBounds(0, 488, 481, 60);
		contentPane.add(nireBalor);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

	}
}
