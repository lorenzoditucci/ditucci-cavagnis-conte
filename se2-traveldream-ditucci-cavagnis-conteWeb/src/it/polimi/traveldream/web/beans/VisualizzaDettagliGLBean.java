package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;

import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;


@ManagedBean(name="visualizzaDettagliGLBean")
@javax.faces.bean.SessionScoped
public class VisualizzaDettagliGLBean {
	
	@EJB
	private VisualizzaDettagliGLMgr visualizzaDettagliGLMgrMgr;

	private GiftListDTO giftList;
	
	
	public VisualizzaDettagliGLBean(){
		this.giftList = new GiftListDTO();
	}
	
	public String dettagli(GiftListDTO giftList){
		System.out.println("Funzione Dettagli");
		this.setGiftList(giftList);
		return "visualizza";
	}

	public String foo(){
		System.out.println("foo");
		return "visualizza";
	}
	public GiftListDTO getGiftList() {
		return giftList;
	}

	public void setGiftList(GiftListDTO giftList) {
		this.giftList = giftList;
	}

}
