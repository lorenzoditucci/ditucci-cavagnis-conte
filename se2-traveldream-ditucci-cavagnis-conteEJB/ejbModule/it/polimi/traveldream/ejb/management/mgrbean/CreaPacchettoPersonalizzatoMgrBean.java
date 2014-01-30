package it.polimi.traveldream.ejb.management.mgrbean;



import it.polimi.traveldream.ejb.management.CreaPacchettoPersonalizzatoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.entity.Citta;
import it.polimi.traveldream.ejb.management.entity.Escursione;
import it.polimi.traveldream.ejb.management.entity.GiftList;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import it.polimi.traveldream.ejb.management.entity.Pacchetto;
import it.polimi.traveldream.ejb.management.entity.Pernottamento;
import it.polimi.traveldream.ejb.management.entity.Volo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class CreaPacchettoPersonalizzatoMgrBean
 */
@Stateless
@LocalBean
public class CreaPacchettoPersonalizzatoMgrBean implements CreaPacchettoPersonalizzatoMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
    /**
     * Default constructor. 
     */
    public CreaPacchettoPersonalizzatoMgrBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public int salvaPacchettoPersonalizzato(PacchettoDTO pacchetto){
    
    Pacchetto newPacchetto = new Pacchetto();
	newPacchetto.setNome("Personalizzato");
	newPacchetto.setDescrizione("Pacchetto Personalizzato");
	//imposto disponibilità attuale e max a 10 come da documento
	newPacchetto.setDisponibilitaAttuale(10);
	newPacchetto.setDisponibilitaMax(10);
	newPacchetto.setDataInizio(pacchetto.getDataInizio());
	newPacchetto.setDataFine(pacchetto.getDataFine());
	newPacchetto.setMail(pacchetto.getMail());
	//relations
	newPacchetto.setCittaDestinazione(new ArrayList<Citta>());
	newPacchetto.setVoli(new ArrayList<Volo>());
	newPacchetto.setEscursioni(new ArrayList<Escursione>());
	newPacchetto.setGiftLists(new ArrayList<GiftList>());
	
	newPacchetto.setCosto(this.calcolaCosto(pacchetto));
	//persist
	em.persist(newPacchetto);
	
	
	//association
	List<Volo> voli = new ArrayList<Volo>();
	for(int i=0; i<pacchetto.getVoli().size(); i++){
		 TypedQuery<Volo> queryRicerca = em.createNamedQuery("Volo.cercaVoloId", Volo.class);
	 	 List<Volo> listaVolo = queryRicerca.setParameter("idVolo", pacchetto.getVoli().get(i).getIdVolo()).getResultList();
	 	 voli.add(listaVolo.get(0));	
	}
	newPacchetto.getVoli().addAll(voli);
	
	
	List<Escursione> escursioni = new ArrayList<Escursione>();
	for(int i=0; i<pacchetto.getEscursioni().size(); i++){
		 TypedQuery<Escursione> queryRicerca = em.createNamedQuery("Escursione.cercaEscursioneId", Escursione.class);
	 	 List<Escursione> listaEscursione = queryRicerca.setParameter("idEscursione", pacchetto.getEscursioni().get(i).getIdEscursione()).getResultList();
	 	 escursioni.add(listaEscursione.get(0));	
	}
	
	newPacchetto.getEscursioni().addAll(escursioni);
	
	
	List<Citta> citta = new ArrayList<Citta>();
	for(int i=0; i<pacchetto.getCittaDestinazione().size(); i++){
		 TypedQuery<Citta> queryRicerca = em.createNamedQuery("Citta.cercaCittaNome", Citta.class);
	 	 List<Citta> listaCitta = queryRicerca.setParameter("nomeCitta", pacchetto.getCittaDestinazione().get(i).getNome()).getResultList();
	     citta.add(listaCitta.get(0));	
	}
	newPacchetto.getCittaDestinazione().addAll(citta);
	
	//pernottamento
	for(int i=0; i<pacchetto.getPernotti().size(); i++){
		Pernottamento newPernottamento = new Pernottamento(pacchetto.getPernotti().get(i));
		
		System.out.println("Id dell'hotel da cercare: " + pacchetto.getPernotti().get(i).getHotel().getIdHotel());
		
		TypedQuery<Hotel> queryRicerca = em.createNamedQuery("Hotel.cercaHotelId", Hotel.class);
	 	List<Hotel> listaHotel = queryRicerca.setParameter("idHotel", pacchetto.getPernotti().get(i).getHotel().getIdHotel()).getResultList();
	
	 	System.out.println(listaHotel.get(0).getIdHotel());
	 	
	 	newPernottamento.setHotel(listaHotel.get(0));
	 	newPernottamento.setPacchetto(newPacchetto);
	 	
	 	em.persist(newPernottamento);
	}

	return newPacchetto.getIdPacchetto() ;
    }
    
    private double calcolaCosto(PacchettoDTO pacchetto){
    	double prezzo = 0;
    	//Aggiungo costo dei voli
    	for(int i=0;i<pacchetto.getVoli().size();i++){
    		prezzo += pacchetto.getVoli().get(i).getCosto();
    	}
    	
    	//Aggiungo costo delle escursioni
    	for(int i=0;i<pacchetto.getEscursioni().size();i++){
    		prezzo += pacchetto.getEscursioni().get(i).getCosto();
    	}
    	
    	//Aggiungo costo dei pernottamenti
    	for(int i=0; i<pacchetto.getPernotti().size(); i++){
			Timestamp dataInizio =pacchetto.getPernotti().get(i).getDataInizio();
			Timestamp dataFine =pacchetto.getPernotti().get(i).getDataFine();
			dataInizio.setHours(0);
			dataInizio.setMinutes(0);
			dataFine.setHours(0);
			dataFine.setMinutes(0);
			int giorni=(int) (dataFine.getTime()-dataInizio.getTime())/86400000;
			prezzo+=giorni*pacchetto.getPernotti().get(i).getHotel().getCosto();
			
		}
    	return prezzo;
    }

}
