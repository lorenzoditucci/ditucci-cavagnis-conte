package it.polimi.traveldream.ejb.management.dto;

/**
 * done
 */


import java.sql.Timestamp;
import java.util.List;



import org.hibernate.validator.constraints.NotEmpty;

public class EscursioneDTO {

	@NotEmpty
	private int idEscursione;

	@NotEmpty
	private byte acquistato;

	@NotEmpty
	private String citta;

	@NotEmpty
	private double costo;

	@NotEmpty
	private Timestamp dataFine;

	@NotEmpty
	private Timestamp dataInizio;

	@NotEmpty
	private String descrizione;

	@NotEmpty
	private String nome;
	
	@NotEmpty
	private List<GiftListDTO> giftLists;
		
	@NotEmpty
	private List<PacchettoDTO> pacchetti;

	public int getIdEscursione() {
		return idEscursione;
	}

	public void setIdEscursione(int idEscursione) {
		this.idEscursione = idEscursione;
	}

	public byte getAcquistato() {
		return acquistato;
	}

	public void setAcquistato(byte acquistato) {
		this.acquistato = acquistato;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Timestamp getDataFine() {
		return dataFine;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}

	public Timestamp getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<GiftListDTO> getGiftLists() {
		return giftLists;
	}

	public void setGiftLists(List<GiftListDTO> giftLists) {
		this.giftLists = giftLists;
	}

	public List<PacchettoDTO> getPacchetti() {
		return pacchetti;
	}

	public void setPacchetti(List<PacchettoDTO> pacchetti) {
		this.pacchetti = pacchetti;
	}
	
	
}
