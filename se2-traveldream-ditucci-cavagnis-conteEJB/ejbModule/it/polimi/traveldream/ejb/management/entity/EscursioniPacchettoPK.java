package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EscursioniPacchetto database table.
 * 
 */
@Embeddable
public class EscursioniPacchettoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int idPacchetto;

	@Column(unique=true, nullable=false)
	private int idEscursione;

	public EscursioniPacchettoPK() {
	}
	public int getIdPacchetto() {
		return this.idPacchetto;
	}
	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}
	public int getIdEscursione() {
		return this.idEscursione;
	}
	public void setIdEscursione(int idEscursione) {
		this.idEscursione = idEscursione;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EscursioniPacchettoPK)) {
			return false;
		}
		EscursioniPacchettoPK castOther = (EscursioniPacchettoPK)other;
		return 
			(this.idPacchetto == castOther.idPacchetto)
			&& (this.idEscursione == castOther.idEscursione);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPacchetto;
		hash = hash * prime + this.idEscursione;
		
		return hash;
	}
}