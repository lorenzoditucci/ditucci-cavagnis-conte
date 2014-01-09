package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Contiene database table.
 * 
 */
@Entity
@Table(name="Contiene")
@NamedQuery(name="Contiene.findAll", query="SELECT c FROM Contiene c")
public class Contiene implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ContienePK id;

	public Contiene() {
	}

	public ContienePK getId() {
		return this.id;
	}

	public void setId(ContienePK id) {
		this.id = id;
	}

}