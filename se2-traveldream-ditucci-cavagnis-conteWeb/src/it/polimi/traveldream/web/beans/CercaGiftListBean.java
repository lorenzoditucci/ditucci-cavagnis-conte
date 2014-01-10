package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.CercaMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 * Bean utilizzato per cercare i dati all'interno del database giftlist
 */
@ManagedBean(name="cercaGiftListBean")
@javax.faces.bean.SessionScoped
public class CercaGiftListBean {

	/**
	 * il manager della ricerca e' colui che effettua le vere operazioni tra cui le query
	 */
	@EJB
	private CercaMgr cercaMGR;
	//private GiftListDTO ricercaGiftList;//CHE MINCHIA E'?
	/**
	 * qui ci salvo il risultato della ricerca
	 */
	private GiftListDTO risultatoRicerca;
	/**
	 * parametro che passo contente l'id della gift list da cercare.
	 */
	private String idRicerca; //stringa per passare
    /**
     * Default constructor. 
     */
    public CercaGiftListBean() {
      //  ricercaGiftList= new GiftListDTO();
        risultatoRicerca= new GiftListDTO();
    }

    public String cerca(){
    	try {
    		setRisultatoRicerca(cercaMGR.cerca(Integer.parseInt(idRicerca)));
        	System.out.println("sto cercando...");
        	/**
        	 * metodo per la redirect
        	 */
        	return "userNonReg/giftList?faces-redirect=true";
		} catch (Exception e) {
			risultatoRicerca = new GiftListDTO();
			risultatoRicerca.setNome("Non trovato, Riprova");
			return null;
		}
    	
    	
    }

	public GiftListDTO getRisultatoRicerca() {
		return risultatoRicerca;
	}

	public void setRisultatoRicerca(GiftListDTO risultatoRicerca) {
		this.risultatoRicerca = risultatoRicerca;
	}

	public String getIdRicerca() {
		return idRicerca;
	}

	public void setIdRicerca(String idRicerca) {
		this.idRicerca = idRicerca;
	}
}
