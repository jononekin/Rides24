package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Movement implements Serializable{

	@Id
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer movementNumber;
	private float diruKantitatea;
	private String mota;
	@XmlIDREF
	private User usr;

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public Movement(Integer movementNumber, User user, float diruKantitatea, String mota) {
		this.diruKantitatea = diruKantitatea;
		this.mota = mota;
		this.usr = user;
	}

	public Movement(User user, float diruKantitatea, String mota) {
		this.diruKantitatea = diruKantitatea;
		this.mota = mota;
		this.usr = user;
	}

	public Movement() {
		super();
	}

	public float getdiruKantitatea() {
		return diruKantitatea;
	}

	public void setdiruKantitatea(float diruKantitatea) {
		this.diruKantitatea = diruKantitatea;
	}

	public Integer getMovementNumber() {
		return movementNumber;
	}

	public void setMovementNumber(Integer movementNumber) {
		this.movementNumber = movementNumber;
	}

	public User getUsr() {
		return usr;
	}

	public void setUsrEmail(User usr) {
		this.usr = usr;
	}

	@Override
	public String toString() {

		return this.getMota() + " " + String.format("%.2f", this.getdiruKantitatea()) + "€";
	}

}