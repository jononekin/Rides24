package gui;

import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

public class DiruaSartuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDirua = new JTextField();
	private JRadioButton rdbtnSartu = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.diruaSartu"));
	private JRadioButton rdbtnAtera = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.diruaAtera"));
	private JLabel JLabelDiruaSartuAtera = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.diruaSartuAtera"));
	private JLabel JLabelSaldoa = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.saldoa"));
	private JTextArea textSaldoa = new JTextArea();
	private JLabel jLabelZenbat = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.zenbatDiru"));
	private JButton btnSartuAtera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.diruaSartuAtera"));
	private JTextArea textArea;

	private String type;
	
	private BLFacade facade;
	
	private User user;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		User u = null;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiruaSartuGUI frame = new DiruaSartuGUI(u);
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
	public DiruaSartuGUI(User u) {
		this.user = u;
		

		facade = MainGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		rdbtnAtera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "Atera";
			}
		});
		
		rdbtnAtera.setBounds(240, 181, 124, 21);
		contentPane.add(rdbtnAtera);
		buttonGroup.add(rdbtnSartu);
		buttonGroup.add(rdbtnAtera);
		rdbtnSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type="Sartu";
				textArea.setText("");
			}
		});
		
		rdbtnSartu.setBounds(87, 181, 135, 21);
		contentPane.add(rdbtnSartu);
		
		textDirua.setBounds(156, 144, 96, 19);
		contentPane.add(textDirua);
		textDirua.setColumns(10);
		
		JLabelSaldoa.setBounds(47, 62, 119, 13);
		contentPane.add(JLabelSaldoa);
		
		textSaldoa.setBounds(200, 56, 81, 22);
		contentPane.add(textSaldoa);
		textSaldoa.setEditable(false);
		textSaldoa.setText(Double.toString(user.getCash()));
		jLabelZenbat.setHorizontalAlignment(SwingConstants.CENTER);
		
		jLabelZenbat.setBounds(113, 113, 190, 13);
		contentPane.add(jLabelZenbat);
		btnSartuAtera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(type.equals("Sartu")) {
					user = facade.updateMoneyByEmail(user.getEmail(), Double.parseDouble(textDirua.getText()));
				} else if(type.equals("Atera") && user.getCash()>=Double.parseDouble(textDirua.getText())) {
					user = facade.updateMoneyByEmail(user.getEmail(), (-1)*Double.parseDouble(textDirua.getText()));
				} else {
					textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.insufficientBalance"));
				}
				textSaldoa.setText(Double.toString(user.getCash()));
			}
		});
		
		btnSartuAtera.setBounds(161, 208, 103, 31);
		contentPane.add(btnSartuAtera);
		JLabelDiruaSartuAtera.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabelDiruaSartuAtera.setBounds(139, 21, 142, 13);
		contentPane.add(JLabelDiruaSartuAtera);
		
		textArea = new JTextArea();
		textArea.setBounds(113, 241, 206, 22);
		contentPane.add(textArea);
	}
}
