package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.traveldream.ejb.management.CercaGiftListMgr;
import it.polimi.traveldream.ejb.management.CittaMgr;
import it.polimi.traveldream.ejb.management.dto.CittaDTO;
import it.polimi.traveldream.ejb.management.entity.Citta;
import it.polimi.traveldream.ejb.management.entity.Pacchetto;
import it.polimi.traveldream.ejb.management.entity.Volo;

@Stateless
@Local(CittaMgr.class)
@LocalBean
public class CittaMgrBean implements CittaMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;	
	
	/*
	 * Metodo che salva sul database un nuova città se non esiste già
	 * 
	 */
	@Override
	public void save(CittaDTO citta) {
		/*Cerco la citta per nome con una query*/
		TypedQuery<Citta> query = em.createNamedQuery("Citta.cercaCittaNome", Citta.class);
		query.setParameter("nomeCitta", citta.getNome());
		List<Citta> risultato = query.getResultList();
		
		/*Creo la città e la salvo nella base di dati*/
		if(risultato.isEmpty()){
			System.out.println("Nuova citta");
			Citta newCitta = new Citta(citta);
			em.persist(newCitta); 
		}else{ 
			/*altrimenti è già presente*/
			System.out.println("Città già presente nel db");
			}	
	}

	@Override
	public void remove(int idCitta) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<String> findAllCittaPerAutoCompletamento() {
		TypedQuery<Citta> queryRicerca = em.createNamedQuery("Citta.findAll", Citta.class);
    	List<Citta> listaCitta =  queryRicerca.getResultList();
    
		return convertiListaCittaInListaStringhe(listaCitta);
	}

	private List<String> convertiListaCittaInListaStringhe(List<Citta> listaCitta) {
		List<String> listaCittaPerAutoComplete= new ArrayList<String>();
		
		for(Citta c: listaCitta){
			listaCittaPerAutoComplete.add(c.getNome());
		}
		
		return listaCittaPerAutoComplete;
	}

}
