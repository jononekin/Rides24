package domain;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import domain.Eskaera.EskaeraEgoera;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Ride implements Serializable {
	public enum RideEgoera{
		PENDING,
		FINISHED,
		CANCELLED,
	}

	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer rideNumber;
	private String from;
	private String to;
	private int nPlaces;
	private Date date;
	private float price;
	private RideEgoera egoera;
	
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	private ArrayList<Eskaera>  eskaerenList= new ArrayList<Eskaera>();

	
	private Driver driver;  
	
	public Ride(){
		
	}
	public Ride(Integer rideNumber, String from, String to, Date date, int nPlaces, float price, Driver driver) {
		super();
		this.rideNumber = rideNumber;
		this.from = from;
		this.to = to;
		this.nPlaces = nPlaces;
		this.date=date;
		this.price=price;
		this.driver = driver;
		this.setEgoera(RideEgoera.PENDING);
	}

	

	public Ride(String from, String to,  Date date, int nPlaces, float price, Driver driver) {
		super();
		this.from = from;
		this.to = to;
		this.nPlaces = nPlaces;
		this.date=date;
		this.price=price;
		this.driver = driver;
		this.setEgoera(RideEgoera.PENDING);
	}
	
	public RideEgoera getEgoera() {
		return egoera;
	}

	public void setEgoera(RideEgoera egoera) {
		this.egoera = egoera;
	}

	public ArrayList<Eskaera> getEskaerenList() {
		return eskaerenList;
	}
	

	public void setEskaerenList(ArrayList<Eskaera> eskaerenList) {
		this.eskaerenList = eskaerenList;
	}

	public void addEskaera(Eskaera eskBerr) {
		eskaerenList.add(eskBerr);
	}
	public ArrayList<Eskaera>  amaituBidaia() {
		ArrayList<Eskaera> eskList = new ArrayList<Eskaera>();
		this.egoera = RideEgoera.FINISHED;
		for (Eskaera esk : eskList) {
			if (esk.getEgoera() == EskaeraEgoera.ACCEPTED) {
				esk.setEgoera(EskaeraEgoera.FINISHED);
				eskList.add(esk);
			}
		}
		return eskList;
	}
	
	public void setnPlaces(int nPlaces) {
		this.nPlaces = nPlaces;
	}

	
	
	/**
	 * Get the  number of the ride
	 * 
	 * @return the ride number
	 */
	public Integer getRideNumber() {
		return rideNumber;
	}

	
	/**
	 * Set the ride number to a ride
	 * 
	 * @param ride Number to be set	 */
	
	public void setRideNumber(Integer rideNumber) {
		this.rideNumber = rideNumber;
	}


	/**
	 * Get the origin  of the ride
	 * 
	 * @return the origin location
	 */

	public String getFrom() {
		return from;
	}


	/**
	 * Set the origin of the ride
	 * 
	 * @param origin to be set
	 */	
	
	public void setFrom(String origin) {
		this.from = origin;
	}

	/**
	 * Get the destination  of the ride
	 * 
	 * @return the destination location
	 */

	public String getTo() {
		return to;
	}


	/**
	 * Set the origin of the ride
	 * 
	 * @param destination to be set
	 */	
	public void setTo(String destination) {
		this.to = destination;
	}

	/**
	 * Get the free places of the ride
	 * 
	 * @return the available places
	 */
	
	/**
	 * Get the date  of the ride
	 * 
	 * @return the ride date 
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Set the date of the ride
	 * 
	 * @param date to be set
	 */	
	public void setDate(Date date) {
		this.date = date;
	}

	
	public int getnPlaces() {
		return nPlaces;
	}

	/**
	 * Set the free places of the ride
	 * 
	 * @param  nPlaces places to be set
	 */

	public void setBetMinimum(int nPlaces) {
		this.nPlaces = nPlaces;
	}

	/**
	 * Get the driver associated to the ride
	 * 
	 * @return the associated driver
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * Set the driver associated to the ride
	 * 
	 * @param driver to associate to the ride
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}



	public String toString(){
		return " Nondik: "+from+" Nora: "+to+/*" Eguna: "+date+*/" Leku kopurua: "+nPlaces+" Prezioa: "+price+" Egoera: "+egoera;  
	}

	public float guztiraBalioa (Eskaera esk) {
		return esk.getRide().getPrice()*esk.getNPlaces();
	}


	
}
