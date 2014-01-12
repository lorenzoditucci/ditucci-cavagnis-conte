package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.UserDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the GiftList database table.
 * 
 */
@Entity
@Table(name="GiftList")
@NamedQueries({
	@NamedQuery(name="GiftList.findAll", query="SELECT g FROM GiftList g"),
	@NamedQuery(name="cercaGiftList", query="SELECT g FROM GiftList g WHERE g.idGiftList= :id"),
	@NamedQuery(name="cercaGiftListPerMail", query = "SELECT g FROM GiftList g WHERE g.mailCliente = :mail")
})

public class GiftList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
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

	//TODO: è sbagliato creare nuovi ArrayList quindi devo creare metodo che da Lista di DTO mi genera lista di 
	//entità normali.
	public GiftList(GiftListDTO giftList) {
		this.mailCliente = giftList.getMailCliente();
		this.nome = giftList.getNome();
		this.escursioni = new ArrayList<Escursione>();
		this.hotels = new ArrayList<Hotel>();
		this.pacchetti = new ArrayList<Pacchetto>();
		this.pacchettiContenuti = new ArrayList<Pacchetto>();
		this.voli = new ArrayList<Volo>();
		
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
	
	
	
	public List<Volo> getVoli() {
		return voli;
	}

	public void setVoli(List<Volo> voli) {
		this.voli = voli;
	}

	public List<Escursione> getEscursioni() {
		return escursioni;
	}

	public void setEscursioni(List<Escursione> escursioni) {
		this.escursioni = escursioni;
	}

	public List<Pacchetto> getPacchettiContenuti() {
		return pacchettiContenuti;
	}

	public void setPacchettiContenuti(List<Pacchetto> pacchettiContenuti) {
		this.pacchettiContenuti = pacchettiContenuti;
	}

	public List<Pacchetto> getPacchetti() {
		return pacchetti;
	}

	public void setPacchetti(List<Pacchetto> pacchetti) {
		this.pacchetti = pacchetti;
	}


	/**
	 * associazione many to many con Gift List
	 */
	
		@ManyToMany(mappedBy="giftLists")
		private List<Pacchetto> pacchetti;


}