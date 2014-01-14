package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
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
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
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
	public List<VoloDTO> voloInBean;
	
	/*
	 * Questo metodo viene chiamato automaticamente alla creazione 
	 * del bean e serve per inizializzare gli attributi che conserveranno
	 * i dati durante tutta la sessione della creazione del pacchetto
	 * */
	@PostConstruct
	public void initialize() {
		this.pacchettoInBean=new PacchettoDTO();
	    this.voloInBean=new ArrayList<VoloDTO>();
	}

	@Override
	public void instanziaPacchetto(PacchettoDTO pacchetto) {
		copiaDTOtoDTOStateful(pacchetto);
		//debug
		System.out.println("Sei nel bean statefule");
		System.out.println(pacchettoInBean.getNome());
		System.out.println(pacchettoInBean.getDescrizione());
		System.out.println(pacchettoInBean.getDisponibilitaMax());
		System.out.println(pacchettoInBean.getDisponibilitaAttuale());
		System.out.println(pacchettoInBean.getCosto());
		System.out.println(pacchettoInBean.getDataInizio());
		System.out.println(pacchettoInBean.getDataFine());
		System.out.println(pacchettoInBean.getMail());
		
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
		System.out.println(voli.get(0).getDataPartenza().getTime());
		System.out.println(pacchettoInBean.getDataInizio().getTime());
		if(voli.get(0).getDataPartenza().equals(pacchettoInBean.getDataInizio())
				&& voli.get(voli.size()-1).getDataArrivo().equals(pacchettoInBean.getDataFine()))
		{
			System.out.println("date ok al pacchetto");
			return true;

		}
			
		
		System.out.println("NO date non giuste al pacchetto");
		return false;
	}



}
