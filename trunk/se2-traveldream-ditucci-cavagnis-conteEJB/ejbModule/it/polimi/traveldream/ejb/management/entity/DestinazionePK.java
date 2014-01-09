package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Destinazione database table.
 * 
 */
@Embeddable
public class DestinazionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int idPacchetto;

	@Column(unique=true, nullable=false)
	private int idCitta;

	public DestinazionePK() {
	}
	public int getIdPacchetto() {
		return this.idPacchetto;
	}
	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}
	public int getIdCitta() {
		return this.idCitta;
	}
	public void setIdCitta(int idCitta) {
		this.idCitta = idCitta;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DestinazionePK)) {
			return false;
		}
		DestinazionePK castOther = (DestinazionePK)other;
		return 
			(this.idPacchetto == castOther.idPacchetto)
			&& (this.idCitta == castOther.idCitta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPacchetto;
		hash = hash * prime + this.idCitta;
		
		return hash;
	}
}