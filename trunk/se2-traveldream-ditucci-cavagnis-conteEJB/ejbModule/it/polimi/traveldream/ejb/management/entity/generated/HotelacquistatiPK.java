package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the hotelacquistati database table.
 * 
 */
@Embeddable
public class HotelacquistatiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_giftlist", insertable=false, updatable=false, unique=true, nullable=false)
	private int idGiftlist;

	@Column(name="id_hotel", insertable=false, updatable=false, unique=true, nullable=false)
	private int idHotel;

	public HotelacquistatiPK() {
	}
	public int getIdGiftlist() {
		return this.idGiftlist;
	}
	public void setIdGiftlist(int idGiftlist) {
		this.idGiftlist = idGiftlist;
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
		if (!(other instanceof HotelacquistatiPK)) {
			return false;
		}
		HotelacquistatiPK castOther = (HotelacquistatiPK)other;
		return 
			(this.idGiftlist == castOther.idGiftlist)
			&& (this.idHotel == castOther.idHotel);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGiftlist;
		hash = hash * prime + this.idHotel;
		
		return hash;
	}
}