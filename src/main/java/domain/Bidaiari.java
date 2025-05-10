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

import domain.Eskaera.EskaeraEgoera;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bidaiari extends User implements Serializable{
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Eskaera> eskaerak=new ArrayList<Eskaera>();

	public Bidaiari() {}
	
	public Bidaiari(String name, String pasahitza, String email, String nanZbk) {
		super(name, pasahitza, email, nanZbk);
		eskaerak = new ArrayList<Eskaera>();
	}

	
	public void removeEskaera() {
		for (Eskaera esk: eskaerak) {
			if(esk.getEgoera() == EskaeraEgoera.ACCEPTED) {
				float itzuliDiruDriver = esk.getRide().getPrice()*esk.getNPlaces();
				esk.getRide().getDriver().diruSartuDri(itzuliDiruDriver);
			}
			esk.setEgoera(EskaeraEgoera.CANCELLED);
		}
				
	}
	
	
	public void ordaindu(float diru) {
		super.setDirua(super.getDirua()-diru);
	}
	
	public void diruSartuBid (float diru) {
		super.setDirua(super.getDirua()+diru);		
	}
 
	
	public ArrayList<Eskaera> getEskaerak() {
        return eskaerak;
    }
}
