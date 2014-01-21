package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.CreaPacchettoMgr;
import it.polimi.traveldream.ejb.management.GestionePacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

@ManagedBean(name="gestionePacchettoBean")
@SessionScoped
public class GestionePacchettoBean {
	
	@EJB
	private GestionePacchettoMgr gestionePacchettoMgr;
	
	@NotNull
	private int idPacchettoDaCercare;
	
	private PacchettoDTO pacchetto;
	
	private String avviso;
	
	public PacchettoDTO getPacchetto() {
		return pacchetto;
	}


	public void setPacchetto(PacchettoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}


	public String cercaPacchetto(){
		this.pacchetto=gestionePacchettoMgr.cercaPacchettoId(idPacchettoDaCercare);
		
		if(this.pacchetto==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Pacchetto non trovato", "Non presente"));
		    return "";
		}else{
			return "dettagliPacchetto";	
		}	
	}
	
	public String eliminaPacchetto(){
		if(gestionePacchettoMgr.eliminaPacchettoId(pacchetto)){
			
			return "index.xhtml";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Non è possibile eliminare", "Non presente"));
		    return "";
		}
		
	}
	
	public String inviaComunicazione(){
		System.out.println(avviso);
		return null;
	}

	public int getIdPacchettoDaCercare() {
		return idPacchettoDaCercare;
	}

	public void setIdPacchettoDaCercare(int idPacchettoDaCercare) {
		this.idPacchettoDaCercare = idPacchettoDaCercare;
	}


	public String getAvviso() {
		return avviso;
	}


	public void setAvviso(String avviso) {
		this.avviso = avviso;
	}

}
