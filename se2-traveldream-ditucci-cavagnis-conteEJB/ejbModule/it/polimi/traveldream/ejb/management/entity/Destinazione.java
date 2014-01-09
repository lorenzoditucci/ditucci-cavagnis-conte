package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Destinazione database table.
 * 
 */
@Entity
@Table(name="Destinazione")
@NamedQuery(name="Destinazione.findAll", query="SELECT d FROM Destinazione d")
public class Destinazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DestinazionePK id;

	public Destinazione() {
	}

	public DestinazionePK getId() {
		return this.id;
	}

	public void setId(DestinazionePK id) {
		this.id = id;
	}

}