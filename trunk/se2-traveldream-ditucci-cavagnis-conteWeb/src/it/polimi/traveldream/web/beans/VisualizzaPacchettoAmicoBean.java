package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.pacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="visualizzaPacchettoAmicoBean")
@javax.faces.bean.SessionScoped
public class VisualizzaPacchettoAmicoBean {

	@EJB
	private pacchettoMgr pacchettoMgr;
	
	private ArrayList<PacchettoDTO> risultato;
	private int idPacchetto;
	private String mailCreatore;
		
	public String prendiDaBarraIndirizziParametro(){
		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
		setIdPacchetto(id);
		String mailCreatore = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mail");
		setMailCreatore(mailCreatore);
		return visualizzaPacchettoAmico(id,mailCreatore);
	}
	
	private String visualizzaPacchettoAmico(int id,String mailCreatore) {
    	setRisultato(pacchettoMgr.prendiPerIdEMail(id,mailCreatore));
    	return "visualizzaPacchettoAmico?faces-redirect=true";
	}

	public ArrayList<PacchettoDTO> getRisultato() {
		return risultato;
	}

	public void setRisultato(ArrayList<PacchettoDTO> risultato) {
		this.risultato = risultato;
	}

	public int getIdPacchetto() {
		return idPacchetto;
	}

	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	public String getMailCreatore() {
		return mailCreatore;
	}

	public void setMailCreatore(String mailCreatore) {
		this.mailCreatore = mailCreatore;
	}
}
