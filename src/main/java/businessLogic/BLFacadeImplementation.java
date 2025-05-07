package businessLogic;

import java.util.ArrayList;
import java.util.Date;


import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Ride;
import domain.User;
import domain.Eskaera.EskaeraEgoera;
import domain.Alerta;
import domain.Balorazio;
import domain.Bidaiari;
import domain.Car;
import domain.Driver;
import domain.Erreklamazioa;
import domain.Eskaera;
import domain.Movement;
import exceptions.*;


/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");

		dbManager = new DataAccess();

		// dbManager.close();

	}

	public BLFacadeImplementation(DataAccess da) {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();

		dbManager = da;
	}

	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public List<String> getDepartCities() {
		dbManager.open();

		List<String> departLocations = dbManager.getDepartCities();

		dbManager.close();

		return departLocations;

	}

	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public List<String> getDestinationCities(String from) {
		dbManager.open();

		List<String> targetCities = dbManager.getArrivalCities(from);

		dbManager.close();

		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail)
			throws RideMustBeLaterThanTodayException, RideAlreadyExistException {

		dbManager.open();
		Ride ride = dbManager.createRide(from, to, date, nPlaces, price, driverEmail);
		dbManager.close();
		return ride;
	};

	@WebMethod
	public boolean addCar(String licensePlate, int places, String model, String color, String driverEmail) {
		dbManager.open();
		boolean car = dbManager.addCar(licensePlate, places, model, color, driverEmail);
		dbManager.close();
		return car;
	}
	

	@WebMethod
	public Movement addMovement( float diruKantitatea, String mota, User user) {
		dbManager.open();
		Movement mov = dbManager.addMovement( diruKantitatea, mota, user);
		dbManager.close();
		return mov;
	}

	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public List<Ride> getRides(String from, String to, Date date) {
		dbManager.open();
		List<Ride> rides = dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}

	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		dbManager.open();
		List<Date> dates = dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}

	public void close() {
		DataAccess dB4oManager = new DataAccess();

		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public void initializeBD() {
		dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
	/*@WebMethod 
	public boolean jarri(boolean jarri, Eskaera eskaera) {
		dbManager.open();
		boolean ondo=dbManager.jarri(jarri, eskaera);
		dbManager.close();
		return ondo;
	}*/

	/*
	 * @WebMethod public boolean storeDriver(Driver driver) { dbManager.open();
	 * boolean d = dbManager.storeDriver(driver); dbManager.close(); return d; };
	 */

	/*
	 * @WebMethod public boolean storeRider(Bidaiari rider) { dbManager.open();
	 * boolean r= dbManager.storeRider(rider); dbManager.close(); return r; };
	 */

	@WebMethod
	public boolean storeUser(User user) {
		dbManager.open();
		boolean u = dbManager.storeUser(user);
		dbManager.close();
		return u;
	};

	@WebMethod
	public User isRegistered(String email, String password) {
		dbManager.open();
		User u = dbManager.isRegistered(email, password);
		dbManager.close();
		return u;
	}

	@WebMethod
	public boolean diruaSartu(User user, float diru) {
		dbManager.open();
		boolean o = dbManager.diruaSartu(user, diru);
		dbManager.close();
		return o;
	}

	@WebMethod
	public List<Bidaiari> getAllBidaiari() {
		dbManager.open();
		List<Bidaiari> bidList = dbManager.getAllBidaiari();
		dbManager.close();
		return bidList;
	}
	
	@WebMethod 
	public List<Ride> getAllRides(){
		dbManager.open();
		List<Ride> rideList = dbManager.getAllRides();
		dbManager.close();
		return rideList;
	}
	
	@WebMethod 
	public List<Movement> getUserMugimenduak(User user){
		dbManager.open();
		List<Movement> movList = dbManager.getUserMugimenduak(user);
		dbManager.close();
		return movList;
	}


	@WebMethod
	public List<Ride> getDriverRides(Driver driver) {
		dbManager.open();
		List<Ride> ridList = dbManager.getDriverRides(driver);
		dbManager.close();
		return ridList;
	}
	@WebMethod
	public List<Car> getDriverCars(Driver driver) {
		dbManager.open();
		List<Car> carList = dbManager.getDriverCars(driver);
		dbManager.close();
		return carList;
	}


	@WebMethod public void kantzelatuRide(Ride ride) { 
		dbManager.open();
		dbManager.kantzelatuRide(ride);
		dbManager.close();
	}

	@WebMethod
	public List<Eskaera> getAllEskaera() {
		dbManager.open();
		List<Eskaera> eskList = dbManager.getAllEskaera();
		dbManager.close();
		return eskList;
	}

	@WebMethod
	public Eskaera createEskaera(User user, Ride ride, int nPlaces) throws RequestAlreadyExistException {
		dbManager.open();
		Eskaera eskaera = dbManager.createEskaera(user, ride, nPlaces);
		dbManager.close();
		return eskaera;
	}

	@WebMethod
	public List<String> getEmails() {
		dbManager.open();
		List<String> emails = dbManager.getEmails();
		dbManager.close();
		return emails;
	};
	
	@WebMethod 
	public List<Eskaera> getEskaerakRide(Ride ride){
		dbManager.open();
		List<Eskaera> eskaerak = dbManager.getEskaerakRide(ride);
		dbManager.close();
		return eskaerak;
	}
	
	@WebMethod 
	public void acceptEskaera(Eskaera eskaera) throws NotEnoughPlacesException, NotEnoughMoneyException{
		dbManager.open();
	    dbManager.acceptEskaera(eskaera);
		dbManager.close();
	}
	
	@WebMethod public void ezOnartuEskaera(Eskaera eskaera) {
		dbManager.open();
	    dbManager.ezOnartuEskaera(eskaera);
		dbManager.close();
	}

	@WebMethod public void amaituRide(Ride ride) {
		dbManager.open();
	    dbManager.amaituRide(ride);
		dbManager.close();
	}
	
	@WebMethod public List<Eskaera> getEskaerakBidaiari(Bidaiari bidaiari){
		dbManager.open();
		List<Eskaera> eskaerak = dbManager.getEskaerakBidaiari(bidaiari);
		dbManager.close();
		return eskaerak;
	}
	@WebMethod 
	public void konfirmatuEskaera(Eskaera eskaera) {
		dbManager.open();
	    dbManager.konfirmatuEskaera(eskaera);
		dbManager.close();
	}
	
	@WebMethod public void kantzelatuEskaera(Eskaera eskaera) {
		dbManager.open();
	    dbManager.kantzelatuEskaera(eskaera);
		dbManager.close();
	}
	
	@WebMethod public boolean ezabatuUser(User user) {
		dbManager.open();
	    boolean ondo = dbManager.ezabatuUser(user);
		dbManager.close();
		return ondo;
	}
	@WebMethod public User bilatuUserEmail(String email) {
		dbManager.open();
		User user = dbManager.bilatuUserEmail(email);
		dbManager.close();
		return user;
	}
	@WebMethod public List<Alerta> getUserAlertak(User user){
		dbManager.open();
		List<Alerta> alk = dbManager.getUserAlertak(user);
		dbManager.close();
		return alk;
	}
	
	@WebMethod public void ezabatuAlertakUser(User user) {
		dbManager.open();
	    dbManager.ezabatuAlertakUser(user);
		dbManager.close();
	}
	
	@WebMethod public void addBalorazioa(Balorazio balorazio) {
		dbManager.open();
	    dbManager.addBalorazioa(balorazio);
		dbManager.close();
	}
	
	@WebMethod public List<Balorazio> getUserBalorazioa(User user){
		dbManager.open();
		List<Balorazio> alk = dbManager.getUserBalorazioa(user);
		dbManager.close();
		return alk;
	}
	@WebMethod public List<Erreklamazioa> getUserErrek(User user){
		dbManager.open();
		List<Erreklamazioa> errek = dbManager.getUserErrek(user);
		dbManager.close();
		return errek;
	}
	@WebMethod public List<Erreklamazioa> getAllErrek(){
		dbManager.open();
		List<Erreklamazioa> errek = dbManager.getAllErrek();
		dbManager.close();
		return errek;
	}
	
	@WebMethod public void acceptErrek(Erreklamazioa selectRk) {
		dbManager.open();
	    dbManager.acceptErrek(selectRk);
		dbManager.close();
	}

}
