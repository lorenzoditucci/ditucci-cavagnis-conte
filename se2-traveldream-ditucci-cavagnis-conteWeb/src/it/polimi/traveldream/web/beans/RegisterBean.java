package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.dto.UserDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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
		try {
			userMgr.save(user);
			return "home-success?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("registrazione:email", new FacesMessage(FacesMessage.SEVERITY_ERROR,"mail duplicata", "Mail gia' registrata"));
			return "";
		}
		
	}
}
