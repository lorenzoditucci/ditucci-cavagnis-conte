package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.entity.User;
import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Impiegato
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("impiegato")
public class Impiegato extends User implements Serializable {

	
	private Integer matricola;
	private static final long serialVersionUID = 1L;

	public Impiegato() {
		super();
	}   
	public Integer getMatricola() {
		return this.matricola;
	}

	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}
   
}
