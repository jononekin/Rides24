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
		
		this.setSize(495, 290);
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGidariGUI.Title"));
		jLabelSelectOption.setBounds(0, 0, 481, 84);
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		
		jButtonAcceptRide = new JButton();
		jButtonAcceptRide.setBounds(0, 168, 481, 84);
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
		jButtonCreateRide.setBounds(0, 84, 481, 84);
		contentPane.add(jButtonCreateRide);
		contentPane.add(jButtonAcceptRide);
		
		setContentPane(contentPane);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

	}
}
