package it.polimi.traveldream.ejb.management.mgrbean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.traveldream.ejb.management.CreaPacchettoMgr;
import it.polimi.traveldream.ejb.management.cercaPacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.CittaDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import it.polimi.traveldream.ejb.management.entity.Volo;

/*
 * Bean stateful per mantenimento della sessione
 * durante la creazione di un pacchetto
 */

@Stateful
@Local(CreaPacchettoMgr.class)
@LocalBean
public class CreaPacchettoMgrBean implements CreaPacchettoMgr{
	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;

	public PacchettoDTO pacchettoInBean;
	
	/*
	 * Questo metodo viene chiamato automaticamente alla creazione 
	 * del bean e serve per inizializzare gli attributi che conserveranno
	 * i dati durante tutta la sessione della creazione del pacchetto
	 * */
	@PostConstruct
	public void initialize() {
		this.pacchettoInBean=new PacchettoDTO();
		}

	@Override
	public void instanziaPacchetto(PacchettoDTO pacchetto) {
		copiaDTOtoDTOStateful(pacchetto);
	}

	/*
	 * copia il dto in arrivo dalla pagina web nel dto contenuto nello statfule
	 */
	private void copiaDTOtoDTOStateful(PacchettoDTO pacchetto) {
		this.pacchettoInBean.setNome(pacchetto.getNome());
		this.pacchettoInBean.setDescrizione(pacchetto.getDescrizione());
		this.pacchettoInBean.setDisponibilitaMax(pacchetto.getDisponibilitaMax());
		this.pacchettoInBean.setDataInizio(pacchetto.getDataInizio());
		this.pacchettoInBean.setDataFine(pacchetto.getDataFine());
		//E' stato appena creato e quindi non l'ha ancora comprato nessuno
		this.pacchettoInBean.setDisponibilitaAttuale(pacchetto.getDisponibilitaMax());
		//il costo è ancora da calcolare
		this.pacchettoInBean.setCosto(0.0);
		this.pacchettoInBean.setMail(pacchetto.getMail());
	}

	@Override
	public List<VoloDTO> cercaVolo(int idVoloDaCercare) {
        TypedQuery<Volo> queryRicerca = em.createNamedQuery("Volo.cercaVoloId", Volo.class);
    	
    	List<Volo> listaVolo = queryRicerca.setParameter("idVolo", idVoloDaCercare).getResultList();
    	
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
	public boolean inserisciVoliInPacchettoInstanziato(List<VoloDTO> voli) {
		//verifica che i voli siano coerenti ai dati del pacchetto creato
		//precedentemente
		if(stessoGiornoMeseAnno(pacchettoInBean.getDataInizio(), voli.get(0).getDataArrivo())
				&& stessoGiornoMeseAnno(pacchettoInBean.getDataFine(), voli.get(voli.size()-1).getDataArrivo()))
		{
			//copia voli nello stateful
			this.pacchettoInBean.getVoli().addAll(voli);
			System.out.println(pacchettoInBean.getVoli().get(0).getCittaPartenza());
			//setta costo nel pacchetto salvato nel bean stateful
			this.pacchettoInBean.setCosto(pacchettoInBean.getCosto()+costoTotaleDeiVoli());
			//copio città visitate nel pacchetto
			copiaCittaInPacchettoBean();
			
			return true;

		}
		return false;
	}

	/*
	 * Copia le città nel pacchetto, la prima città è quella di partenza
	 * e le restanti sono quelle d'arrivo
	 * */
	private void copiaCittaInPacchettoBean() {
		ArrayList<CittaDTO> daCopiare = new ArrayList<CittaDTO>();
		for(int i=0; i<pacchettoInBean.getVoli().size();i++){
			daCopiare.add(new CittaDTO(pacchettoInBean.getVoli().get(i).getCittaPartenza()));	
		}
		pacchettoInBean.getCittaDestinazione().addAll(daCopiare);
	}

	private double costoTotaleDeiVoli() {
		double tot=0;
		for(int i=0; i<this.pacchettoInBean.getVoli().size(); i++)
			tot=tot+this.pacchettoInBean.getVoli().get(i).getCosto();
		
		return tot;
			
	}

	public static boolean stessoGiornoMeseAnno(Timestamp data1, Date data2) {
		if(data1.getDate() == data2.getDate() && data1.getMonth() == data2.getMonth()
				&& data1.getYear() == data2.getYear())
			return true;
		
		return false;
		
	}

	@Override
	public List<HotelDTO> cercaHotel(int idHotelDaCercare) {
		TypedQuery<Hotel> queryRicerca = em.createNamedQuery("Hotel.cercaHotelId", Hotel.class);
	    	
	    List<Hotel> listaHotel = queryRicerca.setParameter("idHotel", idHotelDaCercare).getResultList();
	    	
	    return convertiInListaHotelDTO(listaHotel); 
	}

	private List<HotelDTO> convertiInListaHotelDTO(List<Hotel> listaHotel) {
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



}
