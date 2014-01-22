package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.VoliAcquistatiProvaMGR;
import it.polimi.traveldream.ejb.management.dto.VoliAcquistatiProvaDTO;
import it.polimi.traveldream.ejb.management.entity.VoliAcquistatiProva;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;

/**
 * Session Bean implementation class VoliAcquistatiProvaMgrBean
 */
@Stateless
@Local(VoliAcquistatiProvaMGR.class)
@LocalBean
public class VoliAcquistatiProvaMgrBean implements VoliAcquistatiProvaMGR {
	
	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
    /**
     * Default constructor. 
     */
    public VoliAcquistatiProvaMgrBean() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see VoliAcquistatiProvaMGR#cercaVoliAcquistati(String)
     */
    public List<VoliAcquistatiProvaDTO> cercaVoliAcquistati(int idRicerca) {
        String query = "SELECT v "
        		+ "FROM VoliAcquistatiProva v "
        		+ "WHERE v.idGiftList = :idGiftList";
        System.out.println("dentro funzione cercavoliacquistati, idricerca vale "+idRicerca);
        TypedQuery<VoliAcquistatiProva> queryRicerca = em.createQuery(query,VoliAcquistatiProva.class);
        queryRicerca.setParameter("idGiftList", idRicerca);
        List<VoliAcquistatiProva> risultati = queryRicerca.getResultList();
        
			return VoliAcquistatiProvaMgrBean.copiaListaQuery(risultati);
    }

	private static List<VoliAcquistatiProvaDTO> copiaListaQuery(List<VoliAcquistatiProva> risultati) {
		List<VoliAcquistatiProvaDTO> copia = new ArrayList<VoliAcquistatiProvaDTO>();
		for(int i=0; i<risultati.size();i++){
			VoliAcquistatiProvaDTO daAggiungere = new VoliAcquistatiProvaDTO();
			daAggiungere.setDataAcquisto(risultati.get(i).getDataAcquisto());
			daAggiungere.setIdGiftList(risultati.get(i).getIdGiftList());
			daAggiungere.setIdVolo(risultati.get(i).getIdVolo());
			daAggiungere.setIdVoloAcquistato(risultati.get(i).getIdVoloAcquistato());
			daAggiungere.setNomeAcquirente(risultati.get(i).getNomeAcquirente());
			copia.add(daAggiungere);
		}
		return copia;
	}

}
