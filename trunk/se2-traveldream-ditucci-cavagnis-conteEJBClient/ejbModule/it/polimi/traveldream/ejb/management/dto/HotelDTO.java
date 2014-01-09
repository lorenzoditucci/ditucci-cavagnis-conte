package it.polimi.traveldream.ejb.management.dto;

import java.util.List;
import it.polimi.traveldream.ejb.management.dto.*;

import org.hibernate.validator.constraints.NotEmpty;



public class HotelDTO {
	
	@NotEmpty
	private int idHotel;

	@NotEmpty
	private byte acquistato;

	@NotEmpty
	private String citta;

	@NotEmpty
	private int classe;

	@NotEmpty
	private double costo;

	@NotEmpty
	private String descrizione;

	@NotEmpty
	private String indirizzo;

	@NotEmpty
	private String nome;
	
	@NotEmpty
	private List<GiftListDTO> giftLists;
	@NotEmpty
	private List<GiftListDTO> pacchetti;

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
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

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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

	public List<GiftListDTO> getPacchetti() {
		return pacchetti;
	}

	public void setPacchetti(List<GiftListDTO> pacchetti) {
		this.pacchetti = pacchetti;
	}
	
	

}
