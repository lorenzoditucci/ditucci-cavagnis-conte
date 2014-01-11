package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.UserMgr;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserBean {
	
	@EJB
	private UserMgr userMgr;
	
	private String mail;
	
	public UserBean(){
		
	}
	
	@PostConstruct
	public void init() {
		setMailCurrentUser();
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

}
