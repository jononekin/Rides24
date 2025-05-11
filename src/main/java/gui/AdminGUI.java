package gui;

import java.awt.EventQueue;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Erreklamazioa;
import domain.Erreklamazioa.ErrekMota;
import domain.Movement;
import domain.User;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AdminGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AdminGUI(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(25, 93, 370, 22);
		contentPane.add(comboBox);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		JButton reject = new JButton(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Reject"));
		reject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.rejectErrekAdmin((Erreklamazioa) comboBox.getSelectedItem());
			}
		});
		reject.setBounds(37, 214, 89, 23);
		contentPane.add(reject);
		
		JButton accept = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.acceptErrek((Erreklamazioa) comboBox.getSelectedItem());
			}
		});
		accept.setBounds(173, 214, 89, 23);
		contentPane.add(accept);
		
		JButton acceptChange = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AWC.Title"));
		acceptChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new AcceptWhithChangeGUI((Erreklamazioa) comboBox.getSelectedItem());
				a.setVisible(true);
			}
		});
		acceptChange.setBounds(309, 214, 89, 23);
		contentPane.add(acceptChange);
		
		List<Erreklamazioa> errekList = facade.getUserErrek(user);
		for (Erreklamazioa errek : errekList) {
			if(errek.getMota().equals(ErrekMota.ADMIN)) {
				comboBox.addItem(errek); 
			}	
		}
		
		
		
	}
}
