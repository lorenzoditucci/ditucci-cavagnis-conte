package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * lo uso per gestire i prodotti base acquistabili
 * @author ditu
 */
@ManagedBean(name="prodottoBaseBean")
@SessionScoped
public class ProdottoBaseBean {

    /**
     * Default constructor. 
     */
    public ProdottoBaseBean() {
        // TODO Auto-generated constructor stub
    }
    
    public String acquistaProdottoBase(VoloDTO volo){
    	return "acquista?faces-redirect=true";
    }
    
    
    public String acquistaProdottoBase(EscursioneDTO escursione){
    	return "acquista?faces-redirect=true";
    }
    
    public String acquistaProdottoBase(PernottamentoDTO pernottamento){
    	return "acquista?faces-redirect=true";
    }

}
