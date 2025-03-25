package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bidaiari;
import domain.Driver;
import domain.Eskaera;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class OnartuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_prezioa;
	private JTextField textField_numSeat;
	private JLabel jLabelMsg = new JLabel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnartuGUI frame = new OnartuGUI();
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
	public OnartuGUI(Driver driver) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Title"));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(49, 80, 335, 22);
		BLFacade facade = MainGUI.getBusinessLogic();
		List<Bidaiari> bidaiariList = facade.getAllBidaiari();
		comboBox.removeAllItems();
		for (Bidaiari bidaiari : bidaiariList) {
		    for (Eskaera eskaera : bidaiari.getEskaerak()) {
		        comboBox.addItem(eskaera);
		    }
		}
		contentPane.add(comboBox);
		JLabel lbl_Prezioa = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.Price")); //$NON-NLS-1$ //$NON-NLS-2$
		lbl_Prezioa.setBounds(10, 143, 82, 14);
		contentPane.add(lbl_Prezioa);
		
		jLabelMsg.setBounds(new Rectangle(81, 214, 305, 20));
		jLabelMsg.setForeground(Color.red);
		contentPane.add(jLabelMsg);
		
		textField_prezioa = new JTextField();
		//textField_prezioa.setText(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		textField_prezioa.setBounds(81, 140, 96, 20);
		contentPane.add(textField_prezioa);
		textField_prezioa.setColumns(10);
		
		JLabel lbl_numSeat = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.NumberOfSeats")); //$NON-NLS-1$ //$NON-NLS-2$
		lbl_numSeat.setBounds(216, 143, 96, 14);
		contentPane.add(lbl_numSeat);
		
		textField_numSeat = new JTextField();
		//textField_numSeat.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.NumberOfSeats")); //$NON-NLS-1$ //$NON-NLS-2$
		textField_numSeat.setBounds(315, 140, 96, 20);
		contentPane.add(textField_numSeat);
		textField_numSeat.setColumns(10);
		
		JLabel lbl_Title = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Title"));
		lbl_Title.setBounds(49, 35, 335, 32);
		contentPane.add(lbl_Title);
		
		JButton btnOnartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Accept"));
		btnOnartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    String preziotext = textField_prezioa.getText().trim();
				    String numSeatText = textField_numSeat.getText().trim();
				    Eskaera selectedEskaera = (Eskaera) comboBox.getSelectedItem();
				    
				    if (preziotext.isEmpty() || numSeatText.isEmpty()) {
				        System.out.println("Error: uno de los campos está vacío.");
				        return;
				    }

				    float prezioa = Float.parseFloat(preziotext);
				    int numSeat = Integer.parseInt(numSeatText);
				    try {
				    	facade.createRide(selectedEskaera.getFrom(), selectedEskaera.getTo(),selectedEskaera.getDate(), numSeat, prezioa, driver.getEmail());
				    	jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Accepted"));
				    }catch(RideMustBeLaterThanTodayException e1){
				    	System.out.println("Error: La fecha del viaje debe ser posterior a hoy.");
				        jLabelMsg.setText(e1.getMessage());
				    }catch (RideAlreadyExistException e2) {
				        System.out.println("Error: Ya existe un viaje con esos datos.");
				        jLabelMsg.setText(e2.getMessage()); 
				    }

				} catch (NumberFormatException o) {
				    System.out.println("Error: el texto del JLabel no es un número válido.");
				}

				/*try {
					BLFacade facade = MainGUI.getBusinessLogic();
					Eskaera selectedEskaera = (Eskaera) comboBox.getSelectedItem();
					try {
						float prezioa = Float.parseFloat(lbl_Prezioa.getText());
						int numSeat = Integer.parseInt(lbl_numSeat.getText());
						facade.createRide(selectedEskaera.getFrom(),selectedEskaera.getTo(), selectedEskaera.getDate(), numSeat, prezioa, driver.getEmail());
					} catch (NumberFormatException o) {
						System.out.println("Error: el texto del JLabel no es un número válido.");
					}
				} catch (RideMustBeLaterThanTodayException e1) {
					// TODO Auto-generated catch block
					jLabelMsg.setText(e1.getMessage());
				} catch (RideAlreadyExistException e1) {
					// TODO Auto-generated catch block
					jLabelMsg.setText(e1.getMessage());
				}*/
			}
		
		});
		btnOnartu.setBounds(177, 171, 89, 23);
		contentPane.add(btnOnartu);
		
		
	}
}
