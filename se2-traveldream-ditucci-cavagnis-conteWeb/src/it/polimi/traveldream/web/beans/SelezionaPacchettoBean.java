package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.AcquistaPacchettoMgr;
import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;
import it.polimi.traveldream.ejb.management.cercaPacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
	@EJB
	private VisualizzaDettagliGLMgr glMgr;
	
	private PacchettoDTO pacchettoSelezionato;
	
    /**
     * Default constructor. 
     */
    public SelezionaPacchettoBean() {
        pacchettoSelezionato = new PacchettoDTO();
    }
    
    public String selezionaPacchetto(int idPacchetto){
    	pacchettoSelezionato = mgr.cercaPacchettoId(idPacchetto).get(0);
    	List<PernottamentoDTO> pernottamenti = getPernottamenti(pacchettoSelezionato);
    	pacchettoSelezionato.setPernotti(pernottamenti);
    	return "visualizzaPacchetto?faces-redirect=true";
    	
    }
    
    private List<PernottamentoDTO> getPernottamenti(PacchettoDTO p) {
		List<PernottamentoDTO> pernottamenti = new ArrayList<PernottamentoDTO>();
		pernottamenti = glMgr.cercaPernottamentiDaPacchetto(p);
		return pernottamenti;
	}
    
    public String selezionaPacchettoDTO(PacchettoDTO pacchetto){
    	pacchettoSelezionato = pacchetto;
    	pacchettoSelezionato.setPernotti(getPernottamenti(pacchetto));
    	return "visualizzaPacchetto"; 
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
