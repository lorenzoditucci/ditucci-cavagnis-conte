package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EscursioniPacchetto database table.
 * 
 */
@Entity
@Table(name="EscursioniPacchetto")
@NamedQuery(name="EscursioniPacchetto.findAll", query="SELECT e FROM EscursioniPacchetto e")
public class EscursioniPacchetto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EscursioniPacchettoPK id;

	public EscursioniPacchetto() {
	}

	public EscursioniPacchettoPK getId() {
		return this.id;
	}

	public void setId(EscursioniPacchettoPK id) {
		this.id = id;
	}

}