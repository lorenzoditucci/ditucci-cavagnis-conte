package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.EscursioneMgr;
import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="escursioneBean")
@SessionScoped
public class EscursioneBean {
	
	@EJB
	private EscursioneMgr escursioneMgr;

	private EscursioneDTO escursione;

	public EscursioneDTO getEscursione() {
		return escursione;
	}

	public void setEscursione(EscursioneDTO escursione) {
		this.escursione = escursione;
	}

	public EscursioneBean() {
		escursione = new EscursioneDTO();
	}
	
	public String aggiungiEscursione() {
		
		if (escursione.getDataInizio().getTime() >= escursione.getDataFine().getTime()) {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Intervallo date errato", "Non ha senso!!!"));
		     return "";
		}
		
		escursioneMgr.save(escursione);	
		return "aggiungiprodottobase";
	}	
	
	
public String rimuoviEscursione(EscursioneDTO e) {
	
	
	if(escursioneMgr.controllaAppertenenzaPacchetto(e)){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Il pb fa parte di un pacchetto!",  "Non puoi rimuovere un prodotto base che fa parte di un pacchetto");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "";
	}
	
		if(e.getAcquistato()==0){
			escursioneMgr.remove(e.getIdEscursione());
			return "cercaescursione";		
		}				
		else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",  "Non puoi rimuovere un prodotto base che fa parte di un pacchetto acquistato");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
		}
		
	}


public String modificaEscursione(EscursioneDTO e){
	this.escursione=e;
	return "modificaEscursione.xhtml";
	
}

public String confermaModifiche(){
	
	if(escursioneMgr.controllaAppertenenzaPacchetto(escursione)){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Il prodotto base fa parte di un pacchetto! non si pu˜ modificare!", "Errore"));	
		return null;
	}
	
	if (escursione.getDataInizio().getTime() >= escursione.getDataFine().getTime()) {
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Intervallo date errato", "Non ha senso!!!"));
	     return null;
	}
	
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Modifiche eseguite", "ok"));	
	
	escursioneMgr.aggiornaModificheEscursione(escursione);
	
	return "cercaescursione";
}
	
}
