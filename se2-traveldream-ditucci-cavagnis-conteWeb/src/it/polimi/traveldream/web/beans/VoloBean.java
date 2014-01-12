package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="voloBean")
@RequestScoped
public class VoloBean {
	
	@EJB
	private VoloMgr voloMgr;

	private VoloDTO volo;
	
	public VoloDTO getVolo() {
		return volo;
	}

	public void setVolo(VoloDTO volo) {
		this.volo = volo;
	}

	public VoloBean() {
		volo = new VoloDTO();
	}
	
	public String aggiungiVolo() {
		
		if (volo.getDataArrivo().getTime() < volo.getDataPartenza().getTime() || volo.getDataArrivo().equals(volo.getDataPartenza())) {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Intervallo date errato", "Non ha senso!!!"));
		     return "";
		}
		    
		if (volo.getCittaArrivo().equals(volo.getCittaPartenza())) {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Un volo non pu� arrivare nello stesso luogo", "Non ha senso!!!"));
		     return "";
		}
		
		voloMgr.save(volo);	
		return "aggiungiprodottobase";
	}
	
	public String rimuoviVolo(VoloDTO v) {
			if(v.getAcquistato()==0){
				voloMgr.remove(v.getIdVolo());
				return "cercaescursione";		
			}				
			else{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",  "Non puoi rimuovere un prodotto base che fa parte di un pacchetto acquistato");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return "";
			}
				
			
		}
	
}