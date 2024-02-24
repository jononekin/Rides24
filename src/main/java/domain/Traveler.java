package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlIDREF;

public class Traveler extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Ride> rides=new Vector<Ride>();
	
	public Traveler(String email, String name, String password, Double cash) {
		super(email, name, password, cash);
	}
	
	public Ride addRide(Ride r)  {
        rides.add(r);
        r.setBetMinimum((int) r.getnPlaces()-1);
        return r;
	}

	@Override
	public String toString() {
		return "Traveler " + super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Traveler other = (Traveler) obj;
		return Objects.equals(rides, other.rides);
	}
}
