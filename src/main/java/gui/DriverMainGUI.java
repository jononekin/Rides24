package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Driver;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class DriverMainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DriverMainGUI.SelectOption"));
	private JButton jButtonCreateRide = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DriverMainGUI.CreateRide"));
	private JButton jButtonAcceptReservation = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DriverMainGUI.AcceptReservation"));

	private Driver driver;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Driver d = null;
				try {
					DriverMainGUI frame = new DriverMainGUI(d);
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
	public DriverMainGUI(Driver d) {
		this.driver = d;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(jLabelSelectOption);
		
		contentPane.add(jButtonCreateRide);
		
		contentPane.add(jButtonAcceptReservation);
	}

}
