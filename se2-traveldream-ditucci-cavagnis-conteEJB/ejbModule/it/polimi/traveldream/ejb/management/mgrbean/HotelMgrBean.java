package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.HotelMgr;
import it.polimi.traveldream.ejb.management.dto.CittaDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
}
