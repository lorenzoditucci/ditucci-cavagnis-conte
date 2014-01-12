package it.polimi.traveldream.ejb.management.mgrbean;

import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.UserDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.entity.Group;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import it.polimi.traveldream.ejb.management.entity.User;
import it.polimi.traveldream.ejb.management.entity.Volo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VoloMgrBean implements VoloMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;

	@Override
	public void save(VoloDTO volo) {
		Volo newVolo = new Volo(volo);
		em.persist(newVolo); 
		}

	public static ArrayList<VoloDTO> copiaListaToDTO(List<Volo> listaVolo){
		ArrayList<VoloDTO> copia = new ArrayList<VoloDTO>();
		for (int i = 0; i < listaVolo.size(); i++){
			VoloDTO daAggiungere = new VoloDTO();
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
}
