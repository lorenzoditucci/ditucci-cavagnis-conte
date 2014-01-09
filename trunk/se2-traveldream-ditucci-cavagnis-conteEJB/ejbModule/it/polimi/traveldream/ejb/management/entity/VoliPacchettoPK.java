package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the VoliPacchetto database table.
 * 
 */
@Embeddable
public class VoliPacchettoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int idPacchetto;

	@Column(unique=true, nullable=false)
	private int idVolo;

	public VoliPacchettoPK() {
	}
	public int getIdPacchetto() {
		return this.idPacchetto;
	}
	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}
	public int getIdVolo() {
		return this.idVolo;
	}
	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof VoliPacchettoPK)) {
			return false;
		}
		VoliPacchettoPK castOther = (VoliPacchettoPK)other;
		return 
			(this.idPacchetto == castOther.idPacchetto)
			&& (this.idVolo == castOther.idVolo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPacchetto;
		hash = hash * prime + this.idVolo;
		
		return hash;
	}
}