package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class FindRidesGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	
	private JComboBox<String> jComboBoxOrigin = new JComboBox<String>();
	DefaultComboBoxModel<String> originLocations = new DefaultComboBoxModel<String>();
	
	private JComboBox<String> jComboBoxDestination = new JComboBox<String>();
	DefaultComboBoxModel<String> destinationLocations = new DefaultComboBoxModel<String>();
	
	private JLabel jLabelOrigin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.LeavingFrom"));
	private JLabel jLabelDestination = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.GoingTo"));
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableRides= new JTable();

	private DefaultTableModel tableModelRides;

	
	private String[] columnNamesRides = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Driver"), 
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NPlaces"), 
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Price")
	};

	
	

//	public FindRidesGUI()
//	{
//		try
//		{
//			jbInit();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}

	
	public FindRidesGUI()
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(457, 6, 140, 25));
		jLabelEvents.setBounds(172, 229, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(274, 419, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});
		BLFacade facade = MainGUI.getBusinessLogic();
		List<String> origins=facade.getSourceLocations();
		for(String location:origins) originLocations.addElement(location);
		
		
		
		
		jLabelOrigin.setBounds(new Rectangle(6, 56, 92, 20));
		jLabelDestination.setBounds(6, 81, 61, 16);
		getContentPane().add(jLabelOrigin);

		getContentPane().add(jLabelDestination);

		jComboBoxOrigin.setModel(originLocations);
		jComboBoxOrigin.setBounds(new Rectangle(103, 50, 172, 20));
		jComboBoxOrigin.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println("Entra state change");
				destinationLocations.removeAllElements();
				BLFacade facade = MainGUI.getBusinessLogic();

				List<String> aCities=facade.getDestinationLocations((String)jComboBoxOrigin.getSelectedItem());
			    for(String aciti:aCities) {
			    	System.out.println(aciti);
			    	destinationLocations.addElement(aciti);
			    }
			    
				
			}
		});
		
		
		jComboBoxDestination.setModel(destinationLocations);
		jComboBoxDestination.setBounds(new Rectangle(103, 80, 172, 20));

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jComboBoxOrigin, null);
		
		this.getContentPane().add(jComboBoxDestination, null);


		jCalendar1.setBounds(new Rectangle(300, 50, 225, 150));

		//datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		//CreateRideGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getDatesWithRides((String)jComboBoxOrigin.getSelectedItem(),(String)jComboBoxDestination.getSelectedItem(),jCalendar1.getDate());
					}



					//CreateRideGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						tableModelRides.setDataVector(null, columnNamesRides);
						tableModelRides.setColumnCount(4); // another column added to allocate ride objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Ride> rides=facade.getRides((String)jComboBoxOrigin.getSelectedItem(),(String)jComboBoxDestination.getSelectedItem(),UtilDate.trim(jCalendar1.getDate()));

						if (rides.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NoRides")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Ride ride:rides){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Ride "+ride);

							row.add(ride.getDriver().getName());
							row.add(ride.getnPlaces());
							row.add(ride.getPrice());
							row.add(ride); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,3)
							tableModelRides.addRow(row);		
						}
						
					} catch (Exception e1) {

						//jLabelQueries.setText(e1.getMessage());
					}
					tableRides.getColumnModel().getColumn(0).setPreferredWidth(170);
					tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
					tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
					tableRides.getColumnModel().removeColumn(tableRides.getColumnModel().getColumn(3)); // not shown in JTable

				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(172, 257, 346, 150));

		

		scrollPaneEvents.setViewportView(tableRides);
		tableModelRides = new DefaultTableModel(null, columnNamesRides);

		tableRides.setModel(tableModelRides);
		
		tableModelRides.setDataVector(null, columnNamesRides);
		tableModelRides.setColumnCount(4); // another column added to allocate ride objects
		
		tableRides.getColumnModel().getColumn(0).setPreferredWidth(170);
		tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);

		tableRides.getColumnModel().removeColumn(tableRides.getColumnModel().getColumn(3)); // not shown in JTable

		this.getContentPane().add(scrollPaneEvents, null);

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}
