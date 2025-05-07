package domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Alerta implements Serializable{
	@Id @XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private int ID;	
	public enum AlertMota{
		DIRUA_SARTU,
		DIRUA_ATERA,
		ESKAERA_KANTZELATU, //Bidaiari - Gidari
		BIDAIA_KANTZELATU, //Bidaiari
		ESKAERA_ONARTU, //Bidaiari
		ESKAERA_EGIN, //Gidari
		ESKAERA_EZONARTUA, //Bidaiari
		BIDAIA_AMAITUTA, //Bidaiari
		BALORATUTA
	}
	private AlertMota mota; 
	private User user;

	public Alerta(User user, AlertMota mota ) {	
		this.user=user;
		this.mota=mota;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}


	public AlertMota getMota() {
		return mota;
	}

	public void setMota(AlertMota mota) {
		this.mota = mota;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
