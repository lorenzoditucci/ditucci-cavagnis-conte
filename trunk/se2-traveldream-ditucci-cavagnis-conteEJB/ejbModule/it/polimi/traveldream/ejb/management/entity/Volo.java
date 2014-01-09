package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Volo database table.
 * 
 */
@Entity
@Table(name="Volo")
@NamedQuery(name="Volo.findAll", query="SELECT v FROM Volo v")
public class Volo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idVolo;

	@Column(nullable=false)
	private byte acquistato;

	@Column(nullable=false, length=45)
	private String cittaArrivo;

	@Column(nullable=false, length=45)
	private String cittaPartenza;

	@Column(nullable=false, length=45)
	private String compagnia;

	@Column(nullable=false)
	private double costo;

	@Column(nullable=false)
	private Timestamp dataFine;

	@Column(nullable=false)
	private Timestamp dataInizio;

	public Volo() {
	}

	public int getIdVolo() {
		return this.idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public byte getAcquistato() {
		return this.acquistato;
	}

	public void setAcquistato(byte acquistato) {
		this.acquistato = acquistato;
	}

	public String getCittaArrivo() {
		return this.cittaArrivo;
	}

	public void setCittaArrivo(String cittaArrivo) {
		this.cittaArrivo = cittaArrivo;
	}

	public String getCittaPartenza() {
		return this.cittaPartenza;
	}

	public void setCittaPartenza(String cittaPartenza) {
		this.cittaPartenza = cittaPartenza;
	}

	public String getCompagnia() {
		return this.compagnia;
	}

	public void setCompagnia(String compagnia) {
		this.compagnia = compagnia;
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
	
	/**
	 * associazione many to many con Gift List
	 */
	
		@ManyToMany(mappedBy="voli")
		private List<GiftList> giftLists;
}