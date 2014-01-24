package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.AcquistaPacchettoMgr;
import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.cercaPacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.UserDTO;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Session Bean implementation class SelezionaPacchettoBean
 */
@ManagedBean(name="selezionaPacchettoBean")
@SessionScoped
public class SelezionaPacchettoBean {
	
	@EJB
	private cercaPacchettoMgr mgr;
	@EJB
	private AcquistaPacchettoMgr acquistaPacchettoMgr;
	@EJB
	private UserMgr userMgr;
	
	private PacchettoDTO pacchettoSelezionato;
	
    /**
     * Default constructor. 
     */
    public SelezionaPacchettoBean() {
        // TODO Auto-generated constructor stub
    }
    
    public String selezionaPacchetto(int idPacchetto){
    	pacchettoSelezionato = mgr.cercaPacchettoId(idPacchetto).get(0);
    	return "visualizzaPacchetto?faces-redirect=true";
    	
    }

	public PacchettoDTO getPacchettoSelezionato() {
		return pacchettoSelezionato;
	}

	public void setPacchettoSelezionato(PacchettoDTO pacchettoSelezionato) {
		this.pacchettoSelezionato = pacchettoSelezionato;
	}
	
	public String acquistaPacchetto(PacchettoDTO p){
		UserDTO userDTO = userMgr.getUserDTO();
		if(!acquistaPacchettoMgr.aggiungiAcquisto(userDTO,p)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Il pacchetto non è più disponibile", "Non è possibile comprare questo pacchetto perchè i "
					+ "posti sono esauriti."));
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Il pacchetto è stato acquistato correttamente", ""));
		return "index";
	}

}
