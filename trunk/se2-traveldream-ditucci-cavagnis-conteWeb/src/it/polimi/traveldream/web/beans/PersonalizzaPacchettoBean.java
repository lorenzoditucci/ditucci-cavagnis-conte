package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="personalizzaPacchettoBean")
@SessionScoped
public class PersonalizzaPacchettoBean {

	private PacchettoDTO pacchetto;

	public PacchettoDTO getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(PacchettoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}
	
	public String personalizza(PacchettoDTO p){
		this.pacchetto = p;
		return "personalizza";
	}
	
	
}
