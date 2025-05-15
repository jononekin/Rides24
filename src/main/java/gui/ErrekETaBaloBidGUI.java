package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Eskaera;
import domain.User;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrekETaBaloBidGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	
	
	
	

	/**
	 * Create the frame.
	 */
	public ErrekETaBaloBidGUI(User jarri, User jaso, Eskaera eskera) {
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton baloratu = new JButton("New button");
		baloratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				JFrame a = new BalorazioaGUI(jarri, jaso, eskera);
				a.setVisible(true);
				
			}
		});
		baloratu.setBounds(44, 130, 137, 37);
		contentPane.add(baloratu);
		
		JButton erreklamatu = new JButton("New button");
		erreklamatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new JarriErreklamGUI(jarri, jaso, eskera);
				a.setVisible(true);
			}
		});
		erreklamatu.setBounds(245, 132, 150, 33);
		contentPane.add(erreklamatu);
		
		JButton itxi = new JButton("New button");
		itxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		itxi.setBounds(152, 202, 117, 29);
		contentPane.add(itxi);
	}
}
