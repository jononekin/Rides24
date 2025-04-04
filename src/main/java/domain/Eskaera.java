package domain;

import java.io.*;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Eskaera implements Serializable {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private String from;
	private String to;
	private Date date;
	private boolean baieztatuta;
	private float prez;
	
	public float getPrez() {
		return prez;
	}

	public void setPrez(float prez) {
		this.prez = prez;
	}

	public boolean isBaieztatuta() {
		return baieztatuta;
	}

	public void setBaieztatuta(boolean baieztatuta) {
		this.baieztatuta = baieztatuta;
	}

	private Bidaiari bidaiari;  
	
	public Eskaera(){
		super();
	}
	
	public Eskaera(String from, String to, Date date, Bidaiari bidaiari, float prez) {
		super();
		this.from = from;
		this.to = to;
		this.date=date;
		this.bidaiari = bidaiari;
		this.prez=prez;
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

	public String toString(){
		return from + ";" +to+";"+date;  
	}




	
}
