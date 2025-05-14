package businessLogic;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Ride;
import domain.User;
import domain.Eskaera.EskaeraEgoera;
import domain.Alerta;
import domain.Balorazio;
import domain.Bidaiari;
import domain.Car;
import domain.Driver;
import domain.Erreklamazioa;
import domain.Erreklamazioa.ErrekLarri;
import domain.Eskaera;
import domain.Movement;
import exceptions.*;


import javax.jws.WebMethod;
import javax.jws.WebService;
 
/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	@WebMethod public List<String> getDepartCities();
	
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	@WebMethod public List<String> getDestinationCities(String from);


	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driver to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail) throws RideMustBeLaterThanTodayException, RideAlreadyExistException;
	
   
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	@WebMethod public List<Ride> getRides(String from, String to, Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	@WebMethod public List<Date> getThisMonthDatesWithRides(String from, String to, Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	//@WebMethod public boolean storeDriver(Driver driver);
	
	//@WebMethod public boolean storeRider(Bidaiari rider);
	
	@WebMethod public List<String> getEmails();
	//@WebMethod public boolean jarri(boolean jarri, Eskaera eskaera);
	@WebMethod public boolean storeUser(User user);
	
	@WebMethod public User isRegistered(String email, String password);
	
	@WebMethod public boolean diruaSartu(User user, float diru);
	@WebMethod public List<Bidaiari> getAllBidaiari();
	@WebMethod public List<Ride> getDriverRides(Driver driver);
	//@WebMethod public List<Eskaera> getAllEskaera();
	@WebMethod public List<Ride> getAllRides();
	@WebMethod public List<Movement> getUserMugimenduak(User user);
	@WebMethod public List<Car> getDriverCars(Driver driver); 
	@WebMethod public Eskaera createEskaera(User user, Ride ride, int nPlaces) throws RequestAlreadyExistException;
	@WebMethod public boolean addCar(String licensePlate, int places, String model, String color, String driverEmail);
	@WebMethod public void kantzelatuRide(Ride ride);
	//@WebMethod public boolean ezabatuEskaera(Eskaera eskaera);
	
	@WebMethod public List<Eskaera> getEskaerakRide(Ride ride);
	@WebMethod public void acceptEskaera(Eskaera eskaera) throws NotEnoughPlacesException, NotEnoughMoneyException;
	@WebMethod public void ezOnartuEskaera(Eskaera eskaera);
	@WebMethod public void amaituRide(Ride ride);
	@WebMethod public List<Eskaera> getEskaerakBidaiari(Bidaiari bidaiari);
	@WebMethod public void konfirmatuEskaera(Eskaera eskaera);
	@WebMethod public void kantzelatuEskaera(Eskaera eskaera);
	@WebMethod public boolean ezabatuUser(User user);
	@WebMethod public User bilatuUserEmail(String email);
	@WebMethod public List<Alerta> getUserAlertak(User user);
	@WebMethod public void ezabatuAlertakUser(User user);
	@WebMethod public void addBalorazioa(Balorazio balorazio);
	@WebMethod public List<Balorazio> getUserBalorazioa(User user);
	@WebMethod public List<Erreklamazioa> getUserErrek(User user);
	@WebMethod public List<Erreklamazioa> getAllErrek();
	@WebMethod public void acceptErrek(Erreklamazioa selectRk);
	@WebMethod public void rejectErrekUser(Erreklamazioa selectRk);
	@WebMethod public void rejectErrekAdmin(Erreklamazioa selectRk);
	@WebMethod public void AcceptWithChange(Erreklamazioa erreklamazio, int num);
	@WebMethod public void addErreklamazio(User userJarri, User userJaso, Eskaera eskSelect, String sartutakoTxt, float prez, ErrekLarri lar);
	
	
}
