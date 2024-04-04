package domain;

import java.util.Date;

public class Movement {
	
	private User user;
	private double diruKop;
	private Date data;
	
	public Movement(User user, double diruKop, Date data) {
		super();
		this.user = user;
		this.diruKop = diruKop;
		this.data = data;
	}

	public double getDiruKop() {
		return diruKop;
	}

	public void setDiruKop(double diruKop) {
		this.diruKop = diruKop;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "[diruKop=" + diruKop + ", data=" + data + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
