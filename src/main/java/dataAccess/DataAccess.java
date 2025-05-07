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
import domain.Erreklamazioa;
import domain.Eskaera;
import domain.Eskaera.EskaeraEgoera;
import domain.Ride.RideEgoera;
import domain.Movement;
import domain.Ride;
import domain.User;
import domain.Alerta;
import domain.Balorazio;
import domain.Alerta.AlertMota;
import domain.Bidaiari;
import domain.Car;
import exceptions.*;


/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	private EntityManager db;
	private EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess() {
		/*
		 * if (c.isDatabaseInitialized()) { String fileName=c.getDbFilename();
		 * 
		 * File fileToDelete= new File(fileName); if(fileToDelete.delete()){ File
		 * fileToDeleteTemp= new File(fileName+"$"); fileToDeleteTemp.delete();
		 * 
		 * System.out.println("File deleted"); } else {
		 * System.out.println("Operation failed"); } }
		 */
		open();
		if (c.isDatabaseInitialized())
			initializeDB();

		System.out.println("DataAccess created => isDatabaseLocal: " + c.isDatabaseLocal() + " isDatabaseInitialized: "
				+ c.isDatabaseInitialized());

		close();

	}

	public DataAccess(EntityManager db) {
		this.db = db;
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();

		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 1;
				year += 1;
			}

			/*
			 * //Create drivers Driver driver1=new
			 * Driver("driver1@gmail.com","Aitor Fernandez", "53256", "7342S", "hfsk");
			 * Driver driver2=new Driver("driver2@gmail.com","Ane Gaztañaga", "53256",
			 * "7342S", "hfsk"); Driver driver3=new
			 * Driver("driver3@gmail.com","Test driver", "53256", "7342S", "hfsk");
			 * 
			 * 
			 * //Create rides driver1.addRide("Donostia", "Bilbo",
			 * UtilDate.newDate(year,month,15), 4, 7); driver1.addRide("Donostia",
			 * "Gazteiz", UtilDate.newDate(year,month,6), 4, 8); driver1.addRide("Bilbo",
			 * "Donostia", UtilDate.newDate(year,month,25), 4, 4);
			 * 
			 * driver1.addRide("Donostia", "Iruña", UtilDate.newDate(year,month,7), 4, 8);
			 * 
			 * driver2.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 3, 3);
			 * driver2.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 2, 5);
			 * driver2.addRide("Eibar", "Gasteiz", UtilDate.newDate(year,month,6), 2, 5);
			 * 
			 * driver3.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,14), 1, 3);
			 * 
			 * 
			 * 
			 * db.persist(driver1); db.persist(driver2); db.persist(driver3);
			 */

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method returns all the cities where rides depart
	 * 
	 * @return collection of cities
	 */
	public List<String> getDepartCities() {
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.from FROM Ride r ORDER BY r.from", String.class);
		List<String> cities = query.getResultList();
		return cities;

	}

	/**
	 * This method returns all the arrival destinations, from all rides that depart
	 * from a given city
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	public List<String> getArrivalCities(String from) {
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.to FROM Ride r WHERE r.from=?1 ORDER BY r.to",
				String.class);
		query.setParameter(1, from);
		List<String> arrivingCities = query.getResultList();
		return arrivingCities;

	}

	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from        the origin location of a ride
	 * @param to          the destination location of a ride
	 * @param date        the date of the ride
	 * @param nPlaces     available seats
	 * @param driverEmail to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today
	 * @throws RideAlreadyExistException         if the same ride already exists for
	 *                                           the driver
	 */
	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail)
			throws RideAlreadyExistException, RideMustBeLaterThanTodayException {
		System.out.println(">> DataAccess: createRide=> from= " + from + " to= " + to + " driver=" + driverEmail
				+ " date " + date);
		try {
			if (new Date().compareTo(date) > 0) {
				throw new RideMustBeLaterThanTodayException(
						ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();

			Driver driver = db.find(Driver.class, driverEmail);
			if (driver.doesRideExists(from, to, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(
						ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			}
			Ride ride = driver.addRide(from, to, date, nPlaces, price);
			db.persist(driver);
			db.getTransaction().commit();

			return ride;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}
	}
	public Movement addMovement( float diruKantitatea, String mota, User user) {
		try {
			db.getTransaction().begin();
			User existingUser = db.find(User.class, user.getEmail());
			if(mota.equals("+")) {
			 addAlert(user, AlertMota.DIRUA_SARTU);
			}else {
				addAlert(user, AlertMota.DIRUA_ATERA);
			}
			Movement mov = existingUser.addMovement( diruKantitatea, mota);
			db.persist(mov);
			
			db.getTransaction().commit();
			return mov;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().rollback();
			return null;
		}
	}

/*
 * public RideRequest requestRide(User user, Ride ride, int seatQuantity) throws RequestAlreadyExistsException {
		open();
		db.getTransaction().begin();
		ArrayList<RideRequest> rRs = ((Traveler)user).getRideRequests();
		RideRequest newRequest = new RideRequest(RideRequest.RequestState.PENDING, seatQuantity, ride, (Traveler)user);;
		
		boolean alreadyHasReq = false;
		
		for (RideRequest rr: rRs) {
			if (rr.getRide().equals(ride)) {
				alreadyHasReq = true;
				break;
			}
		}
		if (alreadyHasReq == false) {
			db.persist(newRequest);

			Ride rideDB = db.find(Ride.class, ride.getRideNumber());
			rideDB.addRequest(newRequest);
			
			System.out.println(newRequest);
			
			Alert alert = new Alert("Traveler batek bidaia eskatu du"+user.getEmail(),"",rideDB.getDriver());
			db.persist(alert);
			registerAlert(alert);

		} else throw new RequestAlreadyExistsException();

		db.getTransaction().commit();
		close();
		
		return newRequest;

	}

 */

	public Eskaera createEskaera(User user, Ride ride, int nPlaces)throws RequestAlreadyExistException{
		db.getTransaction().begin();
		Bidaiari bidaiari = ((Bidaiari)user);
		Bidaiari bidaiariDB = db.find(Bidaiari.class, bidaiari.getEmail());
		Ride rideDB = db.find(Ride.class, ride.getRideNumber());
		Eskaera eskBerr = new Eskaera(Eskaera.EskaeraEgoera.PENDING, nPlaces, rideDB, bidaiariDB);
		ArrayList<Eskaera> eskaeraListDB = bidaiariDB.getEskaerak();
		boolean exisDa = false;
		for (Eskaera eskDB : eskaeraListDB) {
			if (eskDB.getRide().equals(ride)) {
				exisDa=true;
				break;
			}
		}
		if (exisDa==false) {
			eskaeraListDB.add(eskBerr);
		} else {
			db.getTransaction().rollback();
			throw new RequestAlreadyExistException();
		}
		ArrayList<Eskaera> rideEskaeraListDB = rideDB.getEskaerenList();
		rideEskaeraListDB.add(eskBerr);
		addAlert(ride.getDriver(), AlertMota.ESKAERA_EGIN);
		db.getTransaction().commit();
			
		return eskBerr;
	}

	/*
	 * public boolean storeRider (Bidaiari rider) { try {
	 * db.getTransaction().begin(); Bidaiari existingRider = db.find(Bidaiari.class,
	 * rider.getEmail()); if (existingRider == null) { db.persist(rider);
	 * db.getTransaction().commit();
	 * System.out.println("Rider stored successfully: " + rider); return true; }
	 * else { db.getTransaction().rollback();
	 * System.out.println("Rider already exists with email: " + rider.getEmail());
	 * return false; } } catch (Exception e) { db.getTransaction().rollback();
	 * System.err.println("Error storing rider: " + e.getMessage()); return false; }
	 */
	/*
	 * try { db.getTransaction().begin(); db.persist(rider);
	 * db.getTransaction().commit();
	 * System.out.println("Rider stored successfully: " + rider); } catch (Exception
	 * e) { db.getTransaction().rollback();
	 * System.err.println("Error storing rider: " + e.getMessage()); }
	 */
	// }

	/*
	 * public boolean storeDriver (Driver driver) {
	 * 
	 * try { db.getTransaction().begin(); Driver existingDriver =
	 * db.find(Driver.class, driver.getEmail()); if (existingDriver == null) {
	 * db.persist(driver); db.getTransaction().commit();
	 * System.out.println("Driver stored successfully: " + driver); return true; }
	 * else { db.getTransaction().rollback();
	 * System.out.println("Driver already exists with email: " + driver.getEmail());
	 * return false; } } catch (Exception e) { db.getTransaction().rollback();
	 * System.err.println("Error storing driver: " + e.getMessage()); return false;
	 * }
	 */

	/*
	 * try { db.getTransaction().begin(); db.persist(driver);
	 * db.getTransaction().commit();
	 * System.out.println("Driver stored successfully: " + driver); } catch
	 * (Exception e) { db.getTransaction().rollback();
	 * System.err.println("Error storing driver: " + e.getMessage()); }
	 */
	// }
	public User isRegistered(String email, String password) {
		try {
			db.getTransaction().begin();
			User existingUser = db.find(User.class, email);
			if (existingUser == null) { // Ez dago erregistratuta email hori duen inor
				db.getTransaction().rollback();// para deshacer una transacción en una base de datos
				System.out.println("You are not registered yet");
				return null;
			} else {
				if (password.equals(existingUser.getPasahitza())) {// berdinak badira pasahitzak
					System.out.println("Good");
					db.getTransaction().commit();
					return existingUser;
				} else {
					
					System.out.println("Your password is not correct");
					db.getTransaction().rollback();
					return null;
				}
			}
		} catch (Exception e) {
			db.getTransaction().rollback();
			System.out.println("Error while checking registration: " + e.getMessage());
			return null;
		}
	}

	public boolean diruaSartu(User user, float diru) {
		try {
			db.getTransaction().begin();
			User existingUser = db.find(User.class, user.getEmail());
			float diruBerr = existingUser.getDirua() + diru;
			if (diruBerr>0) {
				existingUser.setDirua(diruBerr);
				db.getTransaction().commit();
				return true;
			}else {
				db.getTransaction().rollback();
				return false;
			}
			
		} catch (Exception e) {
			db.getTransaction().rollback();
			System.err.println("Errorea dirua sartzean: " + e.getMessage());
			return false;
		}
	}
	
	

	public List<Bidaiari> getAllBidaiari() {
		db.getTransaction().begin();
		TypedQuery<Bidaiari> query = db.createQuery("SELECT b FROM Bidaiari b", Bidaiari.class);
		db.getTransaction().commit();
		return query.getResultList();
		
	}

	public List<Eskaera> getAllEskaera() {
		db.getTransaction().begin();
		TypedQuery<Eskaera> query = db.createQuery("SELECT b FROM Eskaera b", Eskaera.class);
		db.getTransaction().commit();
		return query.getResultList();
	}
	
	public List<Ride> getAllRides(){
		db.getTransaction().begin();
		TypedQuery<Ride> query = db.createQuery("SELECT b FROM Ride b", Ride.class);
		db.getTransaction().commit();
		return query.getResultList();
	}
	public List<Movement> getUserMugimenduak(User user) {
		try {
			db.getTransaction().begin();
			User existingUser = db.find(User.class, user.getEmail());
			db.getTransaction().commit();
			return existingUser.getMugimenduak();
		} catch (Exception e) {
			db.getTransaction().rollback();
			System.err.println("Errorea: " + e.getMessage());
			return null;
		}
	}
	/*public boolean jarri(boolean jarri, Eskaera eskaera) {
		try {
			db.getTransaction().begin();
			eskaera.setBaieztatuta(jarri);
			db.merge(eskaera);
			db.getTransaction().commit();
			return true;
		}catch (Exception e) {
			db.getTransaction().rollback();
			System.err.println("Error eskaera onartzean: " + e.getMessage());
			return false;
		}
	}*/

	public List<Ride> getDriverRides(Driver driver) {
		db.getTransaction().begin();
		Driver existingDriver = db.find(Driver.class, driver.getEmail());
		db.getTransaction().commit();
		return existingDriver.getRides();
	}
	public List<Car> getDriverCars(Driver driver) {
		db.getTransaction().begin();
		Driver existingDriver = db.find(Driver.class, driver.getEmail());
		db.getTransaction().commit();
		return existingDriver.getCars();
	}
	public void kantzelatuRide(Ride ride) {
		db.getTransaction().begin();
		Ride rideDB = db.find(Ride.class, ride.getRideNumber());
		rideDB.setEgoera(RideEgoera.CANCELLED);
		for(Eskaera esk: rideDB.getEskaerenList()) {
			if (esk.getEgoera()==EskaeraEgoera.ACCEPTED) {
				esk.getBidaiari().setDirua(esk.getBidaiari().getDirua()+esk.getPrez());
				addMovement(esk.getPrez(), "+", esk.getBidaiari());
				addAlert(esk.getBidaiari(), AlertMota.BIDAIA_KANTZELATU);
				esk.setEgoera(EskaeraEgoera.CANCELLED);
			}
		}
		db.getTransaction().commit();
		
	}
	
	
	public void amaituRide(Ride ride) {
		db.getTransaction().begin();
		Ride rideDB = db.find(Ride.class, ride.getRideNumber());
		rideDB.setEgoera(RideEgoera.FINISHED);
		for(Eskaera esk: rideDB.getEskaerenList()) {
			if(esk.getEgoera()==EskaeraEgoera.ACCEPTED) {
				esk.setEgoera(EskaeraEgoera.FINISHED);
				addAlert(esk.getBidaiari(), AlertMota.BIDAIA_AMAITUTA);
			}
		}
		
		db.getTransaction().commit();
	}
	
	public void ezabatuAlertakUser(User user) {
		db.getTransaction().begin();
		User userDB = db.find(User.class, user.getEmail());
		userDB.ezabatuAlertakUser();
		db.getTransaction().commit();
		
	}

	/*public boolean ezabatuEskaera(Eskaera esk) {
		try {
			db.getTransaction().begin();
			if(esk.getBidaiari() !=null) {
				Bidaiari bidaiari = esk.getBidaiari();
				bidaiari.getEskaerak().remove(esk);
				esk.setBidaiari(null);
				db.merge(bidaiari);
			}
			db.remove(db.contains(esk) ? esk : db.merge(esk));
			db.getTransaction().commit();
			return true;
		} catch (Exception e) {
			db.getTransaction().rollback();
			System.err.println("Error deleting ride: " + e.getMessage());
			return false;
		}
	}*/

	public boolean storeUser(User user) {
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
	 * @param to   the destination location of a ride
	 * @param date the date of the ride
	 * @return collection of rides
	 */
	public List<Ride> getRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getRides=> from= " + from + " to= " + to + " date " + date);

		List<Ride> res = new ArrayList<>();
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date=?3",
				Ride.class);
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, date);
		List<Ride> rides = query.getResultList();
		for (Ride ride : rides) {
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
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param from the origin location of a ride
	 * @param to   the destination location of a ride
	 * @param date of the month for which days with rides want to be retrieved
	 * @return collection of rides
	 */
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT r.date FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date BETWEEN ?3 and ?4",
				Date.class);

		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, firstDayMonthDate);
		query.setParameter(4, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			res.add(d);
		}
		return res;
	}

	public boolean addCar(String licensePlate, int places, String model, String color, String driverEmail) {
	
			db.getTransaction().begin();
			Driver driver = db.find(Driver.class, driverEmail);
			if(driver!=null) {
				if (driver.doesCarExist(licensePlate)) {
					db.getTransaction().commit();
					System.out.println("car already exists");
					return false;
				}
				System.out.println(licensePlate);
				Car car = driver.addCar(licensePlate,  places,  model,  color);
				System.out.println(car.toString());

				//db.persist(driver);
				db.getTransaction().commit();
				return true;
			}else {
				db.getTransaction().rollback();
				return false;
			}
			
		
	}

	
	public void open() {

		String fileName = c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);
			db = emf.createEntityManager();
		}
		System.out.println("DataAccess opened => isDatabaseLocal: " + c.isDatabaseLocal());

	}

	public void close() {
		db.close();
		System.out.println("DataAcess closed");
	}
	
	
	public List<Eskaera> getEskaerakRide(Ride ride) {
		
		db.getTransaction().begin();
	    Ride rideDB = db.find(Ride.class, ride.getRideNumber());
	    TypedQuery<Eskaera> query = db.createQuery("SELECT DISTINCT esk FROM Eskaera esk WHERE esk.ride = :ride", Eskaera.class);
	    query.setParameter("ride", rideDB);
	    List<Eskaera> eskaerak = query.getResultList();
	    db.getTransaction().commit();
	    return eskaerak;
	}
	
	
	public void acceptEskaera(Eskaera eskaera) throws NotEnoughPlacesException, NotEnoughMoneyException{
		db.getTransaction().begin();

		Eskaera eskaeraDB = db.find(Eskaera.class, eskaera.getEskaeraNumber());
		Bidaiari bidaiariDB = db.find(Bidaiari.class, eskaera.getBidaiari());
		Ride ride = eskaeraDB.getRide();
		int lekuLibre = ride.getnPlaces();
		int lekuEskatu = eskaeraDB.getNPlaces();
		float daukanDiru = bidaiariDB.getDirua();
		float balioDuena = ride.getPrice();
		
		if(daukanDiru<balioDuena) {
			db.getTransaction().rollback();
			throw new NotEnoughMoneyException();
		}
		
		db.persist(bidaiariDB);

		if(lekuLibre<lekuEskatu) {
			db.getTransaction().rollback();
			throw new NotEnoughPlacesException();
		}
		
		eskaeraDB.acceptRequest();
		ride.setnPlaces(lekuLibre-lekuEskatu);
		addMovement(eskaeraDB.getPrez(), "-", bidaiariDB);
		addAlert(bidaiariDB, AlertMota.ESKAERA_ONARTU);
		db.getTransaction().commit();
	}
	
	public void ezOnartuEskaera(Eskaera eskaera) {
		db.getTransaction().begin();
		Eskaera eskaeraDB = db.find(Eskaera.class, eskaera.getEskaeraNumber());
		eskaeraDB.ezeztatuEskaera();
		addAlert(eskaera.getBidaiari(), AlertMota.ESKAERA_EZONARTUA);
		db.getTransaction().commit();
	}
	public void konfirmatuEskaera(Eskaera eskaera) {
		db.getTransaction().begin();
		Eskaera eskaeraDB = db.find(Eskaera.class, eskaera.getEskaeraNumber());
		eskaeraDB.konfirmatuEskaera();
		eskaeraDB.getRide().getDriver().diruSartuDri(eskaeraDB.getNPlaces()*eskaeraDB.getRide().getPrice());
		addMovement(eskaeraDB.getRide().getPrice(), "+", eskaeraDB.getRide().getDriver());
		db.getTransaction().commit();
	}
	
	public List<Eskaera> getEskaerakBidaiari(Bidaiari bidaiari){
		db.getTransaction().begin();
		Bidaiari bidaiariDB = db.find(Bidaiari.class, bidaiari.getEmail());
		db.getTransaction().commit();
		return bidaiariDB.getEskaerak();
	}
	public void kantzelatuEskaera(Eskaera eskaera) {
		db.getTransaction().begin();
		Eskaera eskaeraDB = db.find(Eskaera.class, eskaera.getEskaeraNumber());
		if (eskaeraDB.getEgoera() == EskaeraEgoera.PENDING) {
			eskaeraDB.setEgoera(EskaeraEgoera.CANCELLED);
		}else if (eskaeraDB.getEgoera() == EskaeraEgoera.ACCEPTED) {
			eskaeraDB.getBidaiari().diruSartuBid(eskaeraDB.getPrez());
			addMovement(eskaeraDB.getPrez(), "+", eskaeraDB.getBidaiari());
			int lekuLibre = eskaeraDB.getRide().getnPlaces();
			int eskatutakoak = eskaeraDB.getNPlaces();
			eskaeraDB.getRide().setnPlaces(lekuLibre+eskatutakoak);
			eskaeraDB.setEgoera(EskaeraEgoera.CANCELLED);	
		}
		addAlert(eskaera.getRide().getDriver(), AlertMota.ESKAERA_KANTZELATU);
		db.getTransaction().commit();
	}
	
	public boolean ezabatuUser(User user) {
		db.getTransaction().begin();
		boolean ondo = true;
		User ezabUserDB = bilatuUserEmail(user.getEmail());
		if (ezabUserDB instanceof Driver) {
			Driver ezabDriverDB = (Driver) user;
			List<Ride> rideList = ezabDriverDB.getRides();
			for(Ride ride : rideList) {
				kantzelatuRide(ride);
			}
		}else {
			Bidaiari ezabBidDB = (Bidaiari) user;
			List<Eskaera> eskList = ezabBidDB.getEskaerak();
			for(Eskaera esk : eskList) {
				if(esk.getEgoera() == EskaeraEgoera.FINISHED) {
					return false;
				}else {
					kantzelatuEskaera(esk);
				}
			}
		}
		db.getTransaction().commit();
		return true;
	}
	
	public User bilatuUserEmail(String email) {
		User user = db.find(User.class, email);
		if (user != null) {
			return user;
		}else{
			return null;
		}
	}
	
	public List<Alerta> getUserAlertak(User user){
		try {
			db.getTransaction().begin();
			User existingUser = db.find(User.class, user.getEmail());
			db.getTransaction().commit();
			return existingUser.getAlertak();
		} catch (Exception e) {
			db.getTransaction().rollback();
			System.err.println("Errorea: " + e.getMessage());
			return null;
		}
	}
	
	public void addAlert(User user, AlertMota mota) {
		try {
			db.getTransaction().begin();
			User existingUser = db.find(User.class, user.getEmail());
			Alerta ale = existingUser.addAlert(user, mota);
			db.persist(ale);
			db.getTransaction().commit();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().rollback();
		}
	}
	
	public void addBalorazioa(Balorazio balorazio) {
		try {
			db.getTransaction().begin();
			User existingUser = db.find(User.class, balorazio.getUserJaso().getEmail());
			Balorazio balorazioGehitutakoa = existingUser.addBalorazioa(balorazio);
			db.persist(balorazioGehitutakoa);
			addAlert(existingUser, AlertMota.BALORATUTA);
			db.getTransaction().commit();
		} catch (NullPointerException e) {
			db.getTransaction().rollback();
		}
	}
	
	public List<Balorazio> getUserBalorazioa(User user){
		try {
			db.getTransaction().begin();
			User existingUser = db.find(User.class, user.getEmail());
			db.getTransaction().commit();
			return existingUser.getBalorazioak();
		} catch (Exception e) {
			db.getTransaction().rollback();
			System.err.println("Errorea: " + e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Erreklamazioa> getUserErrek(User user){
		try {
			db.getTransaction().begin();
			User existingUser = db.find(User.class, user.getEmail());
			db.getTransaction().commit();
			return existingUser.getErrek();
		} catch (Exception e) {
			db.getTransaction().rollback();
			System.err.println("Errorea: " + e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Erreklamazioa> getAllErrek() {
		db.getTransaction().begin();
		ArrayList<Erreklamazioa> errek = new ArrayList<>();	
		TypedQuery<Erreklamazioa> query = db.createQuery("SELECT c FROM Complaint c",Erreklamazioa.class);   
		List<Erreklamazioa> errekList = query.getResultList();
		for (Erreklamazioa e :errekList){
			errek.add(e);
		}
		
		db.getTransaction().commit();
		return errek;
	}
	
	public void acceptErrek(Erreklamazioa selectRk) {
		db.getTransaction().begin();
		if(selectRk.getErrekJaso() instanceof Bidaiari) {
			
		}else {
			//Bidaiariari gehitu dirua besteari kendu
			Driver driver = (Driver) selectRk.getErrekJaso();
			Driver driverDB = db.find(Driver.class, driver.getEmail());
			
			Bidaiari bidaiari = (Bidaiari) selectRk.getErrekJarri();
			Bidaiari bidaiariDB = db.find(Bidaiari.class, bidaiari.getEmail());
			
			Erreklamazioa erreklDB = db.find(Erreklamazioa.class, selectRk.getId());
			
			bidaiariDB.diruSartuBid(erreklDB.getEskaera().getPrez());
			addMovement(erreklDB.getEskaera().getPrez(), "+", bidaiariDB);
			addAlert(bidaiariDB, AlertMota.DIRUA_SARTU);
			
			driverDB.diruSartuDri(erreklDB.getEskaera().getPrez()*(-1));
			addMovement(erreklDB.getEskaera().getPrez(), "-", driverDB);
			addAlert(driverDB, AlertMota.DIRUA_ATERA);
		}
		db.getTransaction().commit();
	}

}
