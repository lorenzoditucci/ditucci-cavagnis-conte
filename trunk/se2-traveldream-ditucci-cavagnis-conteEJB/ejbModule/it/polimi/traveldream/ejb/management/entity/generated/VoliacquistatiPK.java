package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the voliacquistati database table.
 * 
 */
@Embeddable
public class VoliacquistatiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_giftlist", insertable=false, updatable=false, unique=true, nullable=false)
	private int idGiftlist;

	@Column(name="id_volo", insertable=false, updatable=false, unique=true, nullable=false)
	private int idVolo;

	public VoliacquistatiPK() {
	}
	public int getIdGiftlist() {
		return this.idGiftlist;
	}
	public void setIdGiftlist(int idGiftlist) {
		this.idGiftlist = idGiftlist;
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
		if (!(other instanceof VoliacquistatiPK)) {
			return false;
		}
		VoliacquistatiPK castOther = (VoliacquistatiPK)other;
		return 
			(this.idGiftlist == castOther.idGiftlist)
			&& (this.idVolo == castOther.idVolo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGiftlist;
		hash = hash * prime + this.idVolo;
		
		return hash;
	}
}