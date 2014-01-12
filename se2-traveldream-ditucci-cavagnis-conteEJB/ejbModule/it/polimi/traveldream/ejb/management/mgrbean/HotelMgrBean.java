package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.HotelMgr;
import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import it.polimi.traveldream.ejb.management.entity.Volo;

import javax.annotation.Resource;
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

	@Override
	public void save(HotelDTO hotel) {
		Hotel newHotel = new Hotel(hotel);
		em.persist(newHotel); 
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
	
}
