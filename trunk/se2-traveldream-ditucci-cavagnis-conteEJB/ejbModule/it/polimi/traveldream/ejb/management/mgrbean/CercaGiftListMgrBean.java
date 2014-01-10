package it.polimi.traveldream.ejb.management.mgrbean;

import it.polimi.traveldream.ejb.management.CercaGiftListMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.entity.GiftList;
import it.polimi.traveldream.ejb.management.entity.User;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class CercaMgrBean
 */
@Stateless
@Local(CercaGiftListMgr.class)
@LocalBean
public class CercaGiftListMgrBean implements CercaGiftListMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
    /**
     * Default constructor. 
     */
    public CercaGiftListMgrBean() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * manager per la ricerca. 
     * @see CercaGiftListMgr#cerca(cercaGiftListDTO)
     */

    
	@Override
	public GiftListDTO cerca(int ricerca) {
		
		TypedQuery<GiftList> queryRicerca = em.createNamedQuery("cercaGiftList", GiftList.class);
		GiftList giftList = queryRicerca.setParameter("id", ricerca).getSingleResult();
			
		
		
		GiftListDTO giftListDTO = new GiftListDTO();
		/**
		 * prova passaggio oggetto gift list in dto
		 */
		giftListDTO.setIdGiftList(giftList.getIdGiftList());
		giftListDTO.setNome(giftList.getNome());
		giftListDTO.setMailCliente(giftList.getMailCliente());
		/**
		 * da completare..(mancano le colonne nel database)
		 */
		return giftListDTO;
	}


}
