package it.polimi.traveldream.ejb.management.mgrbean;

import it.polimi.traveldream.ejb.management.EscursioniAcquistateMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioniAcquistateDTO;
import it.polimi.traveldream.ejb.management.entity.EscursioniAcquistate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class EscursioniAcquistateMgrBean
 */
@Stateless
@Local(EscursioniAcquistateMgr.class)
@LocalBean
public class EscursioniAcquistateMgrBean implements EscursioniAcquistateMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
    /**
     * Default constructor. 
     */
    public EscursioniAcquistateMgrBean() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see EscursioniAcquistateMgr#cercaEscursioniAcquistate(int)
     */
    public List<EscursioniAcquistateDTO> cercaEscursioniAcquistate(int idGiftList) {
    	String query = "SELECT e "
        		+ "FROM EscursioniAcquistate e "
        		+ "WHERE e.idGiftList = :idGiftList";
    	TypedQuery<EscursioniAcquistate> queryRicerca = em.createQuery(query,EscursioniAcquistate.class);
    	queryRicerca.setParameter("idGiftList", idGiftList);
    	
		return EscursioniAcquistateMgrBean.copiaListaQuery(queryRicerca.getResultList());
    }

	private static List<EscursioniAcquistateDTO> copiaListaQuery(List<EscursioniAcquistate> resultList) {
		List<EscursioniAcquistateDTO> copia = new ArrayList<EscursioniAcquistateDTO>();
		for(EscursioniAcquistate e:resultList){
			EscursioniAcquistateDTO daAggiungere = new EscursioniAcquistateDTO();
			daAggiungere.setDataAcquisto(e.getDataAcquisto());
			daAggiungere.setIdEscursione(e.getIdEscursione());
			daAggiungere.setIdEscursioniAcquistate(e.getIdEscursioniAcquistate());
			daAggiungere.setIdGiftList(e.getIdGiftList());
			daAggiungere.setNomeAcquirente(e.getNomeAcquirente());
			
			copia.add(daAggiungere);
		}
		return copia;
	}

}
