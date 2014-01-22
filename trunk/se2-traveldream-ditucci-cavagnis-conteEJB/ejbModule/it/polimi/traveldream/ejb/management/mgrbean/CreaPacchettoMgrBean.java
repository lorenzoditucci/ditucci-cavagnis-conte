package it.polimi.traveldream.ejb.management.mgrbean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.Object;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.traveldream.ejb.exception.CoerenzaException;
import it.polimi.traveldream.ejb.management.CreaPacchettoMgr;
import it.polimi.traveldream.ejb.management.cercaPacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.CittaDTO;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.entity.Citta;
import it.polimi.traveldream.ejb.management.entity.Escursione;
import it.polimi.traveldream.ejb.management.entity.GiftList;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import it.polimi.traveldream.ejb.management.entity.Pacchetto;
import it.polimi.traveldream.ejb.management.entity.Pernottamento;
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
	
	@EJB
	private ControlloCoerenzaMGRBean controlloCoerenzaMgr;
	
	
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
		//il costo � ancora da calcolare
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
			//copio citt� visitate nel pacchetto
			copiaCittaInPacchettoBean();
			
			return true;

		}
		return false;
	}

	/*
	 * Copia le citt� nel pacchetto, la prima citt� � quella di partenza
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

	@Override
	public boolean inserisciPernottamentiInPacchettoInstanziato(
			List<PernottamentoDTO> pernottamenti) {
		if(!checkConsistenzaPacchettoPernottamenti(pernottamenti))
			return false;
		
		pacchettoInBean.setPernotti(new ArrayList<PernottamentoDTO>());
		pacchettoInBean.getPernotti().addAll(pernottamenti);
		
		aggiornaPrezzoPacchettoInBeanConPernottamenti();

		return true;
	}

	private void aggiornaPrezzoPacchettoInBeanConPernottamenti() {
		double prezzoPernottamento=0;
		for(int i=0; i<pacchettoInBean.getPernotti().size(); i++){
			Timestamp dataInizio =pacchettoInBean.getPernotti().get(i).getDataInizio();
			Timestamp dataFine =pacchettoInBean.getPernotti().get(i).getDataFine();
			dataInizio.setHours(0);
			dataInizio.setMinutes(0);
			dataFine.setHours(0);
			dataFine.setMinutes(0);
			int giorni=(int) (dataFine.getTime()-dataInizio.getTime())/86400000;
			prezzoPernottamento+=giorni*pacchettoInBean.getPernotti().get(i).getHotel().getCosto();
			pacchettoInBean.setCosto(pacchettoInBean.getCosto()+prezzoPernottamento);
			
		}
		
		
	}

	private boolean checkConsistenzaPacchettoPernottamenti(
			List<PernottamentoDTO> pernottamenti) {
		//controllo se ci sono tanti aerei quante citt� visitate
		if(pernottamenti.size()!=pacchettoInBean.getVoli().size()-1){
			System.out.println("Non va bene la grandezza");
			return false;	
		}
		
		for(int i=0; i<pernottamenti.size(); i++){
			if(!(pacchettoInBean.getVoli().get(i).getCittaArrivo().equals(pernottamenti.get(i).getHotel().getCitta()))
				|| 	!stessoGiornoMeseAnno(new Timestamp(pacchettoInBean.getVoli().get(i).getDataArrivo().getTime()),pernottamenti.get(i).getDataInizio())
				|| !stessoGiornoMeseAnno(new Timestamp(pacchettoInBean.getVoli().get(i+1).getDataPartenza().getTime()),pernottamenti.get(i).getDataFine())
				){System.out.println("Errore nelle date"); return false; } 
		}
	
		return true;
	}

	@Override
	public List<EscursioneDTO> cercaEscursione(int idEscursioneDaCercare) {
		TypedQuery<Escursione> queryRicerca = em.createNamedQuery("Escursione.cercaEscursioneId", Escursione.class);
	    	
	    List<Escursione> listaEscursioni = queryRicerca.setParameter("idEscursione", idEscursioneDaCercare).getResultList();
	    	
	    return convertiInListaEscursioneDTO(listaEscursioni); 
	}

	private List<EscursioneDTO> convertiInListaEscursioneDTO(
			List<Escursione> listaEscursioni) {
		ArrayList<EscursioneDTO> copia = new ArrayList<EscursioneDTO>();
		for(int i=0; i<listaEscursioni.size();i++){
    		EscursioneDTO daAggiungere = new EscursioneDTO();
    		daAggiungere.setIdEscursione(listaEscursioni.get(i).getIdEscursione());
    		daAggiungere.setNome(listaEscursioni.get(i).getNome());
    		daAggiungere.setDescrizione(listaEscursioni.get(i).getDescrizione());
    		daAggiungere.setDataInizio(listaEscursioni.get(i).getDataInizio());
    		daAggiungere.setDataFine(listaEscursioni.get(i).getDataFine());
    		daAggiungere.setCitta(listaEscursioni.get(i).getCitta());
    		daAggiungere.setCosto(listaEscursioni.get(i).getCosto());
    		daAggiungere.setAcquistato(listaEscursioni.get(i).getAcquistato());
    		copia.add(daAggiungere);
    	}
		return copia;	
		
	}

	@Override
	public boolean inserisciEscursioniInPacchettoInstanziato(
			List<EscursioneDTO> escursioni) {
		/*coerenza con pacchetto*/
		for(int i=0; i<escursioni.size(); i++){
			/*se non esiste un pernottamento che include l'escursione allora non � coerente*/
			if(!esisteUnHotelCoerenteAllEscursione(escursioni.get(i))){
				return false;
			}
		}
		pacchettoInBean.setEscursioni(new ArrayList<EscursioneDTO>());
		pacchettoInBean.getEscursioni().addAll(escursioni);
		aggiornaPrezzoPacchettoConEscursioni(escursioni);
		
		return true;

	}

	private void aggiornaPrezzoPacchettoConEscursioni(
			List<EscursioneDTO> escursioni) {
		double prezzoEscursioni=0;
		for(int i=0; i<escursioni.size(); i++){
			prezzoEscursioni+=escursioni.get(i).getCosto();
		}
		pacchettoInBean.setCosto(pacchettoInBean.getCosto()+prezzoEscursioni);
		System.out.println(pacchettoInBean.getCosto());
		
	}

	private boolean esisteUnHotelCoerenteAllEscursione(
			EscursioneDTO escursione) {
		for(int i=0; i<pacchettoInBean.getPernotti().size(); i++){
			if(pacchettoInBean.getPernotti().get(i).getHotel().getCitta().equals(escursione.getCitta())
					&& escursione.getDataInizio().getTime()>=pacchettoInBean.getPernotti().get(i).getDataInizio().getTime()
					&& escursione.getDataFine().getTime()<=pacchettoInBean.getPernotti().get(i).getDataFine().getTime()){
				return true;
			}
		}
		return false;
	}

	@Override
	public PacchettoDTO ottieniPacchettoDaConfermare() throws CloneNotSupportedException {
		return pacchettoInBean.clona();
	}
