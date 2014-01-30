package it.polimi.traveldream.ejb.management.mgrbean;

import it.polimi.traveldream.ejb.management.HotelMgr;
import it.polimi.traveldream.ejb.management.dto.CittaDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import it.polimi.traveldream.ejb.management.entity.Pernottamento;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class HotelMgrBean implements HotelMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
	@EJB
	private CittaMgrBean cittaMgrBean;

	@Override
	public void save(HotelDTO hotel) {
		Hotel newHotel = new Hotel(hotel);
		em.persist(newHotel); 
		
		/*Aggiungi citta' al db*/
		CittaDTO cittaDaInserire = new CittaDTO();
		cittaDaInserire.setNome(hotel.getCitta());
		cittaMgrBean.save(cittaDaInserire);
		
		}
	
	public static ArrayList<HotelDTO> copiaListaToDTO(List<Hotel> listaHotel){
		ArrayList<HotelDTO> copia = new ArrayList<HotelDTO>();
		for(int i=0; i < listaHotel.size(); i++){
			HotelDTO daAggiungere = new HotelDTO();
			daAggiungere.setIdHotel(listaHotel.get(i).getIdHotel());
			daAggiungere.setNome(listaHotel.get(i).getNome());
			daAggiungere.setDescrizione(listaHotel.get(i).getDescrizione());
			daAggiungere.setCitta(listaHotel.get(i).getCitta());
			daAggiungere.setClasse(listaHotel.get(i).getClasse());
			daAggiungere.setCosto(listaHotel.get(i).getCosto());
			daAggiungere.setIndirizzo(listaHotel.get(i).getIndirizzo());
			daAggiungere.setAcquistato(listaHotel.get(i).getAcquistato());
			
			copia.add(daAggiungere);
		}
		return copia;
	}

	@Override
	public void remove(int idHotel) {
		Hotel h = em.find(Hotel.class, idHotel);
        em.remove(h);
	}
	
	@Override
	public List<HotelDTO> cercaHotelPerID(int idHotelDaCercare){
		TypedQuery<Hotel> queryRicerca = em.createNamedQuery("Hotel.cercaHotelId", Hotel.class);
    	List<Hotel> listaHotel = queryRicerca.setParameter("idHotel", idHotelDaCercare).getResultList();
    	
    	return copiaListaToDTO(listaHotel); 
	}
	
	@Override 
	public List<HotelDTO> cercaHotelPerCitta(String citta){
		TypedQuery<Hotel> queryRicerca = em.createNamedQuery("Hotel.cercaHotelPerCitta", Hotel.class);
    	List<Hotel> listaHotel = queryRicerca.setParameter("citta", citta).getResultList();
    	
    	return copiaListaToDTO(listaHotel); 
	}

	@Override
	public boolean controllaAppertenenzaPacchetto(HotelDTO h) {
		TypedQuery<Pernottamento> queryRicercaPernottamenti = em.createNamedQuery("Pernottamento.cercaPernottamentoIdHotel", Pernottamento.class);
		List<Pernottamento> listaPernottamento = queryRicercaPernottamenti.setParameter("idHotel", h.getIdHotel()).getResultList();
	
		if(listaPernottamento.isEmpty())
			return false;
		
		
		return true;		
	}

	@Override
	public void aggiornaModificheHotel(HotelDTO hotel) {
		Hotel h = em.find(Hotel.class, hotel.getIdHotel());
		
		/*aggiornamento dati escursione*/
		h.setNome(hotel.getNome());
		h.setDescrizione(hotel.getDescrizione());
		h.setIndirizzo(hotel.getIndirizzo());
		h.setCitta(hotel.getCitta());
		h.setClasse(hotel.getClasse());
		h.setCosto(hotel.getCosto());

		CittaDTO cittaDaInserire = new CittaDTO();
		cittaDaInserire.setNome(hotel.getCitta());
		cittaMgrBean.save(cittaDaInserire);
		
		/*aggiornamento persistenza*/
		em.merge(h);
	}
	
	
	
}
