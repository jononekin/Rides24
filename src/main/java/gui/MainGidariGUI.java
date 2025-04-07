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
import domain.Driver;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGidariGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JLabel jLabelSelectOption;
	private JButton jButtonAcceptRide = null;

    private static BLFacade appFacadeInterface;
    private JButton jButtonBidaiaKantzel;
    private JButton jButtonAddCar;
    private JButton jButtonMugimenduak;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}

	public MainGidariGUI(Driver driver) {
		super();
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGidariGUI.Title"));
	    setBounds(100, 100, 495, 290);
		
		this.setSize(495, 415);
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGidariGUI.Title"));
		jLabelSelectOption.setBounds(0, 11, 481, 60);
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		
		jButtonAcceptRide = new JButton();
		jButtonAcceptRide.setBounds(0, 128, 481, 60);
		jButtonAcceptRide.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGidariGUI.AcceptRide"));
		jButtonAcceptRide.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new OnartuGUI(driver);
				a.setVisible(true);
			}
		});
	    
	    contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.add(jLabelSelectOption);
		
		JButton jButtonCreateRide = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.CreateRide")); //$NON-NLS-1$ //$NON-NLS-2$
		jButtonCreateRide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new CreateRideGUI(driver);
				a.setVisible(true);
			}
		});
		jButtonCreateRide.setBounds(0, 68, 481, 60);
		contentPane.add(jButtonCreateRide);
		contentPane.add(jButtonAcceptRide);
		
		setContentPane(contentPane);
		
		jButtonBidaiaKantzel = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGidariGUI.RemoveRide")); //$NON-NLS-1$ //$NON-NLS-2$
		jButtonBidaiaKantzel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new KantzelatuGUI(driver);
				a.setVisible(true);
			}
		});
		jButtonBidaiaKantzel.setBounds(0, 188, 481, 60);
		contentPane.add(jButtonBidaiaKantzel);
		
		jButtonAddCar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGidariGUI.AddCar")); //$NON-NLS-1$ //$NON-NLS-2$
		jButtonAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new AddCarGUI(driver);
				a.setVisible(true);
			}
		});
		jButtonAddCar.setBounds(0, 248, 481, 60);
		contentPane.add(jButtonAddCar);
		
		jButtonMugimenduak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGidariGUI.Movement")); //$NON-NLS-1$ //$NON-NLS-2$
		jButtonMugimenduak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new MugimenduakGUI(driver);
				a.setVisible(true);
			}
		});
		jButtonMugimenduak.setBounds(0, 308, 481, 60);
		contentPane.add(jButtonMugimenduak);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

	}
}
