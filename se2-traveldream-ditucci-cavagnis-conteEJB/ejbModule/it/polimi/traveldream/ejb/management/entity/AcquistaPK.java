package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Acquista database table.
 * 
 */
@Embeddable
public class AcquistaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int idPacchetto;

	public AcquistaPK() {
	}
	
	public int getIdPacchetto() {
		return this.idPacchetto;
	}
	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AcquistaPK)) {
			return false;
		}
		AcquistaPK castOther = (AcquistaPK)other;
		return 
			(this.idPacchetto == castOther.idPacchetto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPacchetto;
		
		return hash;
	}
}