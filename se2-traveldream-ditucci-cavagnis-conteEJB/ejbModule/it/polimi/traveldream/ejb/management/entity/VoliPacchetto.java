package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the VoliPacchetto database table.
 * 
 */
@Entity
@Table(name="VoliPacchetto")
@NamedQuery(name="VoliPacchetto.findAll", query="SELECT v FROM VoliPacchetto v")
public class VoliPacchetto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VoliPacchettoPK id;

	public VoliPacchetto() {
	}

	public VoliPacchettoPK getId() {
		return this.id;
	}

	public void setId(VoliPacchettoPK id) {
		this.id = id;
	}

}