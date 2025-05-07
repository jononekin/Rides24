package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bidaiari;
import domain.Driver;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteUserGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jLabelMsg = new JLabel();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public DeleteUserGUI(User user) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jLabelMsg.setBounds(new Rectangle(66, 115, 305, 20));
		jLabelMsg.setForeground(Color.red);
		contentPane.add(jLabelMsg);
		
		JLabel sure = new JLabel("New label");
		sure.setBounds(179, 66, 49, 14);
		contentPane.add(sure);
		
		JButton yes = new JButton("New button");
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.ezabatuUser(user);
			}
		});
		yes.setBounds(289, 167, 89, 23);
		contentPane.add(yes);
		
		JButton cancel = new JButton("New button");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user instanceof Bidaiari) {
					JFrame a = new MainBidaiariGUI((Bidaiari) user);
					a.setVisible(true);
				}else {
					JFrame a = new MainGidariGUI((Driver) user);
					a.setVisible(true);
				}
				itxi(e);
			}
		});
		cancel.setBounds(55, 167, 89, 23);
		contentPane.add(cancel);
	}
	private void itxi(ActionEvent e) {
		this.setVisible(false);
	}
}
