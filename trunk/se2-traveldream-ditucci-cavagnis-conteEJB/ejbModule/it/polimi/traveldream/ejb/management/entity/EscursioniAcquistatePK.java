package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EscursioniAcquistate database table.
 * 
 */
@Embeddable
public class EscursioniAcquistatePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int idGiftList;

	@Column(unique=true, nullable=false)
	private int idEscursione;

	public EscursioniAcquistatePK() {
	}
	public int getIdGiftList() {
		return this.idGiftList;
	}
	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
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
		if (!(other instanceof EscursioniAcquistatePK)) {
			return false;
		}
		EscursioniAcquistatePK castOther = (EscursioniAcquistatePK)other;
		return 
			(this.idGiftList == castOther.idGiftList)
			&& (this.idEscursione == castOther.idEscursione);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGiftList;
		hash = hash * prime + this.idEscursione;
		
		return hash;
	}
}