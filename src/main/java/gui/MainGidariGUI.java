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
	    setTitle(driver.getEmail());
		this.setSize(495, 649);
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
		
		
		JButton deletUser = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.DeleteUser"));
		deletUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new DeleteUserGUI(driver);
				a.setVisible(true);
			}
		});
		deletUser.setBounds(0, 368, 481, 60);
		contentPane.add(deletUser);
		
		JButton notifikazioak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Notifications") +" " /*+ driver.getAlertak().size()*/);
		notifikazioak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("in aurretik lo de visible ta new");
					JFrame a = new AlertaGUI(driver);
					System.out.println(" new in ostian");
					a.setVisible(true);
				}catch(Exception ex) {
					System.err.println("Error al llamar a AlertaGUI:");
		            ex.printStackTrace();
				}
			}
		});
		notifikazioak.setBounds(0, 428, 481, 60);
		contentPane.add(notifikazioak);
		
		JButton jarriErrek = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MakeReclamation"));
		jarriErrek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new JarriErreklamGUI(driver);
				a.setVisible(true);
			}
		});
		jarriErrek.setBounds(0, 488, 240, 60);
		contentPane.add(jarriErrek);
		
		JButton kontsultatuErrek = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Kontsultatu"));
		kontsultatuErrek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new KontsultatuErrekGUI(driver);
				a.setVisible(true);
			}
		});
		kontsultatuErrek.setBounds(241, 488, 240, 60);
		contentPane.add(kontsultatuErrek);
		
		JButton nireBalor = new JButton(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.See"));
		nireBalor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ProfilaGUI(driver);
				a.setVisible(true);
			}
		});
		nireBalor.setBounds(0, 548, 481, 60);
		contentPane.add(nireBalor);
		
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
