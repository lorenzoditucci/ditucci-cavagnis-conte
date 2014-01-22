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

import it.polimi.traveldream.ejb.exception.CoerenzaException;
import it.polimi.traveldream.ejb.management.CreaPacchettoMgr;
import it.polimi.traveldream.ejb.management.GestionePacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.entity.Citta;
import it.polimi.traveldream.ejb.management.entity.Escursione;
import it.polimi.traveldream.ejb.management.entity.GiftList;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import it.polimi.traveldream.ejb.management.entity.Pacchetto;
import it.polimi.traveldream.ejb.management.entity.Pernottamento;
import it.polimi.traveldream.ejb.management.entity.Volo;

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

	@Override
	public boolean controllaCoerenza(PacchettoDTO pacchetto) throws CoerenzaException {
		
		try{
		creaPacchettoMgr.controlloPacchetto(pacchetto);
		aggiornaPersistenzaPacchetto(pacchetto);
		return true;
		}
		catch(CoerenzaException e){
			System.out.println(e.getMessaggi().get(0));
		return false;
		}
		
	}

	private void aggiornaPersistenzaPacchetto(PacchettoDTO pacchettoAggiornato) {
		/*Prelevo l'istanza corrente nel database*/
		Pacchetto newPacchetto = em.find(Pacchetto.class, pacchettoAggiornato.getIdPacchetto());
		/*aggiorno i parametri*/
		newPacchetto.setNome(pacchettoAggiornato.getNome());
		newPacchetto.setDescrizione(pacchettoAggiornato.getDescrizione());
		newPacchetto.setDisponibilitaAttuale(pacchettoAggiornato.getDisponibilitaAttuale());
		newPacchetto.setDisponibilitaMax(pacchettoAggiornato.getDisponibilitaMax());
		newPacchetto.setDataInizio(pacchettoAggiornato.getDataInizio());
		newPacchetto.setDataFine(pacchettoAggiornato.getDataFine());
		newPacchetto.setCosto(pacchettoAggiornato.getCosto());
		newPacchetto.setMail(pacchettoAggiornato.getMail());
		/*aggiorno le relazioni*/
		newPacchetto.setCittaDestinazione(new ArrayList<Citta>());
		newPacchetto.setVoli(new ArrayList<Volo>());
		newPacchetto.setEscursioni(new ArrayList<Escursione>());
		newPacchetto.setGiftLists(new ArrayList<GiftList>());
		
		//association
		List<Volo> voli = new ArrayList<Volo>();
		for(int i=0; i<pacchettoAggiornato.getVoli().size(); i++){
			 TypedQuery<Volo> queryRicerca = em.createNamedQuery("Volo.cercaVoloId", Volo.class);
		 	 List<Volo> listaVolo = queryRicerca.setParameter("idVolo", pacchettoAggiornato.getVoli().get(i).getIdVolo()).getResultList();
		 	 voli.add(listaVolo.get(0));	
		}
		newPacchetto.getVoli().addAll(voli);
		
		
		List<Escursione> escursioni = new ArrayList<Escursione>();
		for(int i=0; i<pacchettoAggiornato.getEscursioni().size(); i++){
			 TypedQuery<Escursione> queryRicerca = em.createNamedQuery("Escursione.cercaEscursioneId", Escursione.class);
		 	 List<Escursione> listaEscursione = queryRicerca.setParameter("idEscursione", pacchettoAggiornato.getEscursioni().get(i).getIdEscursione()).getResultList();
		 	 escursioni.add(listaEscursione.get(0));	
		}
		
		newPacchetto.getEscursioni().addAll(escursioni);
		
		
		List<Citta> citta = new ArrayList<Citta>();
		for(int i=0; i<pacchettoAggiornato.getCittaDestinazione().size(); i++){
			 TypedQuery<Citta> queryRicerca = em.createNamedQuery("Citta.cercaCittaNome", Citta.class);
		 	 List<Citta> listaCitta = queryRicerca.setParameter("nomeCitta", pacchettoAggiornato.getCittaDestinazione().get(i).getNome()).getResultList();
		     citta.add(listaCitta.get(0));	
		}
		newPacchetto.getCittaDestinazione().addAll(citta);
		/*aggiorna la tupla e i riferimenti con i nuovi valori*/
		em.refresh(newPacchetto);
	
		/*eliminazione dei vecchi pernottamenti*/
		TypedQuery<Pernottamento> queryRicercaPernottamenti = em.createNamedQuery("Pernottamento.cercaPernottamentoIdPacchetto", Pernottamento.class);
		List<Pernottamento> listaPernottamento = queryRicercaPernottamenti.setParameter("idPacchetto", newPacchetto.getIdPacchetto()).getResultList();
	
	/*	for(int i=0; i<listaPernottamento.size(); i++){
			em.remove(listaPernottamento.get(i));
		}*/

		
		for(int i=0; i<pacchettoAggiornato.getPernotti().size(); i++){
	
			listaPernottamento.get(i).setDataInizio(pacchettoAggiornato.getPernotti().get(i).getDataInizio());
			
			listaPernottamento.get(i).setDataFine(pacchettoAggiornato.getPernotti().get(i).getDataFine());
			TypedQuery<Hotel> queryRicerca = em.createNamedQuery("Hotel.cercaHotelId", Hotel.class);
		 	List<Hotel> listaHotel = queryRicerca.setParameter("idHotel", pacchettoAggiornato.getPernotti().get(i).getHotel().getIdHotel()).getResultList();
			
		 	listaPernottamento.get(i).setHotel(listaHotel.get(0));
		 	listaPernottamento.get(i).setPacchetto(newPacchetto);
		 	
		 	em.refresh(listaPernottamento.get(i));
		}
	}

}
