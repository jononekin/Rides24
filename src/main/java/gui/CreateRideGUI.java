package gui;

import java.text.DateFormat;
import java.util.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Driver;
import domain.Ride;

import exceptions.RideAlreadyExistException;

public class CreateRideGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	
	private Driver driver;
	private JTextField fieldOrigin=new JTextField();
	private JTextField fieldDestination=new JTextField();
	
	private JLabel jLabelOrigin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.LeavingFrom"));
	private JLabel jLabelDestination = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.GoingTo")); 
	private JLabel jLabelSeats = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.NumberOfSeats"));
	private JLabel jLabRideDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideDate"));
	private JLabel jLabelPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.Price"));

	
	
	private JTextField jTextFieldSeats = new JTextField();
	private JTextField jTextFieldPrice = new JTextField();

	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.CreateRide"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();


//	public CreateRideGUI(Driver driver) {
//		try {
//			jbInit(driver);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public CreateRideGUI(Driver driver) {

		this.driver=driver;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));

	/*	jComboBoxOrigin.setModel(originLocations);
		jComboBoxOrigin.setBounds(new Rectangle(25, 50, 250, 20));
		
		jComboBoxDestination.setModel(destinationLocations);
		jComboBoxDestination.setBounds(new Rectangle(25, 120, 250, 20));
	*/	
		jLabelOrigin.setBounds(new Rectangle(6, 56, 92, 20));
		jLabelSeats.setBounds(new Rectangle(6, 119, 173, 20));
		jTextFieldSeats.setBounds(new Rectangle(139, 119, 60, 20));
		
		jLabelPrice.setBounds(new Rectangle(6, 159, 173, 20));
		jTextFieldPrice.setBounds(new Rectangle(139, 159, 60, 20));

		jCalendar.setBounds(new Rectangle(300, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonCreate.setBounds(new Rectangle(100, 223, 130, 30));
		//jButtonCreate.setEnabled(false);

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(285, 223, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(210, 163, 73, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldSeats, null);

		this.getContentPane().add(jLabelSeats, null);
		this.getContentPane().add(jLabelOrigin, null);
		//this.getContentPane().add(jComboBoxOrigin, null);
		//this.getContentPane().add(jComboBoxDestination, null);

		

		this.getContentPane().add(jCalendar, null);
		
		this.getContentPane().add(jLabelPrice, null);
		this.getContentPane().add(jTextFieldPrice, null);

		
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getDatesWithRides("a","b",jCalendar.getDate());
		//paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);
		
		

		jLabRideDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabRideDate.setBounds(298, 16, 140, 25);
		getContentPane().add(jLabRideDate);
		
		jLabelDestination.setBounds(6, 81, 61, 16);
		getContentPane().add(jLabelDestination);
		
		
		fieldOrigin.setBounds(100, 53, 130, 26);
		getContentPane().add(fieldOrigin);
		fieldOrigin.setColumns(10);
		
		
		fieldDestination.setBounds(104, 81, 123, 26);
		getContentPane().add(fieldDestination);
		fieldDestination.setColumns(10);
		 //Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//			
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: "+calendarAnt.getTime());
					System.out.println("calendarAct: "+calendarAct.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) { 
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolverá 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}
						
						jCalendar.setCalendar(calendarAct);
						System.out.println("Calendar actualizado "+calendarAct);
						
	
					}
					jCalendar.setCalendar(calendarAct);
					System.out.println("Calendar actualizado "+calendarAct);
					int offset = jCalendar.getCalendar().get(Calendar.DAY_OF_WEEK);
					
						if (Locale.getDefault().equals(new Locale("es")))
							offset += 4;
						else
							offset += 5;
				Component o = (Component) jCalendar.getDayChooser().getDayPanel().getComponent(jCalendar.getCalendar().get(Calendar.DAY_OF_MONTH) + offset);
				}}});
		
	}
		
////		// Code for JCalendar
//		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent propertychangeevent) {
////				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
////					public void propertyChange(PropertyChangeEvent propertychangeevent) {
//				if (propertychangeevent.getPropertyName().equals("locale")) {
//					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
//				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
//					calendarAnt = (Calendar) propertychangeevent.getOldValue();
//					calendarAct = (Calendar) propertychangeevent.getNewValue();
//					System.out.println("calendarAnt: "+calendarAnt.getTime());
//					System.out.println("calendarAct: "+calendarAct.getTime());
//					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
//					
//					int monthAnt = calendarAnt.get(Calendar.MONTH);
//					int monthAct = calendarAct.get(Calendar.MONTH);
//					if (monthAct!=monthAnt) {
//						if (monthAct==monthAnt+2) { 
//							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolverá 2 de marzo (se toma como equivalente a 30 de febrero)
//							// Con este código se dejará como 1 de febrero en el JCalendar
//							calendarAct.set(Calendar.MONTH, monthAnt+1);
//							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
//						}
//						
//						jCalendar.setCalendar(calendarAct);
//						
//	
//					}
//				});
//
//
//
//					paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);
//
//					//	Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
//					Date firstDay = UtilDate.trim(calendarAct.getTime());
//
//					try {
//						BLFacade facade = MainGUI.getBusinessLogic();
//
//						Vector<domain.Ride> rides = facade.getRides("a","b",firstDay);
//
//						if (rides.isEmpty())
//							jLabelOrigin.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
//									+ ": " + dateformat1.format(calendarAct.getTime()));
//						else
//							jLabelOrigin.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
//									+ dateformat1.format(calendarAct.getTime()));
//						//jComboBoxOrigin.removeAllItems();
//						System.out.println("Events " + rides);
//
//						/*for (domain.Ride ride : rides)
//							originLocations.addElement(ride.toString());
//						jComboBoxOrigin.repaint();
//*/
//						if (rides.size() == 0)
//							jButtonCreate.setEnabled(false);
//						else
//							jButtonCreate.setEnabled(true);
//
//					} catch (Exception e1) {
//
//						jLabelError.setText(e1.getMessage());
//					}
//
//				}
//			}
//		});


	
//
	
	 
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		//domain.Driver event = ((domain.Driver) jComboBoxOrigin.getSelectedItem());

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			// Displays an exception if the query field is empty
			String inputQuery = jTextFieldSeats.getText();

			if (inputQuery.length() > 0) {

				// It could be to trigger an exception if the introduced string is not a number
				int inputSeats = Integer.parseInt(jTextFieldSeats.getText());

				if (inputSeats <= 0)
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.SeatsMustBeGreaterThan0"));
				else {
					float price = Float.parseFloat(jTextFieldPrice.getText());
					//check price.....
					
					
					
					// Obtain the business logic from a StartWindow class (local or remote)
					BLFacade facade = MainGUI.getBusinessLogic();

					facade.createRide(fieldOrigin.getText(), fieldDestination.getText(), UtilDate.trim(jCalendar.getDate()), inputSeats, price, driver);

					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideCreated"));
				}
			} else
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuery"));
		} catch (java.lang.NumberFormatException e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
		
		} catch (RideAlreadyExistException e1) {

			jLabelMsg.setText(e1.getMessage());
		
		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
