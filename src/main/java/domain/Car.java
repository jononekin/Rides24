package domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;



@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private String licensePlate;
	private int places;
	private String model;
	private String color;
	@XmlIDREF
	private Driver driver;
	public Car() {}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Car(String licensePlate, int places, String model, String color) {
		this.licensePlate = licensePlate;
		this.places = places;
		this.model = model;
		this.color = color;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public int getPlaces() {
		return places;
	}
	public void setPlaces(int places) {
		this.places = places;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "[licensePlate=" + licensePlate + ", places=" + places + ", model=" + model + "]";
	}
	
	
}