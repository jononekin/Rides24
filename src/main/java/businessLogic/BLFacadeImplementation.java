package businessLogic;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Ride;
import domain.User;
import domain.Bidaiari;
import domain.Driver;
import domain.Eskaera;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.RideAlreadyExistException;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		
		    dbManager=new DataAccess();
		    
		//dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		dbManager=da;		
	}
    
    
    /**
     * {@inheritDoc}
     */
    @WebMethod public List<String> getDepartCities(){
    	dbManager.open();	
		
		 List<String> departLocations=dbManager.getDepartCities();		

		dbManager.close();
		
		return departLocations;
    	
    }
    /**
     * {@inheritDoc}
     */
	@WebMethod public List<String> getDestinationCities(String from){
		dbManager.open();	
		
		 List<String> targetCities=dbManager.getArrivalCities(from);		

		dbManager.close();
		
		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
	   
		dbManager.open();
		Ride ride=dbManager.createRide(from, to, date, nPlaces, price, driverEmail);		
		dbManager.close();
		return ride;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}

    
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess();

		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    
    /*@WebMethod
    public boolean storeDriver(Driver driver) {
 		dbManager.open();
 		boolean d = dbManager.storeDriver(driver);	
 		dbManager.close();
 		return d;
    };*/
    
    /*@WebMethod
    public boolean storeRider(Bidaiari rider) {
 		dbManager.open();
 		boolean r= dbManager.storeRider(rider);
 		dbManager.close();
 		return r;
    };*/
    
    @WebMethod
    public boolean storeUser(User user) {
 		dbManager.open();
 		boolean u= dbManager.storeUser(user);
 		dbManager.close();
 		return u;
    };
    @WebMethod
    public User isRegistered(String email, String password){
    	dbManager.open();
    	User u = dbManager.isRegistered(email, password);
    	dbManager.close();
    	return u;
    }
    @WebMethod
    public boolean diruaSartu(User user, int diru) {
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
    public Eskaera createEskaera(String from, String to, Date date, Bidaiari bidaiari) throws RideMustBeLaterThanTodayException, RideAlreadyExistException {
    	dbManager.open();
		Eskaera eskaera=dbManager.createEskaera(from, to, date, bidaiari);		
		dbManager.close();
		return eskaera;
    }
    @WebMethod
    public List<String> getEmails() {
 		dbManager.open();
 		List<String>emails=dbManager.getEmails();
 		dbManager.close();
 		return emails;
    };
    

}

