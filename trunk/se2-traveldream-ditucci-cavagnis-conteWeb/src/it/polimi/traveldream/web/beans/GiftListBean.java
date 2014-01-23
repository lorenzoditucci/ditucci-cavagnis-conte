package it.polimi.traveldream.web.beans;

import java.util.ArrayList;

import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="giftListBean")
@RequestScoped
public class GiftListBean {
	
	@EJB
	private GiftListMgr giftListMgr;

	@EJB
	private UserMgr userMgr;
	
	private GiftListDTO giftList;
	
	@ManagedProperty("#{userBean.mail}")
	private String userEmail;


	
	public GiftListBean(){
		giftList = new GiftListDTO();
		userEmail = new String();	
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
		userEmail = userMgr.getUserDTO().getEmail();
		giftList.setMailCliente(userEmail);
		giftListMgr.save(giftList);	
		return "gestione?faces-redirect=true";
	}
		
}