/*
 * Salva il pacchetto nella persistenza dei dati
 * */
	@Remove
	@Override
	public boolean salvaPacchettoInDB(){
		Pacchetto newPacchetto = new Pacchetto();
		newPacchetto.setNome(pacchettoInBean.getNome());
		newPacchetto.setDescrizione(pacchettoInBean.getDescrizione());
		newPacchetto.setDisponibilitaAttuale(pacchettoInBean.getDisponibilitaAttuale());
		newPacchetto.setDisponibilitaMax(pacchettoInBean.getDisponibilitaMax());
		newPacchetto.setDataInizio(pacchettoInBean.getDataInizio());
		newPacchetto.setDataFine(pacchettoInBean.getDataFine());
		newPacchetto.setCosto(pacchettoInBean.getCosto());
		newPacchetto.setMail(pacchettoInBean.getMail());
		//relations
		newPacchetto.setCittaDestinazione(new ArrayList<Citta>());
		newPacchetto.setVoli(new ArrayList<Volo>());
		newPacchetto.setEscursioni(new ArrayList<Escursione>());
		newPacchetto.setGiftLists(new ArrayList<GiftList>());
		
		//persist
		em.persist(newPacchetto);
		
		
		//association
		List<Volo> voli = new ArrayList<Volo>();
		for(int i=0; i<pacchettoInBean.getVoli().size(); i++){
			 TypedQuery<Volo> queryRicerca = em.createNamedQuery("Volo.cercaVoloId", Volo.class);
		 	 List<Volo> listaVolo = queryRicerca.setParameter("idVolo", pacchettoInBean.getVoli().get(i).getIdVolo()).getResultList();
		 	 voli.add(listaVolo.get(0));	
		}
		newPacchetto.getVoli().addAll(voli);
		
		
		List<Escursione> escursioni = new ArrayList<Escursione>();
		for(int i=0; i<pacchettoInBean.getEscursioni().size(); i++){
			 TypedQuery<Escursione> queryRicerca = em.createNamedQuery("Escursione.cercaEscursioneId", Escursione.class);
		 	 List<Escursione> listaEscursione = queryRicerca.setParameter("idEscursione", pacchettoInBean.getEscursioni().get(i).getIdEscursione()).getResultList();
		 	 escursioni.add(listaEscursione.get(0));	
		}
		
		newPacchetto.getEscursioni().addAll(escursioni);
		
		
		List<Citta> citta = new ArrayList<Citta>();
		for(int i=0; i<pacchettoInBean.getCittaDestinazione().size(); i++){
			 TypedQuery<Citta> queryRicerca = em.createNamedQuery("Citta.cercaCittaNome", Citta.class);
		 	 List<Citta> listaCitta = queryRicerca.setParameter("nomeCitta", pacchettoInBean.getCittaDestinazione().get(i).getNome()).getResultList();
		     citta.add(listaCitta.get(0));	
		}
		newPacchetto.getCittaDestinazione().addAll(citta);
		
		//pernottamento
		for(int i=0; i<pacchettoInBean.getPernotti().size(); i++){
			Pernottamento newPernottamento=new Pernottamento(pacchettoInBean.getPernotti().get(i));

			TypedQuery<Hotel> queryRicerca = em.createNamedQuery("Hotel.cercaHotelId", Hotel.class);
		 	List<Hotel> listaHotel = queryRicerca.setParameter("idHotel", pacchettoInBean.getPernotti().get(i).getHotel().getIdHotel()).getResultList();
			
		 	newPernottamento.setHotel(listaHotel.get(0));
		 	newPernottamento.setPacchetto(newPacchetto);
		 	
		 	em.persist(newPernottamento);
		}
	
		//manca pernottamento che sono da creare
		return true;
	}

	@PreDestroy
	public void exit(){
		System.out.println("Sessione staful crea pacchetto terminata");
	}

	@Override
	public void controlloPacchetto(PacchettoDTO pacchetto) throws CoerenzaException {
		controlloCoerenzaMgr.controlloPacchetto(pacchetto);
	}
	
	
}
