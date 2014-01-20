package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the Pernottamento database table.
 * 
 */
@Entity
@Table(name="Pernottamento")
@NamedQuery(name="Pernottamento.findAll", query="SELECT p FROM Pernottamento p")
public class Pernottamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(unique=true, nullable=false)
	private int idPernottametto;
	
	@Column(nullable=false)
	private Timestamp dataInizio;

	@Column(nullable=false)
	private Timestamp dataFine;
	
	@OneToOne(optional = false)
	private Hotel hotel;
	
	@OneToOne(optional = false)
	private Pacchetto pacchetto;

	public Pernottamento() {
	}
	
	public Pernottamento(PernottamentoDTO pernottamento) {
		this.dataInizio=pernottamento.getDataInizio();
		this.dataFine=pernottamento.getDataFine();
	}

	public int getIdPernottametto() {
		return idPernottametto;
	}
	
	public void setIdPernottametto(int idPernottametto) {
		this.idPernottametto = idPernottametto;
	}
	
	public Timestamp getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Timestamp getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Timestamp numeroNotti) {
		this.dataFine = numeroNotti;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Pacchetto getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(Pacchetto pacchetto) {
		this.pacchetto = pacchetto;
	}
	
	

}