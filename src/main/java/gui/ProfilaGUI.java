package gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Balorazio;
import domain.Movement;
import domain.User;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProfilaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ProfilaGUI(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> movListView = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 47, 305, 139);
		scrollPane.setViewportView(movListView);
		contentPane.add(scrollPane);
		movListView.setBounds(71, 47, 305, 175);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(172, 212, 89, 23);
		contentPane.add(btnNewButton);
		BLFacade facade = MainGUI.getBusinessLogic();
		List<Balorazio> movList = facade.getUserBalorazioa(user);
		for (Balorazio mov : movList) {
		    listModel.addElement(mov.toString()); 
		}
		
		
	}

}
