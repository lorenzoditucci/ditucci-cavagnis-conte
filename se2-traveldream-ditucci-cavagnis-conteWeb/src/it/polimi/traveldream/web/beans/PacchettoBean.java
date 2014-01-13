package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.pacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 * bean per la gestione dei pacchetti
 */
@ManagedBean(name="pacchettoBean")
@javax.faces.bean.SessionScoped
public class PacchettoBean {
	
	/**
	 * questo serve x recuperare il bean in questione
	 * per utilizzare cioe' la mail dell'utente
	 */
	@ManagedProperty("#{userBean.mail}")
	private String userEmail;
	
	@EJB
	private pacchettoMgr pacchettoMgr;
	
	private ArrayList<PacchettoDTO> risultato;
    
	@PostConstruct
	public void init() {
		setRisultato(new ArrayList<PacchettoDTO>());
        userEmail = new String();
	}
	
	
    public PacchettoBean() {
        
    }
    
    public void cercaAll(){
    	/*
    	 * Prendi tutti?? non avevamo detto che si prendevano gli ultimi aggiunti?
    	 * */
    	setRisultato(pacchettoMgr.prendiTutti());
    	
    }

    public void cercaAcquistati(String mail){
    	setUserEmail(mail);
    	setRisultato(pacchettoMgr.prendiAcquistati(mail));
    	
    }
	public ArrayList<PacchettoDTO> getRisultato() {
		return risultato;
	}

	public void setRisultato(ArrayList<PacchettoDTO> risultato) {
		this.risultato = risultato;
	}


	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
