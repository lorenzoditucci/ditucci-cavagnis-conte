package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.UserMgr;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class UserBean {
	
	@EJB
	private UserMgr userMgr;
	
	public String getName() {
		return userMgr.getUserDTO().getFirstName(); //chiamo l'oggetto e poi chiedo il nome
	}
}
