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

import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Driver;
import domain.Eskaera;
import domain.Ride;
import domain.User;
import domain.Bidaiari;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;
	


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess()  {
		/*if (c.isDatabaseInitialized()) {
			String fileName=c.getDbFilename();

			File fileToDelete= new File(fileName);
			if(fileToDelete.delete()){
				File fileToDeleteTemp= new File(fileName+"$");
				fileToDeleteTemp.delete();

				  System.out.println("File deleted");
				} else {
				  System.out.println("Operation failed");
				}
		}*/
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
	    
		   
		    /*//Create drivers 
			Driver driver1=new Driver("driver1@gmail.com","Aitor Fernandez", "53256", "7342S", "hfsk");
			Driver driver2=new Driver("driver2@gmail.com","Ane Gaztañaga", "53256", "7342S", "hfsk");
			Driver driver3=new Driver("driver3@gmail.com","Test driver", "53256", "7342S", "hfsk");

			
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
	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail) throws  RideAlreadyExistException, RideMustBeLaterThanTodayException {
		System.out.println(">> DataAccess: createRide=> from= "+from+" to= "+to+" driver="+driverEmail+" date "+date);
		try {
			if(new Date().compareTo(date)>0) {
				throw new RideMustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();
			
			Driver driver = db.find(Driver.class, driverEmail);
			if (driver.doesRideExists(from, to, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			}
			Ride ride = driver.addRide(from, to, date, nPlaces, price);
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
	
	public Eskaera createEskaera(String from, String to, Date date, Bidaiari bidaiari)throws  RideAlreadyExistException, RideMustBeLaterThanTodayException {
		try {
			if(new Date().compareTo(date)>0) {
				throw new RideMustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();
			bidaiari = db.merge(bidaiari);
			if (bidaiari.doesEskaeraExists(from, to, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			}
			Eskaera eskaera = bidaiari.addEskaera(from, to, date);
			//next instruction can be obviated
			db.persist(eskaera); 
			db.getTransaction().commit();

			return eskaera;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}
		
	}
		
		
		/*
		public boolean storeRider (Bidaiari rider) {
			try {
		        db.getTransaction().begin();
		        Bidaiari existingRider = db.find(Bidaiari.class, rider.getEmail());
		        if (existingRider == null) {
		            db.persist(rider);
		            db.getTransaction().commit();
		            System.out.println("Rider stored successfully: " + rider);
		            return true;
		        } else {
		            db.getTransaction().rollback();
		            System.out.println("Rider already exists with email: " + rider.getEmail());
		            return false;
		        }
		    } catch (Exception e) {
		        db.getTransaction().rollback();
		        System.err.println("Error storing rider: " + e.getMessage());
		        return false;
		    }*/
			/*try {
		        db.getTransaction().begin();
		        db.persist(rider);
		        db.getTransaction().commit();
		        System.out.println("Rider stored successfully: " + rider);
		    } catch (Exception e) {
		        db.getTransaction().rollback(); 
		        System.err.println("Error storing rider: " + e.getMessage());
		    }*/
		//}
		
		/*public boolean storeDriver (Driver driver) {
			
			try {
		        db.getTransaction().begin();
		        Driver existingDriver = db.find(Driver.class, driver.getEmail());
		        if (existingDriver == null) {
		            db.persist(driver);
		            db.getTransaction().commit();
		            System.out.println("Driver stored successfully: " + driver);
		            return true;
		        } else {
		            db.getTransaction().rollback();
		            System.out.println("Driver already exists with email: " + driver.getEmail());
		            return false;
		        }
		    } catch (Exception e) {
		        db.getTransaction().rollback();
		        System.err.println("Error storing driver: " + e.getMessage());
		        return false;
		    }*/
			
			/*try {
				db.getTransaction().begin();
				db.persist(driver);
			    db.getTransaction().commit();
			    System.out.println("Driver stored successfully: " + driver);
			} catch (Exception e) {
			    db.getTransaction().rollback(); 
			    System.err.println("Error storing driver: " + e.getMessage());
			}*/
		//}
		public User isRegistered(String email, String password){
			try {
				db.getTransaction().begin();
				User existingUser = db.find(User.class, email);
				if(existingUser == null) { //Ez dago erregistratuta email hori duen inor
					db.getTransaction().rollback();//para deshacer una transacción en una base de datos
					System.out.println("You are not registered yet");
					return null;
				}else {
					if(password.equals(existingUser.getPasahitza())) {//berdinak badira pasahitzak
						System.out.println("Good");
						return existingUser;
					}else {
						System.out.println("Your password is not correct");
						return null;
					}
				}
			} catch(Exception e) {
				db.getTransaction().rollback();
		        System.out.println("Error while checking registration: " + e.getMessage());
		        return null;
			}
		}
		
		public boolean diruaSartu (User user, int diru) {
			try {
				db.getTransaction().begin();
				User existingUser = db.find(User.class, user.getEmail());
				int diruBerr = ((Bidaiari)existingUser).getDirua() + diru;
				((Bidaiari)existingUser).setDirua(diruBerr);
				db.getTransaction().commit();
				return true;
			}catch(Exception e) {
				db.getTransaction().rollback();
		        System.err.println("Errorea dirua sartzean: " + e.getMessage());
		        return false;
			}
		}
		
		public List<Bidaiari> getAllBidaiari() {
			db.getTransaction().begin();
			TypedQuery<Bidaiari> query = db.createQuery("SELECT b FROM Bidaiari b", Bidaiari.class);
		    return query.getResultList();
		}
		
		public boolean storeUser (User user) {
			try {
		        db.getTransaction().begin();
		        User existingUser = db.find(User.class, user.getEmail());
		        if (existingUser == null) {
		            db.persist(user);
		            db.getTransaction().commit();
		            System.out.println("User stored successfully: " + user);
		            return true;
		        } else {
		            db.getTransaction().rollback();
		            System.out.println("User already exists with email: " + user.getEmail());
		            return false;
		        }
		    } catch (Exception e) {
		        db.getTransaction().rollback();
		        System.err.println("Error storing user: " + e.getMessage());
		        return false;
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
	public List<String> getEmails() {
		List<String> emails = new ArrayList<String>();	
		emails = db.createQuery("SELECT d.email FROM Driver d", String.class).getResultList();
	 	return emails;
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
	
}
