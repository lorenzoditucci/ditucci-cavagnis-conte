package it.polimi.traveldream.ejb.management.dto;

/**
 * done
 */
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class GiftListDTO {

	@NotEmpty
	private int idGiftList;

	@NotEmpty
	private String mailCliente;

	@NotEmpty
	private String nome;

	@NotEmpty
	private List<PernottamentiAcquistatiDTO> pernottamenti;
	
	@NotEmpty
	private List<VoliAcquistatiProvaDTO> voli;

	@NotEmpty
	private List<EscursioniAcquistateDTO> escursioni;
	
	@NotEmpty
	private List<PacchettoDTO> pacchettiContenuti;
	
	public GiftListDTO(){
		this.setPernottamenti(new ArrayList<PernottamentiAcquistatiDTO>()); 
		this.pacchettiContenuti = new ArrayList<PacchettoDTO>();
		this.nome = new String();
		this.mailCliente = new String();
		this.voli = new ArrayList<VoliAcquistatiProvaDTO>();
		this.setEscursioni(new ArrayList<EscursioniAcquistateDTO>());
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

	public List<VoliAcquistatiProvaDTO> getVoli() {
		return voli;
	}

	public void setVoli(List<VoliAcquistatiProvaDTO> voli) {
		this.voli = voli;
	}

	public List<PacchettoDTO> getPacchettiContenuti() {
		return pacchettiContenuti;
	}

	public void setPacchettiContenuti(List<PacchettoDTO> pacchettiContenuti) {
		this.pacchettiContenuti = pacchettiContenuti;
	}

	public List<PernottamentiAcquistatiDTO> getPernottamenti() {
		return pernottamenti;
	}

	public void setPernottamenti(List<PernottamentiAcquistatiDTO> pernottamenti) {
		this.pernottamenti = pernottamenti;
	}

	public List<EscursioniAcquistateDTO> getEscursioni() {
		return escursioni;
	}

	public void setEscursioni(List<EscursioniAcquistateDTO> escursioni) {
		this.escursioni = escursioni;
	}	
	

}
