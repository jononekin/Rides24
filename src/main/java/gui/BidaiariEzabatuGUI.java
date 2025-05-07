package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BidaiariEzabatuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jLabelMsg = new JLabel();

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public BidaiariEzabatuGUI() {
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(178, 85, 49, 14);
		jLabelMsg.setText("");
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(162, 150, 89, 23);
		contentPane.add(btnNewButton);
		
		jLabelMsg.setBounds(new Rectangle(56, 209, 305, 20));
		jLabelMsg.setForeground(Color.red);
		contentPane.add(jLabelMsg);
	}

}
