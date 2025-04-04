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
	private float dirua;
	private List<Movement> mugimenduak = new Vector<Movement>();

	public List<Movement> getMugimenduak() {
		return mugimenduak;
	}

	public void setMugimenduak(List<Movement> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}

	public User(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	public float getDirua() {
		return dirua;
	}

	public void setDirua(float dirua) {
		this.dirua = dirua;
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
		return Float.floatToIntBits(dirua) == Float.floatToIntBits(other.dirua) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(nanZbk, other.nanZbk)
				&& Objects.equals(pasahitza, other.pasahitza) && Objects.equals(tlf, other.tlf);
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", nanZbk=" + nanZbk + ", tlf=" + tlf + ", pasahitza="
				+ pasahitza + ", dirua=" + dirua + "]";
	}

	public boolean doesMovementExist(Integer movementNumber) {
		for (Movement mov : mugimenduak)
			if ((java.util.Objects.equals(mov.getMovementNumber(), movementNumber)))
				return true;

		return false;
	}

	public Movement addMovement(String usrEmail, float diruKantitatea, String mota) {
		Movement mov = new Movement(usrEmail, diruKantitatea, mota);
		mugimenduak.add(mov);
		return mov;
	}

}
