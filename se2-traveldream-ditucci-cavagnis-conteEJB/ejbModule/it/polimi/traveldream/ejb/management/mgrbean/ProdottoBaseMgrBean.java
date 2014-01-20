package it.polimi.traveldream.ejb.management.mgrbean;

import it.polimi.traveldream.ejb.management.ProdottoBaseMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class ProdottoBaseMgrBean
 */
@Stateless
@Local(ProdottoBaseMgr.class)
@LocalBean
public class ProdottoBaseMgrBean implements ProdottoBaseMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
    /**
     * Default constructor. 
     */
    public ProdottoBaseMgrBean() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ProdottoBaseMgr#registraAcquistoVolo(VoloDTO)
     */
    public void registraAcquisto(VoloDTO volo,GiftListDTO giftList) {
    	/**
    	 * aggiungo il volo alla lista e faccio update della giftList
    	 * devo anche controllare che il booleano di acquistato sia ad uno?
    	 */
    	controllaAcquisto(volo);
        giftList.getVoli().add(volo);
        em.merge(giftList);
    }
    
    /**
     * controlla se il volo registrato e' gia' stato acquistato e nel caso lo registra
     * @param volo
     */
	private void controllaAcquisto(VoloDTO volo) {
		if(volo.getAcquistato()==0){
			volo.setAcquistato(1);
			em.merge(volo);
		}
		
	}

	@Override
	public void registraAcquisto(EscursioneDTO escursione, GiftListDTO giftList) {
		controllaAcquisto(escursione);
		giftList.getEscursioni().add(escursione);
		em.merge(escursione);
		
	}

	private void controllaAcquisto(EscursioneDTO escursione) {
		if(escursione.getAcquistato()==0){
			escursione.setAcquistato(1);
			em.merge(escursione);
		}
		
	}

	@Override
	public void registraAcquisto(PernottamentoDTO pernottamento,GiftListDTO giftList) {
		/**
		 * COMPLETARE- ERRORE DB ESISTE HOTELS, NOI QUA PARLIAMO DI PERNOTTAMENTI!!!
		 */
	}
    

}
