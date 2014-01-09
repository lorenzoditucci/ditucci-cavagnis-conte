package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the VoliAcquistati database table.
 * 
 */
@Embeddable
public class VoliAcquistatiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int idGiftList;

	@Column(unique=true, nullable=false)
	private int idVolo;

	public VoliAcquistatiPK() {
	}
	public int getIdGiftList() {
		return this.idGiftList;
	}
	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
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
		if (!(other instanceof VoliAcquistatiPK)) {
			return false;
		}
		VoliAcquistatiPK castOther = (VoliAcquistatiPK)other;
		return 
			(this.idGiftList == castOther.idGiftList)
			&& (this.idVolo == castOther.idVolo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGiftList;
		hash = hash * prime + this.idVolo;
		
		return hash;
	}
}