package dataAccess;

import java.io.File;
import java.net.NoRouteToHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.UserAlreadyExistException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess()  {
		if (c.isDatabaseInitialized()) {
			String fileName=c.getDbFilename();

			File fileToDelete= new File(fileName);
			if(fileToDelete.delete()){
				File fileToDeleteTemp= new File(fileName+"$");
				fileToDeleteTemp.delete();

				  System.out.println("File deleted");
				} else {
				  System.out.println("Operation failed");
				}
		}
		open();
		if  (c.isDatabaseInitialized())initializeDB();
		
		System.out.println("DataAccess created => isDatabaseLocal: "+c.isDatabaseLocal()+" isDatabaseInitialized: "+c.isDatabaseInitialized());

		close();

	}
     
    public DataAccess(EntityManager db) {
    	this.db=db;
    }

	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();

		try {

		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=1; year+=1;}  
	    
		   
		    /*Create drivers 
			Driver driver1=new Driver("driver1@gmail.com","Aitor Fernandez");
			Driver driver2=new Driver("driver2@gmail.com","Ane Gaztañaga");
			Driver driver3=new Driver("driver3@gmail.com","Test driver");

			
			//Create rides
			driver1.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 4, 7);
			driver1.addRide("Donostia", "Gazteiz", UtilDate.newDate(year,month,6), 4, 8);
			driver1.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 4, 4);

			driver1.addRide("Donostia", "Iruña", UtilDate.newDate(year,month,7), 4, 8);
			
			driver2.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 3, 3);
			driver2.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 2, 5);
			driver2.addRide("Eibar", "Gasteiz", UtilDate.newDate(year,month,6), 2, 5);

			driver3.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,14), 1, 3);
			
			
						
			db.persist(driver1);
			db.persist(driver2);
			db.persist(driver3);
			*/
	
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	public List<String> getDepartCities(){
			TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.from FROM Ride r ORDER BY r.from", String.class);
			List<String> cities = query.getResultList();
			return cities;
		
	}
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	public List<String> getArrivalCities(String from){
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.to FROM Ride r WHERE r.from=?1 ORDER BY r.to",String.class);
		query.setParameter(1, from);
		List<String> arrivingCities = query.getResultList(); 
		return arrivingCities;
		
	}
	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driverEmail to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail, Car car) throws  RideAlreadyExistException, RideMustBeLaterThanTodayException {
		System.out.println(">> DataAccess: createRide=> from= "+from+" to= "+to+" driver="+driverEmail+" date "+date);
		try {
			if(new Date().compareTo(date)>0) {
				throw new RideMustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();
			
			Driver driver = db.find(Driver.class, driverEmail);
			if (car.doesRideExists(from, to, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			}
			Ride ride = car.addRide(from, to, date, nPlaces, price);
			//next instruction can be obviated
			db.persist(driver); 
			db.getTransaction().commit();

			return ride;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}
		
		
	}
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	public List<Ride> getRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getRides=> from= "+from+" to= "+to+" date "+date);

		List<Ride> res = new ArrayList<>();	
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date=?3",Ride.class);   
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, date);
		List<Ride> rides = query.getResultList();
	 	 for (Ride ride:rides){
		   res.add(ride);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT r.date FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date BETWEEN ?3 and ?4",Date.class);   
		
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, firstDayMonthDate);
		query.setParameter(4, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
		   res.add(d);
		  }
	 	return res;
	}
	
	public User isLogged(String user, String password) {
		User u = db.find(User.class, user);
		if(u.getPassword().equals(password)) {
			System.out.println(">> DataAccess: isLogged=> erabiltzailea sisteman dago");
			return u;
		} else {
			return null;
		}
	}
	
	public void storeUser(User u) throws UserAlreadyExistException {
		System.out.println(">> DataAccess: storeUser=> " + u.toString());
		User user = db.find(User.class, u.getEmail());
		if(user == null) {
			db.getTransaction().begin();
			db.persist(u); 
			db.getTransaction().commit();
		} else {
			throw new UserAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.UserAlreadyExist"));
		}
	}
	
	public User getUserByEmail(String email) {
		return db.find(User.class,email);
	}
	
	public User updateMoneyByEmail(String email, double cash) {
		User u = getUserByEmail(email);
		db.getTransaction().begin();
		u.setCash(u.getCash() + cash);
		db.persist(u);
		db.getTransaction().commit();
		System.out.println(u + " has been updated");
		return u;
	}
	
	public Ride getRideByRideNumber(int rideNumber) {
		return db.find(Ride.class,rideNumber);
	}
	
	public boolean addReserve(ReserveStatus rs, int rideNumber) {
		Ride ride = this.getRideByRideNumber(rideNumber);
		db.getTransaction().begin();
		boolean e = ride.addReserve(rs);
		ride.setCount(ride.getCount()+1);
		db.persist(ride);
		db.getTransaction().commit();
		System.out.println("Reserve: " + rs.getReserveNumber() + " has been added to: " + ride);
		return e;
	}  
	
	public void removeReserve(int rideNumber, int reserveNumber) {
		Ride ride = this.getRideByRideNumber(rideNumber);
		db.getTransaction().begin();
		ReserveStatus[] rlist = ride.getReserveList();
		ReserveStatus rstatus;
		System.out.println(rlist);
		for(int i = 0; i < rlist.length; i++) {
			rstatus = rlist[i];
			if(rstatus != null && rstatus.getReserveNumber()==reserveNumber) {
				rlist[i]=null;
				ride.setCount(ride.getCount()-1);
			}
		}
		DataAccess.moveNullsToRight(rlist);
		System.out.println(rlist);
		ride.setReserveList(rlist);
		db.persist(ride);
		db.getTransaction().commit();
		System.out.println("Reserve: " + reserveNumber + " has been removed");
	}
	
	public List<Ride> getAllRidesFromEmail(String email) {
		List<Ride> rideList = new ArrayList<>();
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.driver.email = ?1", Ride.class);
        query.setParameter(1, email);
		rideList = query.getResultList();
	 	return rideList;
	}
	
	public boolean addRideByEmail(String email, int rideNumber) {
		Ride ride = this.getRideByRideNumber(rideNumber);
		db.getTransaction().begin();
		Traveler t = (Traveler)this.getUserByEmail(email);
		db.persist(t);
		db.getTransaction().commit();
		System.out.println("Ride: " + ride + " has been added to: " + t);
		return true;
	}  
	
	 public boolean addCarByEmail(String email, String marka, String modeloa, int eserlekuKop) {
		 db.getTransaction().begin();
		 Driver driver = (Driver) this.getUserByEmail(email);
		 System.out.println(driver.getCars());
		 driver.addCar(marka, modeloa, eserlekuKop);
		 db.persist(driver);
		 db.getTransaction().commit();
		 System.out.println("Car: " + marka + ", " + modeloa + ", " + eserlekuKop +  " has been added to: " + driver);
		 return true;
	 }
	
	

public void open(){
		
		String fileName=c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);
			  db = emf.createEntityManager();
    	   }
		System.out.println("DataAccess opened => isDatabaseLocal: "+c.isDatabaseLocal());

		
	}

	public void close(){
		db.close();
		System.out.println("DataAcess closed");
	}
	
	 public static void moveNullsToRight(ReserveStatus[] array) {
	        int writeIndex = array.length - 1;

	        for (int readIndex = array.length - 1; readIndex >= 0; readIndex--) {
	            if (array[readIndex] != null) {
	                if (readIndex != writeIndex) {
	                    array[writeIndex] = array[readIndex];
	                    array[readIndex] = null;
	                }
	                writeIndex--;
	            }
	        }
	    }
	
}
