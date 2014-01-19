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
        giftList.getVoli().add(volo);
        em.merge(giftList);
    }

	@Override
	public void registraAcquisto(EscursioneDTO escursione, GiftListDTO giftList) {
		giftList.getEscursioni().add(escursione);
		em.merge(escursione);
		
	}

	@Override
	public void registraAcquisto(PernottamentoDTO pernottamento,GiftListDTO giftList) {
		/**
		 * COMPLETARE- ERRORE DB ESISTE HOTELS, NOI QUA PARLIAMO DI PERNOTTAMENTI!!!
		 */
	}
    

}
