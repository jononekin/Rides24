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
import domain.Ride.RideEgoera;
import exceptions.RequestAlreadyExistException;
//import domain.Rider;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

public class RequestRideGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	
	private Bidaiari bidaiari;
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonRequest = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.RequestRide"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();

	
	private List<Date> datesWithEventsCurrentMonth;
	private JTextField lekuKop;


	//private Rider rider;


	public RequestRideGUI(Bidaiari bidaiari) {

		//this.rider=rider;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.RequestRide"));
		this.bidaiari = bidaiari;
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));


		jLabelMsg.setBounds(new Rectangle(275, 214, 305, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(115, 232, 320, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		JComboBox rides = new JComboBox();
		rides.setBounds(20, 105, 560, 22);
		getContentPane().add(rides);
		
		List<Ride> rideList = facade.getAllRides();
		for (Ride ride : rideList) {
			if (ride.getEgoera()==RideEgoera.PENDING) {
				rides.addItem(ride);
			}
		}
		
		lekuKop = new JTextField();
		lekuKop.setBounds(219, 183, 96, 20);
		getContentPane().add(lekuKop);
		lekuKop.setColumns(10);
		
		JLabel sartuLekuKop = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RequestRideGUI.nPlaces")); //$NON-NLS-1$ //$NON-NLS-2$
		sartuLekuKop.setBounds(28, 186, 136, 14);
		getContentPane().add(sartuLekuKop);

		JButton request = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainBidaiariGUI.RequestRide"));
		request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Ride selectedRide = (Ride) rides.getSelectedItem();
					int lekuKopuru = Integer.parseInt(lekuKop.getText());
					facade.createEskaera(bidaiari, selectedRide, lekuKopuru);
				}catch (RequestAlreadyExistException a) {
					jLabelMsg.setText(a.getMessage());
				}
			}

		});
		request.setBounds(179, 263, 193, 23);
		getContentPane().add(request);
		
	}	 
		
}		
		
		
	

