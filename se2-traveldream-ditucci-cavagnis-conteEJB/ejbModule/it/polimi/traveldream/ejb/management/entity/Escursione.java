package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Escursione database table.
 * 
 */
@Entity
@Table(name="Escursione")
@NamedQueries({
	@NamedQuery(name="Escursione.findAll", query="SELECT e FROM Escursione e"),
	@NamedQuery(name="Escursione.cercaEscursioneId", query="SELECT e FROM Escursione e WHERE e.idEscursione = :idEscursione")
})
public class Escursione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(unique=true, nullable=false)
	private int idEscursione;

	@Column(nullable=false)
	private byte acquistato;

	@Column(nullable=false, length=45)
	private String citta;

	@Column(nullable=false)
	private double costo;

	@Column(nullable=false)
	private Timestamp dataFine;

	@Column(nullable=false)
	private Timestamp dataInizio;

	@Column(nullable=false, length=45)
	private String descrizione;

	@Column(nullable=false, length=45)
	private String nome;

	public Escursione() {
	}
	
	public Escursione(EscursioneDTO escursione) {
		this.nome=escursione.getNome();
		this.descrizione=escursione.getDescrizione();
		this.dataInizio=new Timestamp(escursione.getDataInizio().getTime());
		this.dataFine=new Timestamp(escursione.getDataFine().getTime());
		this.costo=escursione.getCosto();
		this.citta=escursione.getCitta();
		this.acquistato=0; /*a 0 di default in creazione*/
	}

	public int getIdEscursione() {
		return this.idEscursione;
	}

	public void setIdEscursione(int idEscursione) {
		this.idEscursione = idEscursione;
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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * associazione many to many con Gift List
	 */
	
		@ManyToMany(mappedBy="escursioni")
		private List<GiftList> giftLists;
		
	/**
	 * associazione con Pacchetto - escursioniPacchetto
	 */

		@ManyToMany(mappedBy="escursioni")
		private List<Pacchetto> pacchetti;
}