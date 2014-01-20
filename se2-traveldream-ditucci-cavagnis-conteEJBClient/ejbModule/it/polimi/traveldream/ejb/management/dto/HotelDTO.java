package it.polimi.traveldream.ejb.management.dto;
/**
 *  Done
 * 
 */

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;



public class HotelDTO {
	
	
	private int idHotel;
	
	@NotNull
	private int acquistato;

	@NotEmpty
	private String citta;

	@NotNull
	@Min(1)
	private int classe;

	@NotNull
	@Min(1)
	private double costo;

	@NotEmpty
	private String descrizione;

	@NotEmpty
	private String indirizzo;

	@NotEmpty
	private String nome;

	public int getAcquistato() {
		return acquistato;
	}

	public void setAcquistato(int acquistato) {
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

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

}
