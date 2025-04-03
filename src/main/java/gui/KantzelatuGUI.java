package gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bidaiari;
import domain.Eskaera;
import domain.Ride;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KantzelatuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KantzelatuGUI frame = new KantzelatuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	

	/**
	 * Create the frame.
	 */
	public KantzelatuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(82, 115, 262, 22);
		contentPane.add(comboBox);
		BLFacade facade = MainGUI.getBusinessLogic();
		List<Ride> rideList = facade.getAllRides();
		comboBox.removeAllItems();
		for (Ride ride : rideList) {
		   comboBox.addItem(ride);
		}
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				boolean ondo = facade.ezabatuRide((Ride)comboBox.getSelectedItem());
				
			}
		});
		btnNewButton.setBounds(170, 185, 89, 23);
		contentPane.add(btnNewButton);
	}
}
