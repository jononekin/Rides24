package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Car;
import domain.Driver;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCarGUI extends JFrame {

    //private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField licensePlate;
    private JTextField model;
    private JTextField places;
    private JTextField color;
    private JLabel jLabelMsg = new JLabel(); 
    /**
     * Create the frame.
     */
    public AddCarGUI(Driver driver) {
        
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titulo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.AddCar"));
        titulo.setBounds(113, 10, 233, 50);
        contentPane.add(titulo);

        licensePlate = new JTextField();
        licensePlate.setBounds(178, 85, 168, 19);
        contentPane.add(licensePlate);
        licensePlate.setColumns(10);

        JLabel matrik = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.LicensePlate"));
        matrik.setBounds(46, 88, 104, 13);
        contentPane.add(matrik);

        JLabel modelo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.Model"));
        modelo.setBounds(46, 117, 104, 13);
        contentPane.add(modelo);

        model = new JTextField();
        model.setBounds(178, 114, 168, 19);
        contentPane.add(model);
        model.setColumns(10);

        JLabel lekuak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.Places"));
        lekuak.setBounds(46, 152, 104, 13);
        contentPane.add(lekuak);

        JLabel kolore = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.Color"));
        kolore.setBounds(46, 185, 104, 13);
        contentPane.add(kolore);

        places = new JTextField();
        places.setBounds(178, 149, 168, 19);
        contentPane.add(places);
        places.setColumns(10);

        color = new JTextField();
        color.setBounds(178, 182, 168, 19);
        contentPane.add(color);
        color.setColumns(10);
        
        jLabelMsg.setBounds(new Rectangle(56, 209, 305, 20));
		jLabelMsg.setForeground(Color.red);
		
		this.getContentPane().add(jLabelMsg, null);

        JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.AddCar"));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jLabelMsg.setText("");
        		BLFacade facade = MainGUI.getBusinessLogic();
        		if(!licensePlate.getText().isBlank() && !places.getText().isBlank() && !model.getText().isBlank() && !color.getText().isBlank()) {
					int placeNum = Integer.parseInt(places.getText());
        			boolean ondo = facade.addCar(licensePlate.getText(), placeNum, model.getText(), color.getText(), driver.getEmail());
        			if(!ondo) {
        				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error"));
        			}
        		}
        	}
        });
        btnNewButton.setBounds(189, 232, 119, 21);
        contentPane.add(btnNewButton);
    }
}
