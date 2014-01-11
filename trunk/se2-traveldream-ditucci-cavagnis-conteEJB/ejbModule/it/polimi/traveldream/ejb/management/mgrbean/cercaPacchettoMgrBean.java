package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.cercaPacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.entity.Pacchetto;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class cercaPacchettoMgrBean
 */
@Stateless
@Local(cercaPacchettoMgr.class)
@LocalBean
public class cercaPacchettoMgrBean implements cercaPacchettoMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
    /**
     * Default constructor. 
     */
    public cercaPacchettoMgrBean() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see cercaPacchettoMgr#cercaPacchettiParam(int, String)
     */
    public ArrayList<PacchettoDTO> cercaPacchettiParam(String emailPacchetto, String nomePacchetto) {
        TypedQuery<Pacchetto> queryRicerca = em.createNamedQuery("cercaPacchettiTuttiParam", Pacchetto.class);
        /**
         * set di tutti i parametri di ricerca.
         */
        queryRicerca.setParameter("mail","%" + emailPacchetto + "%");
        queryRicerca.setParameter("nome", "%" + nomePacchetto + "%");
        /**
         * prendo risultati della query
         */
        List<Pacchetto> pacchettiRis = queryRicerca.getResultList();
        
        /**
         * passo tutti i parametri della lista ad un arraylist di pacchettidto
         */
        
        
			return PacchettoMgrBean.copiaListaQuery(pacchettiRis);
    }

}
