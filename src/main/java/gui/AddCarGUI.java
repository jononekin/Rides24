package gui;

import java.awt.EventQueue;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

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

        JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.AddCar"));
        lblNewLabel.setBounds(113, 10, 233, 50);
        contentPane.add(lblNewLabel);

        licensePlate = new JTextField();
        licensePlate.setBounds(178, 85, 168, 19);
        contentPane.add(licensePlate);
        licensePlate.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.licensePlate"));
        lblNewLabel_1.setBounds(46, 88, 104, 13);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.model"));
        lblNewLabel_2.setBounds(46, 117, 104, 13);
        contentPane.add(lblNewLabel_2);

        model = new JTextField();
        model.setBounds(178, 114, 168, 19);
        contentPane.add(model);
        model.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.places"));
        lblNewLabel_3.setBounds(46, 152, 104, 13);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.color"));
        lblNewLabel_4.setBounds(46, 185, 104, 13);
        contentPane.add(lblNewLabel_4);

        places = new JTextField();
        places.setBounds(178, 149, 168, 19);
        contentPane.add(places);
        places.setColumns(10);

        color = new JTextField();
        color.setBounds(178, 182, 168, 19);
        contentPane.add(color);
        color.setColumns(10);

        JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddCarGUI.AddCar"));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(!licensePlate.getText().isBlank() && !places.getText().isBlank() && !model.getText().isBlank() && !color.getText().isBlank()) {
					int placeNum = Integer.parseInt(places.getText());
        			Car car = new Car(licensePlate.getText(), placeNum, model.getText(), color.getText());
        			
        		}
        	}
        });
        btnNewButton.setBounds(189, 232, 119, 21);
        contentPane.add(btnNewButton);
    }
}
