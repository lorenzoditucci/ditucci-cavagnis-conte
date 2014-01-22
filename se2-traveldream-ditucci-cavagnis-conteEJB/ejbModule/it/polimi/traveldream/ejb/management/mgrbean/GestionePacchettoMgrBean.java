package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.traveldream.ejb.management.CreaPacchettoMgr;
import it.polimi.traveldream.ejb.management.GestionePacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
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
	
	@EJB
	private CreaPacchettoMgr creaPacchettoMgr;
	
	
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

	@Override
	public boolean eliminaPacchettoId(PacchettoDTO pacchetto) {
		if(pacchetto.getDisponibilitaAttuale()==pacchetto.getDisponibilitaMax()){
			/*Il pacchetto non è mai stato acquistato*/
			Pacchetto daRimuovere;
			daRimuovere = em.find(Pacchetto.class, pacchetto.getIdPacchetto());
			for(int i=0; i<pacchetto.getPernotti().size();i++){
				em.remove(em.find(Pernottamento.class, pacchetto.getPernotti().get(i).getIdPernottametto()));
			}	
			em.remove(daRimuovere);
			return true;
		}else{
			return false;
			}
		
	}

	@Override
	public List<VoloDTO> cercaVolo(int idVoloDaCercare) {
		return creaPacchettoMgr.cercaVolo(idVoloDaCercare);
	}

	@Override
	public List<HotelDTO> cercaHotel(int idHotelDaCercare) {
		return creaPacchettoMgr.cercaHotel(idHotelDaCercare);
	}

	@Override
	public List<EscursioneDTO> cercaEscursione(int idEscursioneDaCercare) {
		return creaPacchettoMgr.cercaEscursione(idEscursioneDaCercare);
	}

}
