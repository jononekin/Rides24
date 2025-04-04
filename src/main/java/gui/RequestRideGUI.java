package gui;

import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Bidaiari;
import domain.Driver;
import domain.Eskaera;
import domain.Ride;
//import domain.Rider;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

public class RequestRideGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	
	private Bidaiari bidaiari;
	private JTextField fieldOrigin=new JTextField();
	private JTextField fieldDestination=new JTextField();
	
	private JLabel jLabelOrigin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.LeavingFrom"));
	private JLabel jLabelDestination = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.GoingTo")); 
	private JLabel jLabRideDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideDate"));

	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonRequest = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.RequestRide"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();

	
	private List<Date> datesWithEventsCurrentMonth;
	private JTextField prezioa;


	//private Rider rider;


	public RequestRideGUI(Bidaiari bidaiari) {

		//this.rider=rider;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.RequestRide"));
		this.bidaiari = bidaiari;
		jLabelOrigin.setBounds(new Rectangle(6, 56, 151, 26));

		jCalendar.setBounds(new Rectangle(300, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonRequest.setBounds(new Rectangle(100, 263, 130, 30));

		jButtonRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(275, 263, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 214, 305, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(6, 191, 320, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonRequest, null);
		this.getContentPane().add(jLabelOrigin, null);
		

		

		this.getContentPane().add(jCalendar, null);

		
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getThisMonthDatesWithRides("a","b",jCalendar.getDate());		
		
		jLabRideDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabRideDate.setBounds(298, 16, 140, 25);
		getContentPane().add(jLabRideDate);
		
		jLabelDestination.setBounds(6, 81, 151, 26);
		getContentPane().add(jLabelDestination);
		
		
		fieldOrigin.setBounds(160, 53, 130, 26);
		getContentPane().add(fieldOrigin);
		fieldOrigin.setColumns(10);
		
		
		fieldDestination.setBounds(160, 81, 130, 26);
		getContentPane().add(fieldDestination);
		fieldDestination.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RequestRideGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setBounds(6, 118, 86, 14);
		getContentPane().add(lblNewLabel);
		
		prezioa = new JTextField();
		prezioa.setText(ResourceBundle.getBundle("Etiquetas").getString("RequestRideGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		prezioa.setBounds(160, 115, 130, 20);
		getContentPane().add(prezioa);
		prezioa.setColumns(10);
		
		 //Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {		
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
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
	
					}
					jCalendar.setCalendar(calendarAct);
					int offset = jCalendar.getCalendar().get(Calendar.DAY_OF_WEEK);
					
						if (Locale.getDefault().equals(new Locale("es")))
							offset += 4;
						//else
							//offset += 5;
				Component o = (Component) jCalendar.getDayChooser().getDayPanel().getComponent(jCalendar.getCalendar().get(Calendar.DAY_OF_MONTH) + offset);
				}}});
		
	}	 
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		try {
			jLabelMsg.setText("");
			BLFacade facade = MainGUI.getBusinessLogic();
			String from = fieldOrigin.getText().trim();
			String to = fieldDestination.getText().trim();
			Date date = jCalendar.getDate();
			int prez = Integer.parseInt(prezioa.getText());
			if(bidaiari.getDirua()<prez) {
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString(""));//poner la etiqueta
			}else {
				facade.createEskaera(from, to, date, bidaiari, prez);
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("RequestRideGUI.RequestDone"));
				System.out.println("Eskaera gorde da.");
			}
			} catch (RideMustBeLaterThanTodayException e1) {
					// TODO Auto-generated catch block
			jLabelMsg.setText(e1.getMessage());
		} catch (RideAlreadyExistException e1) {
			// TODO Auto-generated catch block
			jLabelMsg.setText(e1.getMessage());
		}
	}
	

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	private String field_Errors() {
		if ((fieldOrigin.getText().length()==0) || (fieldDestination.getText().length()==0)) {
			return ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorQuery");
		}
		return null;
	}
}
