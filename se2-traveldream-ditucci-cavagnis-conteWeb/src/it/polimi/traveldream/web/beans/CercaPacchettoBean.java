package it.polimi.traveldream.web.beans;


import it.polimi.traveldream.ejb.management.cercaPacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
 * Session Bean implementation class CercaPacchettoBean
 */
@ManagedBean(name="cercaPacchettoBean")
@SessionScoped
public class CercaPacchettoBean {
	
	@EJB
	private cercaPacchettoMgr mgr;
	
	private ArrayList<PacchettoDTO> risultato;
	private int idPacchetto;
	private String nomePacchetto;
	private String emailPacchetto;
	
	

    /**
     * Default constructor. 
     */
    public CercaPacchettoBean() {
        // TODO Auto-generated constructor stub
    }



	public int getIdPacchetto() {
		return idPacchetto;
	}



	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}



	public ArrayList<PacchettoDTO> getRisultato() {
		return risultato;
	}



	public void setRisultato(ArrayList<PacchettoDTO> risultato) {
		this.risultato = risultato;
	}



	public String getNomePacchetto() {
		return nomePacchetto;
	}



	public void setNomePacchetto(String nomePacchetto) {
		this.nomePacchetto = nomePacchetto;
	}
	
	public String cercaPacchetti(){
		System.out.println("cercapacchetti - faccio controllo");
		if(emailPacchetto.equals("") && nomePacchetto.equals("")){
			FacesContext.getCurrentInstance().addMessage("ricercaPacchetto:nome-pacchetto", new FacesMessage(FacesMessage.SEVERITY_ERROR,"campiVuoti", "campi vuoti - ricerca nulla!"));
			return "";
		}
		setRisultato(mgr.cercaPacchettiParam(emailPacchetto, nomePacchetto));
		
		return "risultatiRicercaPacchetti?faces-redirect=true";
	}



	public String getEmailPacchetto() {
		return emailPacchetto;
	}



	public void setEmailPacchetto(String emailPacchetto) {
		this.emailPacchetto = emailPacchetto;
	}

}
