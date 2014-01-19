package it.polimi.traveldream.web.beans;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.UserDTO;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


@ManagedBean(name="inserisciPacchettoInGLBean")
@javax.faces.bean.SessionScoped
public class InserisciPacchettoInGLBean {

	@EJB
	GiftListMgr glBean;
	
	private PacchettoDTO pDTO;
	
	@ManagedProperty("#{userBean.mail}")
	private String userEmail;
	
	
	private GiftListDTO glDto;
	
	private List<GiftListDTO> risultatoRicerca;
	
	public InserisciPacchettoInGLBean() {
		this.pDTO = new PacchettoDTO();
		this.userEmail = new String();
		this.risultatoRicerca = new ArrayList<GiftListDTO>();
	}
	
	public String inserisciPacchetto(PacchettoDTO pacchettoDTO){
		this.pDTO = pacchettoDTO;
		System.out.println("inserisci pacchetto");
		return "inserisciPacchettoInGL?faces-redirect=true";
	}
	
	public void cercaPerMail(){
		
		this.setRisultatoRicerca(glBean.cercaGLperMail(userEmail));
		return;
	}
	
	public void foo(){
		System.out.println("foo");
	}

	public PacchettoDTO getpDTO() {
		return pDTO;
	}

	public void setpDTO(PacchettoDTO pDTO) {
		this.pDTO = pDTO;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public GiftListDTO getGlDto() {
		return glDto;
	}

	public void setGlDto(GiftListDTO glDto) {
		this.glDto = glDto;
	}

	public List<GiftListDTO> getRisultatoRicerca() {
		return risultatoRicerca;
	}

	public void setRisultatoRicerca(List<GiftListDTO> risultatoRicerca) {
		this.risultatoRicerca = risultatoRicerca;
	}
	
	
	
	
}
