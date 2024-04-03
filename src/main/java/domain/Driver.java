package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
	private List<Car> cars=new Vector<Car>();

	public Driver(String email, String name, String password, double cash) {
		super(email, name, password, cash);
	}	
	
	@Override
	public String toString() {
		return "Driver " + super.toString();
	}

	public Car addCar(String marka, String modeloa, int eserlekuKop)  {
		Car car = new Car(this, marka, modeloa, eserlekuKop);
        cars.add(car);
        return car;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		return Objects.equals(cars, other.cars);
	}
	
}
