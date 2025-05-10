package domain;

import java.io.*;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Eskaera implements Serializable{
	
	@Override
	public int hashCode() {
		return Objects.hash(date, from, nPlaces, bidaiari, ride, egoera, to);
	}
	
	public enum EskaeraEgoera{
		ACCEPTED,
		REJECTED,
		PENDING,
		CANCELLED,
		FINISHED,
		COMPLAINED,
		CONFIRMED,
		VALUED
	}
	
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer eskaeraNumber;
	private String from;
	private String to;
	private Date date;
	private float prez;
	private Bidaiari bidaiari; 
	private EskaeraEgoera egoera;
	private Ride ride;
	private int nPlaces;
	
	public Eskaera(EskaeraEgoera egoera, int nPlaces, Ride ride, Bidaiari bidaiari) {
		this.ride = ride;
		this.egoera=egoera;
		this.nPlaces=nPlaces;
		this.from = ride.getFrom();
		this.to = ride.getTo();
		this.date=ride.getDate();
		this.prez=ride.getPrice();
		this.bidaiari = bidaiari;
		this.egoera=EskaeraEgoera.PENDING;
		this.eskaeraNumber = hashCode();	
	}
	public Eskaera(){
		super();
	}
	
	
	
	public void acceptRequest() {
		egoera = EskaeraEgoera.ACCEPTED;
		this.bidaiari.ordaindu(prez);
	}
	
	public void ezeztatuEskaera(){
		egoera = EskaeraEgoera.REJECTED;
	}
	public void konfirmatuEskaera(){
		egoera = EskaeraEgoera.CONFIRMED;
	}
	
	
	public Integer getEskaeraNumber() {
		return eskaeraNumber;
	}

	public void setEskaeraNumber(Integer eskaeraNumber) {
		this.eskaeraNumber = eskaeraNumber;
	}
	
	public float getPrez() {
		return prez;
	}

	public void setPrez(float prez) {
		this.prez = prez;
	}
	public EskaeraEgoera getEgoera() {
		return egoera;
	}

	public void setEgoera(EskaeraEgoera egoera) {
		this.egoera = egoera;
	}


	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Bidaiari getBidaiari() {
		return bidaiari;
	}

	public void setBidaiari(Bidaiari bidaiari) {
		this.bidaiari = bidaiari;
	}
	@Override
	public String toString() {
		return " Bidaiaria: " + bidaiari.getEmail() + " Egoera: " + egoera+ " Eskatutako lekuak: " + nPlaces;
	}
	public Ride getRide() {
		return ride;
	}
	public void setRide(Ride ride) {
		this.ride = ride;
	}
	public int getNPlaces() {
		return nPlaces;
	}
	public void setNPlaces(int nPlaces) {
		this.nPlaces = nPlaces;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eskaera other = (Eskaera) obj;
		return Objects.equals(date, other.date) && Objects.equals(from, other.from)
				&& nPlaces == other.nPlaces
				&& Objects.equals(bidaiari, other.bidaiari) && Objects.equals(ride, other.ride)
				&& egoera == other.egoera && Objects.equals(to, other.to);
	}

	
	
}
