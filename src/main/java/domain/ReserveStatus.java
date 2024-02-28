package domain;

public class ReserveStatus {
 
	private boolean accepted;
	private boolean answered;
	private double frozenBalance;
	private Traveler traveler;
	
	public ReserveStatus(double frozenBalance, Traveler traveler) {
		this.accepted = false;
		this.answered = false;
		this.frozenBalance = frozenBalance;
		this.traveler = traveler;
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

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}
}
