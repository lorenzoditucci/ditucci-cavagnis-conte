package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.dto.UserDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean {
	
	@EJB
	private UserMgr userMgr;

	private UserDTO user;
	
	public RegisterBean() {
		user = new UserDTO();
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public String register() {
		userMgr.save(user);
		return "home?faces-redirect=true";
	}
}
