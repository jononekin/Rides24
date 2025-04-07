package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bidaiari;
import domain.Car;
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
	private JLabel jLabelMsg = new JLabel();
	private JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { OnartuGUI frame = new OnartuGUI();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public OnartuGUI(Driver driver) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Title"));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(110, 139, 316, 22);
		contentPane.add(comboBox_1);
		if (driver.getCars() != null) {
			List<Car> cars = driver.getCars();
			for (Car c : cars) {
				comboBox_1.addItem(c);
			}
		}

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(34, 78, 392, 22);
		BLFacade facade = MainGUI.getBusinessLogic();
		//List<Bidaiari> bidaiariList = facade.getAllBidaiari();
		comboBox.removeAllItems();
		
		List<Eskaera> eskaeraList = facade.getAllEskaera();
		for (Eskaera eskaera : eskaeraList) {
			System.out.println(eskaera);
			if(!eskaera.isBaieztatuta()) {
				comboBox.addItem(eskaera);
			}
		}
		
		/*for (Bidaiari bidaiari : bidaiariList) {
			System.out.println(bidaiari);
			for (Eskaera eskaera : bidaiari.getEskaerak()) {
				System.out.println(eskaera);
				if(!eskaera.isBaieztatuta()) {
					comboBox.addItem(eskaera);
				}
				
			}
		}
		*/
		contentPane.add(comboBox);

		jLabelMsg.setBounds(new Rectangle(81, 214, 305, 20));
		jLabelMsg.setForeground(Color.red);
		contentPane.add(jLabelMsg);

		JLabel lbl_numSeat = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.ChooseCar")); //$NON-NLS-1$ //$NON-NLS-2$
		lbl_numSeat.setBounds(34, 143, 143, 14);
		contentPane.add(lbl_numSeat);

		JLabel lbl_Title = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Title"));
		lbl_Title.setBounds(49, 35, 335, 32);
		contentPane.add(lbl_Title);

		JButton btnOnartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Accept"));
		btnOnartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((comboBox_1.getSelectedItem() != null) && (comboBox.getSelectedItem() != null)) {
					try {
						Eskaera selectedEskaera = (Eskaera) comboBox.getSelectedItem();
						Calendar gaur = Calendar.getInstance();
						gaur.set(Calendar.HOUR_OF_DAY, 0);
						gaur.set(Calendar.MINUTE, 0);
						gaur.set(Calendar.SECOND, 0);
					    gaur.set(Calendar.MILLISECOND, 0);
						Calendar fechaRide = Calendar.getInstance();
						fechaRide.setTime(selectedEskaera.getDate());
						fechaRide.set(Calendar.HOUR_OF_DAY, 0);
					    fechaRide.set(Calendar.MINUTE, 0);
					    fechaRide.set(Calendar.SECOND, 0);
					    fechaRide.set(Calendar.MILLISECOND, 0);
					    if((gaur.equals(fechaRide)||gaur.before(fechaRide)) && facade.diruaSartu(selectedEskaera.getBidaiari(), selectedEskaera.getPrez()*(-1))) {
					    	facade.addMovement( selectedEskaera.getPrez(), "-", selectedEskaera.getBidaiari());
					    	facade.jarri(true, selectedEskaera);
							float prezioa = selectedEskaera.getPrez();
							Car selectedCar = (Car) comboBox_1.getSelectedItem();
							int inputSeats = selectedCar.getPlaces();
							try {

								facade.createRide(selectedEskaera.getFrom(), selectedEskaera.getTo(), selectedEskaera.getDate(),
									inputSeats, prezioa, driver.getEmail());
								jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("OnartuGUI.Accepted"));
							} catch (RideMustBeLaterThanTodayException e1) {
								System.out.println("Error: La fecha del viaje debe ser posterior a hoy.");
								jLabelMsg.setText(e1.getMessage());
							} catch (RideAlreadyExistException e2) {
								System.out.println("Error: Ya existe un viaje con esos datos.");
								jLabelMsg.setText(e2.getMessage());
							}
					    }else {
					    	jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error"));
					    }
					} catch (NumberFormatException o) {
						System.out.println("Error: el texto del JLabel no es un número válido.");
					}
				}else {
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Error"));
				}
				/*
				 * try { BLFacade facade = MainGUI.getBusinessLogic(); Eskaera selectedEskaera =
				 * (Eskaera) comboBox.getSelectedItem(); try { float prezioa =
				 * Float.parseFloat(lbl_Prezioa.getText()); int numSeat =
				 * Integer.parseInt(lbl_numSeat.getText());
				 * facade.createRide(selectedEskaera.getFrom(),selectedEskaera.getTo(),
				 * selectedEskaera.getDate(), numSeat, prezioa, driver.getEmail()); } catch
				 * (NumberFormatException o) {
				 * System.out.println("Error: el texto del JLabel no es un número válido."); } }
				 * catch (RideMustBeLaterThanTodayException e1) { // TODO Auto-generated catch
				 * block jLabelMsg.setText(e1.getMessage()); } catch (RideAlreadyExistException
				 * e1) { // TODO Auto-generated catch block jLabelMsg.setText(e1.getMessage());
				 * }
				 */
			}

		});
		btnOnartu.setBounds(177, 171, 89, 23);
		contentPane.add(btnOnartu);

	}
}
