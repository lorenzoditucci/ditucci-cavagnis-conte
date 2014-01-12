package it.polimi.traveldream.ejb.management.dto;

/**
 * done
 */
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class GiftListDTO {

	@NotEmpty
	private int idGiftList;

	@NotEmpty
	private String mailCliente;

	@NotEmpty
	private String nome;

	@NotEmpty
	private List<HotelDTO> hotels;
	
	@NotEmpty
	private List<VoloDTO> voli;

	@NotEmpty
	private List<EscursioneDTO> escursioni;
	
	@NotEmpty
	private List<PacchettoDTO> pacchettiContenuti;
	
	@NotEmpty
	private List<PacchettoDTO> pacchetti;

	public GiftListDTO(){
		this.pacchetti = new ArrayList<PacchettoDTO>();
		this.hotels = new ArrayList<HotelDTO>(); 
		this.pacchettiContenuti = new ArrayList<PacchettoDTO>();
		this.nome = new String();
		this.mailCliente = new String();
		this.voli = new ArrayList<VoloDTO>();
		this.escursioni = new ArrayList<EscursioneDTO>();
	}
	
	public int getIdGiftList() {
		return idGiftList;
	}

	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
	}

	public String getMailCliente() {
		return mailCliente;
	}

	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<HotelDTO> getHotels() {
		return hotels;
	}

	public void setHotels(List<HotelDTO> hotels) {
		this.hotels = hotels;
	}

	public List<VoloDTO> getVoli() {
		return voli;
	}

	public void setVoli(List<VoloDTO> voli) {
		this.voli = voli;
	}

	public List<EscursioneDTO> getEscursioni() {
		return escursioni;
	}

	public void setEscursioni(List<EscursioneDTO> escursioni) {
		this.escursioni = escursioni;
	}

	public List<PacchettoDTO> getPacchettiContenuti() {
		return pacchettiContenuti;
	}

	public void setPacchettiContenuti(List<PacchettoDTO> pacchettiContenuti) {
		this.pacchettiContenuti = pacchettiContenuti;
	}

	public List<PacchettoDTO> getPacchetti() {
		return pacchetti;
	}

	public void setPacchetti(List<PacchettoDTO> pacchetti) {
		this.pacchetti = pacchetti;
	}
	
	

}
