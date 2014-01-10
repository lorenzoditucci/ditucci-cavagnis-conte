package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.CercaMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 * Session Bean implementation class CercaGiftList
 */
@ManagedBean(name="cercaGiftListBean")
@ViewScoped
public class CercaGiftListBean {

	@EJB
	private CercaMgr cercaMGR;
	private GiftListDTO ricercaGiftList;//CHE MINCHIA E'?
	private GiftListDTO risultatoRicerca;
	private String idRicerca; //stringa per passare
    /**
     * Default constructor. 
     */
    public CercaGiftListBean() {
        ricercaGiftList= new GiftListDTO();
        risultatoRicerca= new GiftListDTO();
    }

    public void cerca(){
    	try {
    		setRisultatoRicerca(cercaMGR.cerca(Integer.parseInt(idRicerca)));
        	System.out.println("sto cercando...");
		} catch (Exception e) {
			risultatoRicerca = new GiftListDTO();
			risultatoRicerca.setNome("Non trovato, Riprova");
		}
    	
    }

	public GiftListDTO getRicerca() {
		return ricercaGiftList;
	}

	public void setRicerca(GiftListDTO ricerca) {
		this.ricercaGiftList = ricerca;
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
