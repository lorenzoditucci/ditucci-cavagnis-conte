package it.polimi.traveldream.ejb.management.mgrbean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    public ArrayList<PacchettoDTO> cercaPacchettiParam(Date dataPartenza, Date dataFine, Double costo, String nomePacchetto) {
    	
    	String query="SELECT p from Pacchetto p WHERE (p.nome LIKE :nome "
    			+ "AND (:dataPartenza IS NULL OR (p.dataInizio >= :dataPartenza)) "
    			+ "AND (:dataFine IS NULL OR(p.dataFine <= :dataFine)) "
    			+ "AND (:costo IS NULL OR(p.costo <= :costo)))";
    	
    	TypedQuery<Pacchetto> queryDiRicerca = em.createQuery(query,Pacchetto.class);
    	/**
    	 * aggiunta parametri
    	 */
    	queryDiRicerca.setParameter("nome", "%" + nomePacchetto + "%");
    	try {
    		Timestamp t1 = new Timestamp(dataPartenza.getTime());
        	queryDiRicerca.setParameter("dataPartenza", t1);
		} catch (NullPointerException e) {
			queryDiRicerca.setParameter("dataPartenza", null);
		}
    	
    	try {
    		Timestamp t2 = new Timestamp(dataFine.getTime());
        	queryDiRicerca.setParameter("dataFine", t2);
			
		} catch (NullPointerException e) {
			queryDiRicerca.setParameter("dataFine", null);
		}
    	
    	queryDiRicerca.setParameter("costo", costo);
    	
    	System.out.println(queryDiRicerca);
    	
        List<Pacchetto> pacchettiRis = queryDiRicerca.getResultList();
        System.out.println(pacchettiRis.size());
        
        /**
         * passo tutti i parametri della lista ad un arraylist di pacchettidto
         */
        
        
			return PacchettoMgrBean.copiaListaQuery(pacchettiRis);
    }

	@Override
	public ArrayList<PacchettoDTO> cercaPacchettoId(int idPacchetto) {
		TypedQuery<Pacchetto> query = em.createNamedQuery("cercaPacchettiId", Pacchetto.class);
		query.setParameter("id", idPacchetto);
		List<Pacchetto> risultati = query.getResultList();
		return PacchettoMgrBean.copiaListaQuery(risultati);
		
	}

}
