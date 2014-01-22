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
	private List<PernottamentoDTO> pernottamenti;
	
	@NotEmpty
	private List<VoliAcquistatiProvaDTO> voli;

	@NotEmpty
	private List<EscursioneDTO> escursioni;
	
	@NotEmpty
	private List<PacchettoDTO> pacchettiContenuti;
	
	public GiftListDTO(){
		this.pernottamenti = new ArrayList<PernottamentoDTO>(); 
		this.pacchettiContenuti = new ArrayList<PacchettoDTO>();
		this.nome = new String();
		this.mailCliente = new String();
		this.voli = new ArrayList<VoliAcquistatiProvaDTO>();
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

	public List<PernottamentoDTO> getPernottamenti() {
		return pernottamenti;
	}

	public void setPernottamenti(List<PernottamentoDTO> pernottamenti) {
		this.pernottamenti = pernottamenti;
	}

	public List<VoliAcquistatiProvaDTO> getVoli() {
		return voli;
	}

	public void setVoli(List<VoliAcquistatiProvaDTO> voli) {
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
	

}
