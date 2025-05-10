package gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bidaiari;
import domain.Driver;
import domain.Erreklamazioa;
import domain.Eskaera;
import domain.Movement;
import domain.User;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KontsultatuErrekGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public KontsultatuErrekGUI(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox jasoDituzunak = new JComboBox();
		jasoDituzunak.setBounds(243, 91, 215, 22);
		contentPane.add(jasoDituzunak);
		BLFacade facade = MainGUI.getBusinessLogic();
		List<Erreklamazioa> errekList = facade.getUserErrek(user);
		for (Erreklamazioa errek : errekList) {
			jasoDituzunak.addItem(errek); 
		}
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> eginDituzunak = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 226, 118);
		scrollPane.setViewportView(eginDituzunak);
		contentPane.add(scrollPane);
		eginDituzunak.setBounds(71, 47, 305, 175);
		List<Erreklamazioa> errekList1 = facade.getAllErrek();
		for (Erreklamazioa errek : errekList1) {
			if(errek.getErrekJarri().equals(user)) {
				listModel.addElement(errek.toString());
			}
		     
		}
		JButton Acept = new JButton("New button");
		Acept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Erreklamazioa selectRk = (Erreklamazioa) jasoDituzunak.getSelectedItem();
				facade.acceptErrek(selectRk);
			}
		});
		Acept.setBounds(369, 136, 89, 23);
		contentPane.add(Acept);
		
		JButton reject = new JButton("New button");
		reject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Erreklamazioa selectRk = (Erreklamazioa) jasoDituzunak.getSelectedItem();
				facade.rejectErrekUser(selectRk);
			}
		});
		reject.setBounds(243, 136, 89, 23);
		contentPane.add(reject);
		
		JButton close = new JButton("New button");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.setBounds(201, 210, 89, 23);
		contentPane.add(close);
		
	}
}
