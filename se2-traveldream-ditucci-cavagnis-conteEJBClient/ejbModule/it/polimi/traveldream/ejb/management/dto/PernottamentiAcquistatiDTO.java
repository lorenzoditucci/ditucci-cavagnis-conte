package it.polimi.traveldream.ejb.management.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class PernottamentiAcquistatiDTO {
	
	@NotEmpty
	private int idPernottamentoAcquistato;
	
	@NotEmpty
	private int idGiftList;
	
	@NotEmpty
	private int idPernottamento;
	
	@NotEmpty
	private Timestamp dataAcquisto;
	
	@NotEmpty
	private String nomeAcquirente;

	public int getIdPernottamentoAcquistato() {
		return idPernottamentoAcquistato;
	}

	public void setIdPernottamentoAcquistato(int idPernottamentoAcquistato) {
		this.idPernottamentoAcquistato = idPernottamentoAcquistato;
	}

	public int getIdGiftList() {
		return idGiftList;
	}

	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
	}

	public int getIdPernottamento() {
		return idPernottamento;
	}

	public void setIdPernottamento(int idPernottamento) {
		this.idPernottamento = idPernottamento;
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