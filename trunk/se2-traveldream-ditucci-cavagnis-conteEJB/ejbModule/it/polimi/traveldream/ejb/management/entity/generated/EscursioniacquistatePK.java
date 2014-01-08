package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the escursioniacquistate database table.
 * 
 */
@Embeddable
public class EscursioniacquistatePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_giftlist", insertable=false, updatable=false, unique=true, nullable=false)
	private int idGiftlist;

	@Column(name="id_escursione", insertable=false, updatable=false, unique=true, nullable=false)
	private int idEscursione;

	public EscursioniacquistatePK() {
	}
	public int getIdGiftlist() {
		return this.idGiftlist;
	}
	public void setIdGiftlist(int idGiftlist) {
		this.idGiftlist = idGiftlist;
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
		if (!(other instanceof EscursioniacquistatePK)) {
			return false;
		}
		EscursioniacquistatePK castOther = (EscursioniacquistatePK)other;
		return 
			(this.idGiftlist == castOther.idGiftlist)
			&& (this.idEscursione == castOther.idEscursione);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGiftlist;
		hash = hash * prime + this.idEscursione;
		
		return hash;
	}
}