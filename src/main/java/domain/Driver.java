package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Driver extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Ride> rides=new Vector<Ride>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Car> cars=new Vector<Car>();
	
	public Driver() {}
	public Driver(String email, String name) {
		super(email, name);
	}

	public Ride removeRide(Integer rideId) {
		for (int i = 0; i < rides.size(); i++) {
            if (rides.get(i).getRideNumber().equals(rideId)) {
                Ride removedRide = rides.get(i);
                rides.remove(i);
                return removedRide;
            }
        }
        return null;
		
		
		
		/*boolean found=false;
		int index=0;
		Ride r=null;
		while (!found && index<rides.size()) {
			r=rides.get(index);
			//if ( (java.util.Objects.equals(r.getFrom(),from)) && (java.util.Objects.equals(r.getTo(),to)) && (java.util.Objects.equals(r.getDate(),date)) )
			if(r.getRideNumber()==rideId) {
				found=true;
			}else {
				index++;
			}
			
		}*/
			
		/*if (found) {
			rides.remove(index);
			System.out.print(rides.toString());
			return r;
		}else if (index==0) {
			rides.remove(index);
			System.out.print(rides.toString());
			return r;
		} else return null;*/
		
		
	}
	
	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 * 
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Ride addRide(String from, String to, Date date, int nPlaces, float price)  {
        Ride ride=new Ride(from,to,date,nPlaces,price, this);
        rides.add(ride);
        return ride;
	}
	
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Car addCar(String licensePlate, int places, String model, String color)  {
        Car car=new Car(licensePlate, places, model, color);
        cars.add(car);
        return car;
	}
	
	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}

	public boolean doesRideExists(String from, String to, Date date)  {	
		for (Ride r:rides)
			if ( (java.util.Objects.equals(r.getFrom(),from)) && (java.util.Objects.equals(r.getTo(),to)) && (java.util.Objects.equals(r.getDate(),date)) )
			 return true;
		
		return false;
	}
	public boolean doesCarExist(String licensePlate)  {	
			for (Car c:cars)
				if ( (java.util.Objects.equals(c.getLicensePlate(),licensePlate)))
					return true;
		return false;
	}
	
	
}
