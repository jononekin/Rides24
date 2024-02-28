package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Traveler;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class TravelerMainGUI extends JFrame {

	private JPanel contentPane;
	private JLabel JlabelAukeratu  = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.Aukeratu"));
	private JButton rdbtDiruaSartuAtera  = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.DiruaSartuAtera"));
	private JButton rdbtBidaiaBilatu  = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.BidaiaBilatu"));
	
	private static Traveler traveler;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TravelerMainGUI frame = new TravelerMainGUI(traveler);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TravelerMainGUI(Traveler t) {
		this.traveler = t;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		JlabelAukeratu.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(JlabelAukeratu);
		rdbtDiruaSartuAtera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiruaSartuGUI frame = new DiruaSartuGUI();
				frame.setVisible(true);
			}
		});
		contentPane.add(rdbtDiruaSartuAtera);
		rdbtBidaiaBilatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindBookGUI frame = new FindBookGUI(t);
				frame.setVisible(true);
			}
		});
		contentPane.add(rdbtBidaiaBilatu);
	}

}
