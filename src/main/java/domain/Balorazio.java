package domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Balorazio implements Serializable {
	@Id @XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private int ID;
	private User userJarri;
	private User userJaso;
	private String deskribapena;
	private int nota;
	
	public Balorazio(User userJarri, User userJaso, String deskribapena, int nota) {
		super();
		this.userJarri = userJarri;
		this.userJaso = userJaso;
		this.deskribapena = deskribapena;
		this.nota = nota;
	}
	@Override
	public String toString() {
		return "Balorazio [userJarri=" + userJarri + ", userJaso=" + userJaso + ", deskribapena=" + deskribapena
				+ ", nota=" + nota + "]";
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public User getUserJarri() {
		return userJarri;
	}
	public void setUserJarri(User userJarri) {
		this.userJarri = userJarri;
	}
	public User getUserJaso() {
		return userJaso;
	}
	public void setUserJaso(User userJaso) {
		this.userJaso = userJaso;
	}
	public String getDeskribapena() {
		return deskribapena;
	}
	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	
}
