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
public class Bidaiari extends User implements Serializable{
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Eskaera> eskaerak=new Vector<Eskaera>();

	public Bidaiari() {}
	
	public Bidaiari(String email, String name) {
		super(email, name);
	}

	
	public Eskaera removeEskaera(String from, String to, Date date) {
		boolean found=false;
		int index=0;
		Eskaera e=null;
		while (!found && index<=eskaerak.size()) {
			e=eskaerak.get(++index);
			if ( (java.util.Objects.equals(e.getFrom(),from)) && (java.util.Objects.equals(e.getTo(),to)) && (java.util.Objects.equals(e.getDate(),date)) )
			found=true;
		}
			
		if (found) {
			eskaerak.remove(index);
			return e;
		} else return null;
	}
	
	public Eskaera addEskaera(String from, String to, Date date, float prez)  {
        Eskaera eskaera=new Eskaera(from,to,date,this, prez);
        eskaerak.add(eskaera);
        return eskaera;
	}
	public boolean doesEskaeraExists(String from, String to, Date date)  {	
		for (Eskaera e:eskaerak)
			if ( (java.util.Objects.equals(e.getFrom(),from)) && (java.util.Objects.equals(e.getTo(),to)) && (java.util.Objects.equals(e.getDate(),date)) )
			 return true;
		
		return false;
	}
	
	public List<Eskaera> getEskaerak() {
        return eskaerak;
    }
}
