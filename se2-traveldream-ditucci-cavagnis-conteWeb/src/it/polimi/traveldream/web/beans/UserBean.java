package it.polimi.traveldream.web.beans;

import java.util.ArrayList;

import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.jboss.weld.context.ejb.Ejb;

@ManagedBean
@SessionScoped
public class UserBean {
	
	@EJB
	private UserMgr userMgr;
	
	private String mail;
	private ArrayList<PacchettoDTO> pacchettiAcquistati;
	
	public UserBean(){
		
	}
	
	@PostConstruct
	public void init() {
		setMailCurrentUser();
		//setPacchettiAcquistati(userMgr.getUserDTO().getPacchettiAcquistati());
		pacchettiAcquistati = new ArrayList<PacchettoDTO>();
	}
	
	public String getName() {
		return userMgr.getUserDTO().getFirstName(); //chiamo l'oggetto e poi chiedo il nome
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMailCurrentUser(){
		this.mail = userMgr.getUserDTO().getEmail();
	}

	public ArrayList<PacchettoDTO> getPacchettiAcquistati() {
		return pacchettiAcquistati;
	}

	public void setPacchettiAcquistati(ArrayList<PacchettoDTO> pacchettiAcquistati) {
		this.pacchettiAcquistati = pacchettiAcquistati;
	}

}
