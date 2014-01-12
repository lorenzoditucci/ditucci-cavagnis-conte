package it.polimi.traveldream.web.beans;

import java.util.ArrayList;

import it.polimi.traveldream.ejb.management.CercaGiftListMgr;
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
	private CercaGiftListMgr cercaMGR;
	//private GiftListDTO ricercaGiftList;//CHE MINCHIA E'?
	/**
	 * qui ci salvo il risultato della ricerca
	 */
	private ArrayList<GiftListDTO> risultatoRicerca;
	/**
	 * parametro che passo contente l'id della gift list da cercare.
	 */
	private String idRicerca; //stringa per passare
    /**
     * Default constructor. 
     */
    public CercaGiftListBean() {
      //  ricercaGiftList= new GiftListDTO();
        setRisultatoRicerca(new ArrayList<GiftListDTO>());
    }

    public String cerca(){
    	try {
    		setRisultatoRicerca(cercaMGR.cerca(Integer.parseInt(idRicerca)));
        	System.out.println("sto cercando...");
        	/**
        	 * metodo per la redirect
        	 */
        	return "giftList?faces-redirect=true";
		} catch (Exception e) {
			setRisultatoRicerca(new ArrayList<GiftListDTO>());
			getRisultatoRicerca().get(0).setNome("Non trovato, Riprova");
			return null;
		}
    	
    	
    }


	public String getIdRicerca() {
		return idRicerca;
	}

	public void setIdRicerca(String idRicerca) {
		this.idRicerca = idRicerca;
	}

	public ArrayList<GiftListDTO> getRisultatoRicerca() {
		return risultatoRicerca;
	}

	public void setRisultatoRicerca(ArrayList<GiftListDTO> risultatoRicerca) {
		this.risultatoRicerca = risultatoRicerca;
	}
}
