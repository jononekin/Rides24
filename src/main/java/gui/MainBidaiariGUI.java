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
		
		this.setSize(495, 290);
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jLabelSelectOption.setBounds(0, 0, 481, 84);
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		
		jButtonWallet = new JButton();
		jButtonWallet.setBounds(0, 76, 481, 60);
		jButtonWallet.setText(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.Wallet"));
		jButtonWallet.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new DiruaSartuGUI(bidaiari);
				a.setVisible(true);
			}
		});
		
		jButtonRequestRide = new JButton();
		jButtonRequestRide.setBounds(0, 135, 481, 60);
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
		
		/*JButton ErreserbenEgoeraKon = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		ErreserbenEgoeraKon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a= new ErrEgKonGUI(bidaiari);
				a.setVisible(true);
			}
		});
		ErreserbenEgoeraKon.setBounds(0, 193, 481, 60);
		contentPane.add(ErreserbenEgoeraKon);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.Title"));
		*/
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

	}
}
