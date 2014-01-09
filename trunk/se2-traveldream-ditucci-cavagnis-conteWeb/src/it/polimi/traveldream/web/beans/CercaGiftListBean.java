package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.CercaMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Session Bean implementation class CercaGiftList
 */
@ManagedBean(name="cercaGiftListBean")
@RequestScoped
public class CercaGiftListBean {

	@EJB
	private CercaMgr cercaMGR;
	private GiftListDTO ricercaGiftList;
    /**
     * Default constructor. 
     */
    public CercaGiftListBean() {
        ricercaGiftList= new GiftListDTO();
    }

    public void cerca(){
    	
    }

	public GiftListDTO getRicerca() {
		return ricercaGiftList;
	}

	public void setRicerca(GiftListDTO ricerca) {
		this.ricercaGiftList = ricerca;
	}
}
