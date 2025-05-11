package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Erreklamazioa;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AcceptWhithChangeGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public AcceptWhithChangeGUI(Erreklamazioa erreklamazio) {
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 45, 259, 112);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setText(erreklamazio.getDeskribapena());
		scrollPane.setViewportView(textArea);
		
		
		JRadioButton leve = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("AWC.Leve"));
		leve.setBounds(29, 179, 111, 23);
		contentPane.add(leve);
		
		JRadioButton intermedio = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("AWC.Mediano"));
		intermedio.setBounds(169, 179, 111, 23);
		contentPane.add(intermedio);
		
		JRadioButton grave = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("AWC.Grave"));
		grave.setBounds(308, 179, 111, 23);
		contentPane.add(grave);
		BLFacade facade = MainGUI.getBusinessLogic();
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(leve.isSelected()) {
					facade.AcceptWithChange(erreklamazio, 1);
				}else if(intermedio.isSelected()) {
					facade.AcceptWithChange(erreklamazio, 2);
				}else {
					facade.AcceptWithChange(erreklamazio, 3);
				}
			}
		});
		btnNewButton.setBounds(169, 229, 89, 23);
		contentPane.add(btnNewButton);
	}
}
