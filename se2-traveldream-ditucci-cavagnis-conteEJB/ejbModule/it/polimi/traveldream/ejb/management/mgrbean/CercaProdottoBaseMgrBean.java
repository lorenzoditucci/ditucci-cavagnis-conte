package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.CercaProdottoBaseMgr;
import it.polimi.traveldream.ejb.management.pacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.entity.Escursione;
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

	@Override
	public ArrayList<EscursioneDTO> cercaEscursione(int idEscursione) {
        TypedQuery<Escursione> queryRicerca = em.createNamedQuery("Escursione.cercaEscursioneId", Escursione.class);
    	
    	List<Escursione> listaEscursione = queryRicerca.setParameter("idEscursione", idEscursione).getResultList();

    	if(listaEscursione.size()>0) System.out.println("Lista non vuota");
    	else System.out.println("Lista VUOTA");
    	
    	return convertiInListaEscursioneDTO(listaEscursione); 
	}

	private ArrayList<EscursioneDTO> convertiInListaEscursioneDTO(
			List<Escursione> listaEscursione) {
		
		ArrayList<EscursioneDTO> copia = new ArrayList<EscursioneDTO>();
		for(int i=0; i<listaEscursione.size();i++){
    		EscursioneDTO daAggiungere = new EscursioneDTO();
    		daAggiungere.setIdEscursione(listaEscursione.get(i).getIdEscursione());
    		daAggiungere.setNome(listaEscursione.get(i).getNome());
    		daAggiungere.setDescrizione(listaEscursione.get(i).getDescrizione());
    		daAggiungere.setDataInizio(listaEscursione.get(i).getDataInizio());
    		daAggiungere.setDataFine(listaEscursione.get(i).getDataFine());
    		daAggiungere.setCitta(listaEscursione.get(i).getCitta());
    		daAggiungere.setCosto(listaEscursione.get(i).getCosto());
    		daAggiungere.setAcquistato(listaEscursione.get(i).getAcquistato());
    		copia.add(daAggiungere);
    	}
		return copia;
		
	}

	
    
   
    

}
