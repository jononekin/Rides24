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
import java.util.ResourceBundle;
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
		
		JLabel sure = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Delete.USure"));
		sure.setBounds(66, 66, 305, 14);
		contentPane.add(sure);
		
		JButton yes = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Delete.Yes"));
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.ezabatuUser(user);
			}
		});
		yes.setBounds(289, 167, 89, 23);
		contentPane.add(yes);
		
		JButton cancel = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Delete.No"));
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
