package it.polimi.traveldream.ejb.management.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class EscursioniAcquistateDTO {

	@NotEmpty
	private int idEscursioniAcquistate;
	
	@NotEmpty
	private int idGiftList;
	
	@NotEmpty
	private int idEscursione;
	
	@NotEmpty
	private Timestamp dataAcquisto;

	@NotEmpty
	private String nomeAcquirente;

	public int getIdEscursioniAcquistate() {
		return idEscursioniAcquistate;
	}

	public void setIdEscursioniAcquistate(int idEscursioniAcquistate) {
		this.idEscursioniAcquistate = idEscursioniAcquistate;
	}

	public int getIdGiftList() {
		return idGiftList;
	}

	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
	}

	public int getIdEscursione() {
		return idEscursione;
	}

	public void setIdEscursione(int idEscursione) {
		this.idEscursione = idEscursione;
	}

	public Timestamp getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(Timestamp dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public String getNomeAcquirente() {
		return nomeAcquirente;
	}

	public void setNomeAcquirente(String nomeAcquirente) {
		this.nomeAcquirente = nomeAcquirente;
	}
}
