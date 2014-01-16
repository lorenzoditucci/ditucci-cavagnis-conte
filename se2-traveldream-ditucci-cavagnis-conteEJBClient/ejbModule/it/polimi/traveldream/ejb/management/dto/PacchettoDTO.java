package it.polimi.traveldream.ejb.management.dto;

/**
 * Done
 */




import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class PacchettoDTO {
	@NotEmpty
	private int idPacchetto;

	@NotNull
	@Min(1)
	private double costo;

	@NotNull
	private Timestamp dataFine;

	@NotNull
	private Timestamp dataInizio;

	@NotEmpty
	private String descrizione;

	@NotNull
	private int disponibilitaAttuale;

	@NotNull
	@Min(1)
	private int disponibilitaMax;

	@NotEmpty
	private String mail;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private List<VoloDTO> voli;
	
	@NotEmpty
	private List<CittaDTO> cittaDestinazione;
	
	private List<PernottamentoDTO> pernotti;
	
	private List<EscursioneDTO> escursioni;
	
	private List<UserDTO> users;
	
	private List<GiftListDTO> giftLists;
	
	public PacchettoDTO(){
		this.cittaDestinazione=new ArrayList<CittaDTO>();
		this.pernotti=new ArrayList<PernottamentoDTO>();
		this.voli=new ArrayList<VoloDTO>();
		this.escursioni=new ArrayList<EscursioneDTO>();	
	}
	
	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

	public List<GiftListDTO> getGiftLists() {
		return giftLists;
	}

	public void setGiftLists(List<GiftListDTO> giftLists) {
		this.giftLists = giftLists;
	}

	public List<EscursioneDTO> getEscursioni() {
		return escursioni;
	}

	public void setEscursioni(List<EscursioneDTO> escursioni) {
		this.escursioni = escursioni;
	}

	public List<CittaDTO> getCittaDestinazione() {
		return cittaDestinazione;
	}

	public void setCittaDestinazione(List<CittaDTO> cittaDestinazione) {
		this.cittaDestinazione = cittaDestinazione;
	}

	public List<VoloDTO> getVoli() {
		return voli;
	}

	public void setVoli(List<VoloDTO> voli) {
		this.voli = voli;
	}


	public int getIdPacchetto() {
		return idPacchetto;
	}

	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
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

	public int getDisponibilitaAttuale() {
		return disponibilitaAttuale;
	}

	public void setDisponibilitaAttuale(int disponibilitaAttuale) {
		this.disponibilitaAttuale = disponibilitaAttuale;
	}

	public int getDisponibilitaMax() {
		return disponibilitaMax;
	}

	public void setDisponibilitaMax(int disponibilitaMax) {
		this.disponibilitaMax = disponibilitaMax;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PernottamentoDTO> getPernotti() {
		return pernotti;
	}

	public void setPernotti(List<PernottamentoDTO> pernotti) {
		this.pernotti = pernotti;
	}
	
	

}
