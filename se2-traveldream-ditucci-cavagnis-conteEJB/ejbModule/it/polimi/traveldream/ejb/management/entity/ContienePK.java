package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Contiene database table.
 * 
 */
@Embeddable
public class ContienePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int idGiftList;

	@Column(unique=true, nullable=false)
	private int idPacchetto;

	public ContienePK() {
	}
	public int getIdGiftList() {
		return this.idGiftList;
	}
	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
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
		if (!(other instanceof ContienePK)) {
			return false;
		}
		ContienePK castOther = (ContienePK)other;
		return 
			(this.idGiftList == castOther.idGiftList)
			&& (this.idPacchetto == castOther.idPacchetto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGiftList;
		hash = hash * prime + this.idPacchetto;
		
		return hash;
	}
}