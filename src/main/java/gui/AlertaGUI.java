package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Alerta;
import domain.Bidaiari;
import domain.Driver;
import domain.Movement;
import domain.User;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class AlertaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AlertaGUI(User user) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.ezabatuAlertakUser(user);
				dispose();
			}
		});
		btnNewButton.setBounds(177, 233, 89, 23);
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> aleListView = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 47, 305, 78);
		scrollPane.setViewportView(aleListView);
		contentPane.add(scrollPane);
		aleListView.setBounds(71, 47, 305, 175);
		BLFacade facade = MainGUI.getBusinessLogic();
		List<Alerta> alertList = facade.getUserAlertak(user);
		for (Alerta alert : alertList) {
		    listModel.addElement(alert.toString()); 
		}
		
	}
}
