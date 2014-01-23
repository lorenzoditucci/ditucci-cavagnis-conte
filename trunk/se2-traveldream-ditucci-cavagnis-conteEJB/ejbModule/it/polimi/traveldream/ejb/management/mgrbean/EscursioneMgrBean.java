package it.polimi.traveldream.ejb.management.mgrbean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.EscursioneMgr;
import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.CittaDTO;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.entity.Escursione;
import it.polimi.traveldream.ejb.management.entity.User;
import it.polimi.traveldream.ejb.management.entity.Volo;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EscursioneMgrBean implements EscursioneMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
	@EJB
	private CittaMgrBean cittaMgrBean;

	@Override
	public void save(EscursioneDTO escursione) {
		Escursione newEscursione = new Escursione(escursione);
		em.persist(newEscursione);
		
		/*Aggiungi citt� al db*/
		CittaDTO cittaDaInserire = new CittaDTO();
		cittaDaInserire.setNome(escursione.getCitta());
		cittaMgrBean.save(cittaDaInserire);
		}
	
	public static ArrayList<EscursioneDTO> copiaListaToDTO(List<Escursione> listaEscursione){
		ArrayList<EscursioneDTO> copia = new ArrayList<EscursioneDTO>();
		for(int i=0; i<listaEscursione.size(); i++){
			EscursioneDTO daAggiungere = new EscursioneDTO();
			daAggiungere.setIdEscursione(listaEscursione.get(i).getIdEscursione());
			daAggiungere.setNome(listaEscursione.get(i).getNome());
			daAggiungere.setDescrizione(listaEscursione.get(i).getDescrizione());
			daAggiungere.setCitta(listaEscursione.get(i).getCitta());
			daAggiungere.setAcquistato(listaEscursione.get(i).getAcquistato());
			daAggiungere.setCosto(listaEscursione.get(i).getIdEscursione());
			daAggiungere.setDataInizio(new Timestamp(listaEscursione.get(i).getDataInizio().getTime()));
			daAggiungere.setDataFine(new Timestamp(listaEscursione.get(i).getDataFine().getTime()));
			
			copia.add(daAggiungere);
		}
		return copia;
	}

	 @Override
	 public void remove(int id) {
		Escursione e = em.find(Escursione.class, id);
        em.remove(e);
	}
	 
	 @Override
		public List<EscursioneDTO> cercaEscursionePerID(int idEscursioneDaCercare){
			TypedQuery<Escursione> queryRicerca = em.createNamedQuery("Escursione.cercaEscursioneId", Escursione.class);
	    	List<Escursione> listaEscursione = queryRicerca.setParameter("idEscursione", idEscursioneDaCercare).getResultList();
	    	
	    	return copiaListaToDTO(listaEscursione); 
		}
	 
	 @Override
		public boolean controllaAppertenenzaPacchetto(EscursioneDTO e) {
			String sqlQuery = "SELECT * FROM EscursioniPacchetto WHERE idEscursione = "+e.getIdEscursione();
			
			/*non appartiene a un pacchetto*/
			if(em.createNativeQuery(sqlQuery).getResultList().isEmpty())
				return false;
			
			/*appartiene ad un pacchetto*/
			return true;
			
		}
}
