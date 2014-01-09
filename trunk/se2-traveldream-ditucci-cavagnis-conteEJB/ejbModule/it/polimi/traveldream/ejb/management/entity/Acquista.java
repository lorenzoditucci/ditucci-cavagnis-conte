package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Acquista database table.
 * 
 */
@Entity
@Table(name="Acquista")
@NamedQuery(name="Acquista.findAll", query="SELECT a FROM Acquista a")
public class Acquista implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AcquistaPK id;

	@Column(length=45)
	private String dataAcquisto;

	public Acquista() {
	}

	public AcquistaPK getId() {
		return this.id;
	}

	public void setId(AcquistaPK id) {
		this.id = id;
	}

	public String getDataAcquisto() {
		return this.dataAcquisto;
	}

	public void setDataAcquisto(String dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

}