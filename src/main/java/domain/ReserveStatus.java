package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class ReserveStatus {
	
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private int reserveNumber;
	private boolean accepted;
	private boolean answered;
	private double frozenBalance;
	private String email;
	private static int count = 0;
	
	public int getReserveNumber() {
		return reserveNumber;
	}

	public void setReserveNumber(int reserveNumber) {
		this.reserveNumber = reserveNumber;
	}

	public ReserveStatus(double frozenBalance, String email) {
		this.accepted = false;
		this.answered = false;
		this.frozenBalance = frozenBalance;
		this.email = email;
		reserveNumber = count;
		count++;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}

	public double getFrozenBalance() {
		return frozenBalance;
	}

	public void setFrozenBalance(double frozenBalance) {
		this.frozenBalance = frozenBalance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
