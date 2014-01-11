package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.pacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

/**
 * bean per la gestione dei pacchetti
 */
@ManagedBean(name="pacchettoBean")
@javax.faces.bean.SessionScoped
public class PacchettoBean {
	
	@EJB
	private pacchettoMgr pacchettoMgr;
	
	private ArrayList<PacchettoDTO> risultato;
    /**
     * Default constructor. 
     */
    public PacchettoBean() {
        setRisultato(new ArrayList<PacchettoDTO>());
    }
    
    public void cercaAll(){
    	/*
    	 * Prendi tutti?? non avevamo detto che si prendevano gli ultimi aggiunti?
    	 * */
    	setRisultato(pacchettoMgr.prendiTutti());
    	
    }

	public ArrayList<PacchettoDTO> getRisultato() {
		return risultato;
	}

	public void setRisultato(ArrayList<PacchettoDTO> risultato) {
		this.risultato = risultato;
	}

}
