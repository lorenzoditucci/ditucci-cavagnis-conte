package it.polimi.traveldream.web.beans;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.exception.CoerenzaException;
import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.UserDTO;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


@ManagedBean(name="inserisciPacchettoInGLBean")
@javax.faces.bean.SessionScoped
public class InserisciPacchettoInGLBean {

	@EJB
	GiftListMgr glBean;
	@EJB
	UserMgr userMgr;
	
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
		this.setpDTO(pacchettoDTO);
		return "inserisciPacchettoInGL.xhtml";
	}
	
	public String aggiungi(GiftListDTO gl){
		if(gl.getPacchettiContenuti().contains(pDTO)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore."
					+ " Il pacchetto selezionato è già inserito nella Gift List", ""));
			return "index";
		}
		try {
			glBean.aggiungiPacchetto(gl, pDTO);
		} catch (CoerenzaException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore di coerenza."
					+ e.getMessaggi().get(0), ""));
			return "index";
		}
		
		return "visualizzaPacchetto";
	}
	
	public void cercaPerMail(){
		this. userEmail = userMgr.getUserDTO().getEmail();
		this.setRisultatoRicerca(glBean.cercaGLperMail(userEmail));
		return;
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
