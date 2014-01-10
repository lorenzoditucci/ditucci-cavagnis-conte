package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Pacchetto database table.
 * 
 */
@Entity
@Table(name="Pacchetto")
@NamedQuery(name="Pacchetto.findAll", query="SELECT p FROM Pacchetto p")
public class Pacchetto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue
	private int idPacchetto;

	@Column(nullable=false)
	private double costo;

	@Column(nullable=false)
	private Timestamp dataFine;

	@Column(nullable=false)
	private Timestamp dataInizio;

	@Column(nullable=false, length=45)
	private String descrizione;

	@Column(nullable=false)
	private int disponibilitaAttuale;

	@Column(nullable=false)
	private int disponibilitaMax;

	@Column(nullable=false, length=45)
	private String mail;

	@Column(nullable=false, length=45)
	private String nome;

	public Pacchetto() {
	}

	public int getIdPacchetto() {
		return this.idPacchetto;
	}

	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Timestamp getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}

	public Timestamp getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getDisponibilitaAttuale() {
		return this.disponibilitaAttuale;
	}

	public void setDisponibilitaAttuale(int disponibilitaAttuale) {
		this.disponibilitaAttuale = disponibilitaAttuale;
	}

	public int getDisponibilitaMax() {
		return this.disponibilitaMax;
	}

	public void setDisponibilitaMax(int disponibilitaMax) {
		this.disponibilitaMax = disponibilitaMax;
	}


	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * connessione bidirezionale many to many con gli hotel
	 */
	
	@ManyToMany
		@JoinTable(
				name="Pernottamento"
				, joinColumns={
						@JoinColumn(name="idPacchetto", nullable=false)
				}
				, inverseJoinColumns={
						@JoinColumn(name="idHotel", nullable=false)
				}
				)
		private List<Hotel> hotels;
	
	/**
	 * connessione con i voli (voliPacchetto)
	 */
	
	@ManyToMany
		@JoinTable(
				name="VoliPacchetto"
				, joinColumns={
						@JoinColumn(name="idPacchetto", nullable=false)
				}
				, inverseJoinColumns={
						@JoinColumn(name="idVolo", nullable=false)
				})
		private List<Volo> voli;
	
	/**
	 * connessione con escursioni (escursioniPacchetto)
	 */
	
	@ManyToMany
		@JoinTable(
				name="EscursioniPacchetto"
				, joinColumns={
						@JoinColumn(name="idPacchetto", nullable=false)
				}
				, inverseJoinColumns={
						@JoinColumn(name="idEscursione", nullable=false)
				})
		private List<Escursione> escursioni;
	
	/**
	 * connessione giftlist - Contiene
	 */
	
	@ManyToMany
	@JoinTable(
			name="Contiene"
			, joinColumns={
					@JoinColumn(name="idPacchetto", nullable=false)
			}
			, inverseJoinColumns={
					@JoinColumn(name="idGiftList", nullable=false)
			})
	private List<GiftList> giftLists;
	
	/**
	 * connessione citta' - destinazione
	 */
	@ManyToMany
	@JoinTable(
			name="Destinazione"
			, joinColumns={
					@JoinColumn(name="idPacchetto", nullable=false)
			}
			, inverseJoinColumns={
					@JoinColumn(name="idCitta", nullable=false)
			})
	private List<Citta> cittaDestinazione;
	
	/**
	 * associazione con User - acquista
	 */
	
		@ManyToMany(mappedBy="pacchetti")
		private List<User> users;
	
}