package it.polimi.traveldream.web.beans;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.dto.AcquistaDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.UserDTO;

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
	//private ArrayList<PacchettoDTO> pacchettiAcquistati;
	private List<AcquistaDTO> acquisti;
	
	public UserBean(){
		
	}
	
	@PostConstruct
	public void init() {
		setMailCurrentUser();
		//setPacchettiAcquistati(userMgr.getUserDTO().getPacchettiAcquistati());
		//acquisti = userMgr.acquisti(userMgr.getUserDTO()); 
	}
	
	
	public List<AcquistaDTO> getAcquisti() {
		return acquisti;
	}

	public void setAcquisti(List<AcquistaDTO> acquisti) {
		this.acquisti = acquisti;
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

	public String pacchettiAcquistati(){
		acquisti = userMgr.acquisti(userMgr.getUserDTO()); 
		return "pacchettiAcquistati";
	}

}
