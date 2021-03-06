package it.polimi.traveldream.ejb.management.mgrbean;

import it.polimi.traveldream.ejb.management.PernottamentiAcquistatiMgr;
import it.polimi.traveldream.ejb.management.dto.PernottamentiAcquistatiDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.entity.PernottamentiAcquistati;
import it.polimi.traveldream.ejb.management.entity.Pernottamento;

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
 * Session Bean implementation class PernottamentiAcquistatiMgrBean
 */
@Stateless
@Local(PernottamentiAcquistatiMgr.class)
@LocalBean
public class PernottamentiAcquistatiMgrBean implements PernottamentiAcquistatiMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
    /**
     * Default constructor. 
     */
    public PernottamentiAcquistatiMgrBean() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see PernottamentiAcquistatiMgr#cercaPernottamentiAcquistati(int)
     */
    public List<PernottamentiAcquistatiDTO> cercaPernottamentiAcquistati(int idGiftList) {
    	String query = "SELECT p "
        		+ "FROM PernottamentiAcquistati p "
        		+ "WHERE p.idGiftList = :idGiftList";
    	
    	TypedQuery<PernottamentiAcquistati> queryRicerca = em.createQuery(query,PernottamentiAcquistati.class);
    	queryRicerca.setParameter("idGiftList", idGiftList);
    	
			return PernottamentiAcquistatiMgrBean.copiaListaQuery(queryRicerca.getResultList());
    }

	private static List<PernottamentiAcquistatiDTO> copiaListaQuery(List<PernottamentiAcquistati> resultList) {
		List<PernottamentiAcquistatiDTO> copia = new ArrayList<PernottamentiAcquistatiDTO>();
		for(PernottamentiAcquistati p:resultList){
			PernottamentiAcquistatiDTO daAggiungere = new PernottamentiAcquistatiDTO();
			daAggiungere.setDataAcquisto(p.getDataAcquisto());
			daAggiungere.setIdGiftList(p.getIdGiftList());
			daAggiungere.setIdPernottamento(p.getIdPernottamento());
			daAggiungere.setIdPernottamentoAcquistato(p.getIdPernottamentoAcquistato());
			daAggiungere.setNomeAcquirente(p.getNomeAcquirente());
			copia.add(daAggiungere);
		}
		return copia;
	}
	
	@Override
	public List<PernottamentoDTO> pernottamentoPerID(int id){
		TypedQuery<Pernottamento> queryRicerca = em.createNamedQuery("Pernottamento.cercaPernottamentoId", Pernottamento.class);
    	List<Pernottamento> lista = queryRicerca.setParameter("idPernottamento", id).getResultList();
    	
    	return Pernottamento.copiaToPernottamentoDTO(lista);
	}

}
