package domain;

import java.io.*;
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
public abstract class User {

	@XmlID
	@Id
	private String email;
	private String name;
	private String nanZbk;
	private String tlf;
	private String pasahitza;
	

	public User(String email, String name) {
		super();
		this.email = email;
		this.name = name;
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
	public String toString() {
		return "Rider [email=" + email + ", name=" + name + ", nanZbk=" + nanZbk + ", tlf=" + tlf + ", pasahitza="
				+ pasahitza;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, name, nanZbk, pasahitza, tlf);
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
		return Objects.equals(email, other.email)&& Objects.equals(name, other.name) && Objects.equals(nanZbk, other.nanZbk)&& Objects.equals(pasahitza, other.pasahitza) && Objects.equals(tlf, other.tlf);
	}


}
