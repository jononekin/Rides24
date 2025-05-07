package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Erreklamazioa {
	@Id 
	@GeneratedValue
	private Integer id;
	private User errekJarri;
	private User errekJaso;
	private Eskaera eskaera;
	private String deskribapena;
	private ErrekMota mota;
	private float diru;
	public enum ErrekMota{
		PENDING,
		REJECTED,
		ACCEPTED,
	}
	private ErrekLarri larri;
	public enum ErrekLarri{
		TXIKIA,
		ERTAINA,
		HANDIA,
	}
	
	public Erreklamazioa(User errekJarri, User errekJaso, Eskaera eskaera, String deskribapena, ErrekMota mota,
			float diru, ErrekLarri larri) {
		super();
		this.errekJarri = errekJarri;
		this.errekJaso = errekJaso;
		this.eskaera = eskaera;
		this.deskribapena = deskribapena;
		this.mota = mota;
		this.diru = diru;
		this.larri=larri;
	}
	public Erreklamazioa(User errekJarri, User errekJaso, Eskaera eskaera, String deskribapena, ErrekMota mota) {
		super();
		this.errekJarri = errekJarri;
		this.errekJaso = errekJaso;
		this.eskaera = eskaera;
		this.deskribapena = deskribapena;
		this.mota = mota;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getErrekJarri() {
		return errekJarri;
	}
	public void setErrekJarri(User errekJarri) {
		this.errekJarri = errekJarri;
	}
	public User getErrekJaso() {
		return errekJaso;
	}
	public void setErrekJaso(User errekJaso) {
		this.errekJaso = errekJaso;
	}
	public Eskaera getEskaera() {
		return eskaera;
	}
	public void setEskaera(Eskaera eskaera) {
		this.eskaera = eskaera;
	}
	public String getDeskribapena() {
		return deskribapena;
	}
	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}
	public ErrekMota getMota() {
		return mota;
	}
	public void setMota(ErrekMota mota) {
		this.mota = mota;
	}
	public float getDiru() {
		return diru;
	}
	public void setDiru(float diru) {
		this.diru = diru;
	}
	

}
