package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private JButton rdbtErreserbaEgin  = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.ErreserbaEgin"));
	private JButton rdbtBidaiaBilatu  = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TravelerMainGUI.BidaiaBilatu"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TravelerMainGUI frame = new TravelerMainGUI();
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
	public TravelerMainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		JlabelAukeratu.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(JlabelAukeratu);
		rdbtDiruaSartuAtera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(rdbtDiruaSartuAtera);
		contentPane.add(rdbtErreserbaEgin);
		contentPane.add(rdbtBidaiaBilatu);
	}

}
