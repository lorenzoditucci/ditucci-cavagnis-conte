package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the hotelacquistati database table.
 * 
 */
@Entity
@Table(name="hotelacquistati")
@NamedQuery(name="Hotelacquistati.findAll", query="SELECT h FROM Hotelacquistati h")
public class Hotelacquistati implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HotelacquistatiPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_acquisto", nullable=false)
	private Date dataAcquisto;

	@Column(name="nome_acquirente", nullable=false, length=45)
	private String nomeAcquirente;

	//uni-directional many-to-one association to Giftlist
	@ManyToOne
	@JoinColumn(name="id_giftlist", nullable=false, insertable=false, updatable=false)
	private Giftlist giftlist;

	//uni-directional many-to-one association to Hotel
	@ManyToOne
	@JoinColumn(name="id_hotel", nullable=false, insertable=false, updatable=false)
	private Hotel hotel;

	public Hotelacquistati() {
	}

	public HotelacquistatiPK getId() {
		return this.id;
	}

	public void setId(HotelacquistatiPK id) {
		this.id = id;
	}

	public Date getDataAcquisto() {
		return this.dataAcquisto;
	}

	public void setDataAcquisto(Date dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public String getNomeAcquirente() {
		return this.nomeAcquirente;
	}

	public void setNomeAcquirente(String nomeAcquirente) {
		this.nomeAcquirente = nomeAcquirente;
	}

	public Giftlist getGiftlist() {
		return this.giftlist;
	}

	public void setGiftlist(Giftlist giftlist) {
		this.giftlist = giftlist;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}