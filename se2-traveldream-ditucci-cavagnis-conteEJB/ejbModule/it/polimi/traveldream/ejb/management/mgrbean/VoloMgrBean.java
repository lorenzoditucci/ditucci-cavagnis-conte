package it.polimi.traveldream.ejb.management.mgrbean;

import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.CittaDTO;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.UserDTO;
import it.polimi.traveldream.ejb.management.dto.VoliAcquistatiProvaDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.entity.Escursione;
import it.polimi.traveldream.ejb.management.entity.Group;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import it.polimi.traveldream.ejb.management.entity.User;
import it.polimi.traveldream.ejb.management.entity.VoliAcquistatiProva;
import it.polimi.traveldream.ejb.management.entity.Volo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class VoloMgrBean implements VoloMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
	@EJB
	private CittaMgrBean cittaMgrBean;

	@Override
	public void save(VoloDTO volo) {
		Volo newVolo = new Volo(volo);
		em.persist(newVolo);
		
		/*Aggiungi citt� al db*/
		CittaDTO cittaDaInserire = new CittaDTO();
		cittaDaInserire.setNome(volo.getCittaArrivo());
		cittaMgrBean.save(cittaDaInserire);
		
		cittaDaInserire = new CittaDTO();
		cittaDaInserire.setNome(volo.getCittaPartenza());
		cittaMgrBean.save(cittaDaInserire);
		}

	public static ArrayList<VoloDTO> copiaListaToDTO(List<Volo> listaVolo){
		ArrayList<VoloDTO> copia = new ArrayList<VoloDTO>();
		for (int i = 0; i < listaVolo.size(); i++){
			VoloDTO daAggiungere = new VoloDTO();
			daAggiungere.setIdVolo(listaVolo.get(i).getIdVolo());
			daAggiungere.setCittaArrivo(listaVolo.get(i).getCittaArrivo());
			daAggiungere.setCittaPartenza(listaVolo.get(i).getCittaPartenza());
			daAggiungere.setCompagnia(listaVolo.get(i).getCompagnia());
			daAggiungere.setAcquistato(listaVolo.get(i).getAcquistato());
			daAggiungere.setCosto(listaVolo.get(i).getCosto());
			daAggiungere.setDataArrivo(new Timestamp(listaVolo.get(i).getDataFine().getTime()));
			daAggiungere.setDataPartenza(new Timestamp(listaVolo.get(i).getDataInizio().getTime()));
			
			copia.add(daAggiungere);
		}
		return copia;
	}

	@Override
	public void remove(int idVolo) {
		Volo v = em.find(Volo.class, idVolo);
        em.remove(v);
		
	}
	
	@Override
	public List<VoloDTO> cercaVoloPerID(int idVoloDaCercare){
		TypedQuery<Volo> queryRicerca = em.createNamedQuery("Volo.cercaVoloId", Volo.class);
    	List<Volo> listaVolo = queryRicerca.setParameter("idVolo", idVoloDaCercare).getResultList();
    	
    	return copiaListaToDTO(listaVolo); 
	}
	
	@Override
	public List<VoloDTO> cercaVoloPerPartenzaArrivo(String partenza, String arrivo){
		TypedQuery<Volo> queryRicerca = em.createNamedQuery("Volo.cercaPartenzaArrivo", Volo.class);
		List<Volo> listaVolo = queryRicerca.setParameter("partenza", partenza).setParameter("arrivo", arrivo).getResultList();
		return copiaListaToDTO(listaVolo);
	}

	@Override
	public boolean controllaAppertenenzaPacchetto(VoloDTO v) {
		String sqlQuery = "SELECT * FROM VoliPacchetto WHERE idVolo = "+v.getIdVolo();
		
		/*non appartiene a un pacchetto*/
		if(em.createNativeQuery(sqlQuery).getResultList().isEmpty())
			return false;
		
		/*appartiene ad un pacchetto*/
		return true;
		
	}
	
	@Override
	public void aggiornaModificheVolo(VoloDTO volo) {
		Volo v = em.find(Volo.class, volo.getIdVolo());
		
		/*aggiornamento dati escursione*/
		v.setCompagnia(volo.getCompagnia());
		v.setCittaArrivo(volo.getCittaArrivo());
		v.setCittaPartenza(volo.getCittaPartenza());
		v.setDataInizio(new Timestamp(volo.getDataPartenza().getTime()));
		v.setDataFine(new Timestamp(volo.getDataArrivo().getTime()));
		v.setCosto(volo.getCosto());
		
		CittaDTO cittaDaInserire = new CittaDTO();
		cittaDaInserire.setNome(v.getCittaPartenza());
		cittaMgrBean.save(cittaDaInserire);
		
		cittaDaInserire = new CittaDTO();
		cittaDaInserire.setNome(v.getCittaArrivo());
		cittaMgrBean.save(cittaDaInserire);
		
		/*aggiornamento persistenza*/
		em.merge(v);
	}

	public static List<VoliAcquistatiProvaDTO> copiaToDTO(
			List<VoliAcquistatiProva> voli) {
		
			List<VoliAcquistatiProvaDTO> copia = new ArrayList<VoliAcquistatiProvaDTO>();
			for(int i=0; i< voli.size(); i++){
				VoliAcquistatiProvaDTO daAggiungere = new VoliAcquistatiProvaDTO();
				daAggiungere.setDataAcquisto(voli.get(i).getDataAcquisto());
				daAggiungere.setIdGiftList(voli.get(i).getIdGiftList());
				daAggiungere.setIdVolo(voli.get(i).getIdVolo());
				daAggiungere.setIdVoloAcquistato(voli.get(i).getIdVoloAcquistato());
				daAggiungere.setNomeAcquirente(voli.get(i).getNomeAcquirente());
				copia.add(daAggiungere);
				
			}
			return copia;
		
		
	}

	
}
