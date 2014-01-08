package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the pernottamento database table.
 * 
 */
@Embeddable
public class PernottamentoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_pacchetto", insertable=false, updatable=false, unique=true, nullable=false)
	private int idPacchetto;

	@Column(name="id_hotel", insertable=false, updatable=false, unique=true, nullable=false)
	private int idHotel;

	public PernottamentoPK() {
	}
	public int getIdPacchetto() {
		return this.idPacchetto;
	}
	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}
	public int getIdHotel() {
		return this.idHotel;
	}
	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PernottamentoPK)) {
			return false;
		}
		PernottamentoPK castOther = (PernottamentoPK)other;
		return 
			(this.idPacchetto == castOther.idPacchetto)
			&& (this.idHotel == castOther.idHotel);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPacchetto;
		hash = hash * prime + this.idHotel;
		
		return hash;
	}
}