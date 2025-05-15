package domain;

import java.io.*;
import java.util.ArrayList;
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

import domain.Alerta.AlertMota;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public abstract class User implements Serializable {

	@XmlID
	@Id
	private String email;
	private String name;
	private String nanZbk;
	private String tlf;
	private String pasahitza;
	private float dirua;

	@XmlIDREF
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ArrayList<Movement> mugimenduak = new ArrayList<Movement>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ArrayList<Alerta> alertak = new ArrayList<Alerta>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ArrayList<Balorazio> balorazioak = new ArrayList<Balorazio>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ArrayList<Erreklamazioa> errek = new ArrayList<Erreklamazioa>();

	public ArrayList<Erreklamazioa> getErrek() {
		return errek;
	}

	public void setErrek(ArrayList<Erreklamazioa> errek) {
		this.errek = errek;
	}

	public User() {
	}

	public User(String name, String pasahitza, String email, String nanZbk) {
		this.email = email;
		this.name = name;
		this.pasahitza = pasahitza;
		this.nanZbk = nanZbk;
		this.mugimenduak = new ArrayList<Movement>();
		this.alertak = new ArrayList<Alerta>();
		this.balorazioak = new ArrayList<Balorazio>();
		this.errek = new ArrayList<Erreklamazioa>();

	}

	public ArrayList<Movement> getMugimenduak() {
		return mugimenduak;
	}

	public void setMugimenduak(ArrayList<Movement> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}

	public float getDirua() {
		return dirua;
	}

	public void setDirua(float dirua) {
		this.dirua = dirua;
	}


	public ArrayList<Alerta> getAlertak() {
		return alertak;
	}

	public void setAlertak(ArrayList<Alerta> alertak) {
		this.alertak = alertak;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNanZbk() {
		return nanZbk;
	}

	public void setNanZbk(String nanZbk) {
		this.nanZbk = nanZbk;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dirua, email, name, nanZbk, pasahitza, tlf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", nanZbk=" + nanZbk + ", tlf=" + tlf + "dirua=" + dirua + "]";
	}

	public boolean doesMovementExist(Integer movementNumber) {
		for (Movement mov : mugimenduak)
			if ((java.util.Objects.equals(mov.getMovementNumber(), movementNumber)))
				return true;

		return false;
	}

	public Movement addMovement(Movement mov) {
		mugimenduak.add(mov);
		return mov;
	}

	public Balorazio addBalorazioa(Balorazio balorazio) {
		balorazioak.add(balorazio);
		return balorazio;
	}

	public ArrayList<Balorazio> getBalorazioak() {
		return balorazioak;
	}

	public void setBalorazioak(ArrayList<Balorazio> balorazioak) {
		this.balorazioak = balorazioak;
	}

	public Alerta addAlert(Alerta alert) {
		alertak.add(alert);
		System.out.println("User-eko addAlert metodora iritsi da");
		return alert;
	}
	public Erreklamazioa addErrek(Erreklamazioa errek1) {
		errek.add(errek1);
		return errek1;
	}
	public void ezabatuAlertakUser() {
		alertak.clear();
	}

}
