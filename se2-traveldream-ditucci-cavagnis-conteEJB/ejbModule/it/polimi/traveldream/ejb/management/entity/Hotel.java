package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Hotel database table.
 * 
 */
@Entity
@Table(name="Hotel")
@NamedQueries({
	@NamedQuery(name="Hotel.findAll", query="SELECT h FROM Hotel h"),
	@NamedQuery(name="Hotel.cercaHotelNome", query="SELECT h FROM Hotel h WHERE h.nome= :nome")
})
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(unique=true, nullable=false)
	private int idHotel;

	@Column(nullable=false)
	private byte acquistato;

	@Column(nullable=false, length=45)
	private String citta;

	@Column(nullable=false)
	private int classe;

	@Column(nullable=false)
	private double costo;

	@Column(nullable=false, length=45)
	private String descrizione;

	@Column(nullable=false, length=45)
	private String indirizzo;

	@Column(nullable=false, length=45)
	private String nome;

	//bi-directional many-to-many association to GiftList
	@ManyToMany(mappedBy="hotels")
	private List<GiftList> giftLists;
	
	/**
	 * associazione many to many con Gift List
	 */
	
		@ManyToMany(mappedBy="hotels")
		private List<GiftList> pacchetti;

	public Hotel() {
	}

	public Hotel(HotelDTO hotel) {
			this.citta=hotel.getCitta();
			this.classe=hotel.getClasse();
			this.costo=hotel.getCosto();
			this.descrizione=hotel.getDescrizione();
			this.nome=hotel.getNome();
			this.indirizzo=hotel.getIndirizzo();
			this.acquistato=0; /*a 0 di default in creazione*/	
	}

	public int getIdHotel() {
		return this.idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public byte getAcquistato() {
		return this.acquistato;
	}

	public void setAcquistato(byte acquistato) {
		this.acquistato = acquistato;
	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public int getClasse() {
		return this.classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<GiftList> getGiftLists() {
		return this.giftLists;
	}

	public void setGiftLists(List<GiftList> giftLists) {
		this.giftLists = giftLists;
	}

}