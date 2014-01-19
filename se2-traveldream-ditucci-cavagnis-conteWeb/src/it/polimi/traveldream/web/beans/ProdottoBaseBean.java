package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.ProdottoBaseMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * lo uso per gestire i prodotti base acquistabili
 * @author ditu
 */
@ManagedBean(name="prodottoBaseBean")
@SessionScoped
public class ProdottoBaseBean {

	@EJB
	private ProdottoBaseMgr mgr;
    /**
     * Default constructor. 
     */
    public ProdottoBaseBean() {
        // TODO Auto-generated constructor stub
    }
    
    public String acquistaProdottoBase(VoloDTO volo,GiftListDTO giftList){
    	/**
    	 * non so se andra' messo qua o ci mettiamo una pagina nel mezzo
    	 * nel caso spostiamo il tutto in un altra funzione
    	 */
    	
    	/**
    	 * devo registrare che il prodotto base e' stato acquistato
    	 */
    	mgr.registraAcquisto(volo,giftList);
    	return "acquista?faces-redirect=true";
    }
    
    
    public String acquistaProdottoBase(EscursioneDTO escursione,GiftListDTO giftList){
    	mgr.registraAcquisto(escursione,giftList);
    	return "acquista?faces-redirect=true";
    }
    
    public String acquistaProdottoBase(PernottamentoDTO pernottamento,GiftListDTO giftList){
    	mgr.registraAcquisto(pernottamento, giftList);
    	return "acquista?faces-redirect=true";
    }

}
