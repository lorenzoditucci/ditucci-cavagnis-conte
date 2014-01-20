package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.traveldream.ejb.management.GestionePacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import it.polimi.traveldream.ejb.management.entity.Pacchetto;
import it.polimi.traveldream.ejb.management.entity.User;
import it.polimi.traveldream.ejb.management.entity.Pernottamento;;

@Stateless
public class GestionePacchettoMgrBean implements GestionePacchettoMgr {
	
	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
	
	@Override
	public PacchettoDTO cercaPacchettoId(int idPacchettoDaCercare) {
		Pacchetto pacchettoTrovato;
		pacchettoTrovato=em.find(Pacchetto.class, idPacchettoDaCercare);
		if(pacchettoTrovato!=null){
			PacchettoDTO pacchettoDTO = pacchettoTrovato.convertiInDTO();
			//prendi i pernottamenti
			List<Pernottamento> listaPernottamenti = new ArrayList<Pernottamento>();
			TypedQuery<Pernottamento> queryRicerca = em.createNamedQuery("Pernottamento.cercaPernottamentoIdPacchetto", Pernottamento.class);
	    	listaPernottamenti = queryRicerca.setParameter("idPacchetto", pacchettoDTO.getIdPacchetto()).getResultList();
	    	
	    	pacchettoDTO.setPernotti(Pernottamento.copiaToPernottamentoDTO(listaPernottamenti));	
			
	    	return pacchettoDTO;
		}else{
			return null;
		}

	}

}
