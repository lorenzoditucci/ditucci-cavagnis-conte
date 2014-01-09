package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the GiftList database table.
 * 
 */
@Entity
@Table(name="GiftList")
@NamedQuery(name="GiftList.findAll", query="SELECT g FROM GiftList g")
public class GiftList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idGiftList;

	@Column(nullable=false, length=45)
	private String mailCliente;

	@Column(nullable=false, length=45)
	private String nome;

	/**
	 * connessione bidirezionale many to many con gli hotel
	 */
	@ManyToMany
	@JoinTable(
		name="HotelAcquistati"
		, joinColumns={
			@JoinColumn(name="idGiftList", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="idHotel", nullable=false)
			}
		)
	private List<Hotel> hotels;
	
	/**
	 * connessione bidirezionale many to many con i voli
	 */
	@ManyToMany
		@JoinTable(
			name="VoliAcquistati"
			, joinColumns={
				@JoinColumn(name="idGiftList", nullable=false)
				}
			, inverseJoinColumns={
				@JoinColumn(name="idVolo", nullable=false)
				}
			)
		private List<Volo> voli;

	/**
	 * connessione bidirezionale many to many con le escursioni
	 */
	
	@ManyToMany
		@JoinTable(
				name="EscursioniAcquistate"
				, joinColumns={
						@JoinColumn(name="idGiftList", nullable=false)
				}
				, inverseJoinColumns={
						@JoinColumn(name="idEscursione", nullable=false)
				}
				)
		private List<Escursione> escursioni;
	
	/**
	 * connessione pacchetto - Contiene
	 */
	
	@ManyToMany
	@JoinTable(
			name="Contiene"
			, joinColumns={
					@JoinColumn(name="idGiftList", nullable=false)
			}
			, inverseJoinColumns={
					@JoinColumn(name="idPacchetto", nullable=false)
			})
	private List<Pacchetto> pacchettiContenuti;
	


	
	public GiftList() {
	}

	public int getIdGiftList() {
		return this.idGiftList;
	}

	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
	}

	public String getMailCliente() {
		return this.mailCliente;
	}

	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Hotel> getHotels() {
		return this.hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}
	
	/**
	 * associazione many to many con Gift List
	 */
	
		@ManyToMany(mappedBy="giftLists")
		private List<Pacchetto> pacchetti;

}