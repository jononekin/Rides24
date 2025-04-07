package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Movement;
import domain.Ride;
import domain.User;

import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class MugimenduakGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MugimenduakGUI frame = new MugimenduakGUI();
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
	public MugimenduakGUI(User user) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(177, 233, 89, 23);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> movListView = new JList<>(listModel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 47, 305, 78);
		scrollPane.setViewportView(movListView);
		contentPane.add(scrollPane);
		
		movListView.setBounds(71, 47, 305, 175);
		
		
		
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		List<Movement> movList = facade.getUserMugimenduak(user);
		
		

		for (Movement mov : movList) {
		    listModel.addElement(mov.toString()); 
		}

		
	}
}
