package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="gestioneGiftListBean")
@ViewScoped

public class GestioneGiftListBean {
	@EJB
	private GiftListMgr giftListMgr;
	@EJB
	private UserMgr userMgr;
	
	private GiftListDTO giftList;
	
	//@ManagedProperty("#{userBean.mail}")
	private String userEmail;
	
	private ArrayList<GiftListDTO> risultatoRicerca;
	
	public GestioneGiftListBean(){
		this.giftList = new GiftListDTO();
		this.userEmail = new String();
		this.risultatoRicerca = new ArrayList<GiftListDTO>();
	}
	
	public GiftListDTO getGiftList() {
		return giftList;
	}

	public void setGiftList(GiftListDTO giftList) {
		this.giftList = giftList;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public ArrayList<GiftListDTO> getRisultatoRicerca() {
		return risultatoRicerca;
	}

	public void setRisultatoRicerca(ArrayList<GiftListDTO> risultatoRicerca) {
		this.risultatoRicerca = risultatoRicerca;
	}
	
	public String cercaGlPerMail(){
		userEmail = userMgr.getUserDTO().getEmail();
		setRisultatoRicerca(giftListMgr.cercaGLperMail(this.userEmail));
		return "gestione?faces-redirect=true";
	}

}
