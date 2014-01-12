package it.polimi.traveldream.web.beans;

import java.util.ArrayList;

import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="giftListBean")
@RequestScoped
public class GiftListBean {
	
	@EJB
	private GiftListMgr giftListMgr;

	private GiftListDTO giftList;
	
	@ManagedProperty("#{userBean.mail}")
	private String userEmail;
	
	private ArrayList<GiftListDTO> risultatoRicerca;

	public ArrayList<GiftListDTO> getRisultatoRicerca() {
		return risultatoRicerca;
	}


	public void setRisultatoRicerca(ArrayList<GiftListDTO> risultatoRicerca) {
		this.risultatoRicerca = risultatoRicerca;
	}


	public GiftListBean() {
		giftList = new GiftListDTO();
		userEmail = new String();
		risultatoRicerca = new ArrayList<GiftListDTO>();
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

	public String aggiungiGiftList() {
		giftList.setMailCliente(userEmail);
		giftListMgr.save(giftList);	
		return "gestioneGL/gestione";
	}
	
	public String cercaGlPerMail(){
		setRisultatoRicerca(giftListMgr.cercaGLperMail(this.userEmail));
		return "gestione?faces-redirect=true";
	}
}
