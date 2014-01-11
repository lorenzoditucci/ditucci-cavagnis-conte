package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.CercaProdottoBaseMgr;
import it.polimi.traveldream.ejb.management.pacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.entity.GiftList;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import it.polimi.traveldream.ejb.management.entity.Pacchetto;
import it.polimi.traveldream.ejb.management.entity.Volo;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;





@Stateless
@Local(CercaProdottoBaseMgr.class)
@LocalBean
public class CercaProdottoBaseMgrBean implements CercaProdottoBaseMgr {
	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
	 /**
     * Default constructor. 
     */
    public CercaProdottoBaseMgrBean() {
        // TODO Auto-generated constructor stub
    }
    
  /*
   * Mgr per la ricerca degli Hotel
   * */
    @Override
    public ArrayList<HotelDTO> cercaHotel(int idHotel) {
    	
    	TypedQuery<Hotel> queryRicerca = em.createNamedQuery("Hotel.cercaHotelId", Hotel.class);
    	
    	List<Hotel> listaHotel = queryRicerca.setParameter("idHotel", idHotel).getResultList();

    	if(listaHotel.size()>0) System.out.println("Lista non vuota");
    	else System.out.println("Lista VUOTA");
    	
    	return convertiInListaDTO(listaHotel);     
    }

	private ArrayList<HotelDTO> convertiInListaDTO(List<Hotel> listaHotel) {

			ArrayList<HotelDTO> copia = new ArrayList<HotelDTO>();
			for(int i=0; i<listaHotel.size();i++){
	    		HotelDTO daAggiungere = new HotelDTO();
	    		daAggiungere.setIdHotel(listaHotel.get(i).getIdHotel());
	            daAggiungere.setNome(listaHotel.get(i).getNome());
	            daAggiungere.setDescrizione(listaHotel.get(i).getDescrizione());
	            daAggiungere.setIndirizzo(listaHotel.get(i).getIndirizzo());
	            daAggiungere.setCitta(listaHotel.get(i).getCitta());
	            daAggiungere.setClasse(listaHotel.get(i).getClasse());
	            daAggiungere.setCosto(listaHotel.get(i).getCosto());
	            daAggiungere.setAcquistato(listaHotel.get(i).getAcquistato());
	    		copia.add(daAggiungere);
	    	}
			return copia;
	}

	@Override
	public ArrayList<VoloDTO> cercaVolo(int idVolo) {
		TypedQuery<Volo> queryRicerca = em.createNamedQuery("Volo.cercaVoloId", Volo.class);
    	
    	List<Volo> listaVolo = queryRicerca.setParameter("idVolo", idVolo).getResultList();

    	if(listaVolo.size()>0) System.out.println("Lista non vuota");
    	else System.out.println("Lista VUOTA");
    	
    	return convertiInListaVoloDTO(listaVolo); 
	}

	private ArrayList<VoloDTO> convertiInListaVoloDTO(List<Volo> listaVolo) {
		ArrayList<VoloDTO> copia = new ArrayList<VoloDTO>();
		for(int i=0; i<listaVolo.size();i++){
    		VoloDTO daAggiungere = new VoloDTO();
    		daAggiungere.setIdVolo(listaVolo.get(i).getIdVolo());
    		daAggiungere.setCompagnia(listaVolo.get(i).getCompagnia());
    		daAggiungere.setDataPartenza(listaVolo.get(i).getDataInizio());
    		daAggiungere.setDataArrivo(listaVolo.get(i).getDataFine());
    		daAggiungere.setCittaPartenza(listaVolo.get(i).getCittaPartenza());
    		daAggiungere.setCittaArrivo(listaVolo.get(i).getCittaArrivo());
    		daAggiungere.setCosto(listaVolo.get(i).getCosto());
    		daAggiungere.setAcquistato(listaVolo.get(i).getAcquistato());
    		copia.add(daAggiungere);
    	}
		return copia;
	}

	
    
   
    

}
