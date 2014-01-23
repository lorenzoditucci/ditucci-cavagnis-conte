package it.polimi.traveldream.ejb.management.dto;



import java.sql.Timestamp;
import org.hibernate.validator.constraints.NotEmpty;



public class VoliAcquistatiProvaDTO {

	@NotEmpty
	private int idVoloAcquistato;
	
	@NotEmpty
	private int idGiftList;
	
	@NotEmpty
	private int idVolo;
	
	@NotEmpty
	private Timestamp dataAcquisto;

	@NotEmpty
	private String nomeAcquirente;
	
	public VoliAcquistatiProvaDTO(){
		
	}

	public int getIdVoloAcquistato() {
		return idVoloAcquistato;
	}

	public void setIdVoloAcquistato(int idVoloAcquistato) {
		this.idVoloAcquistato = idVoloAcquistato;
	}

	public int getIdGiftList() {
		return idGiftList;
	}

	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
	}

	public Timestamp getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(Timestamp dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public int getIdVolo() {
		return idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public String getNomeAcquirente() {
		return nomeAcquirente;
	}

	public void setNomeAcquirente(String nomeAcquirente) {
		this.nomeAcquirente = nomeAcquirente;
	}
	
}
