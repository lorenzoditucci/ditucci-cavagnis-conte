package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.polimi.traveldream.ejb.management.pacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.entity.GiftList;
import it.polimi.traveldream.ejb.management.entity.Pacchetto;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

/**
 * Session Bean implementation class PacchettoMgrBean
 */
@Stateless
@Local(pacchettoMgr.class)
@LocalBean
public class PacchettoMgrBean implements pacchettoMgr {
	
	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
    /**
     * Default constructor. 
     */
    public PacchettoMgrBean() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see pacchettoMgr#prendiTutti()
     */
    @Override
    public ArrayList<PacchettoDTO> prendiTutti() {
    	System.out.println("entro in prendi tutti");
    	TypedQuery<Pacchetto> queryRicerca = em.createNamedQuery("Pacchetto.findAll", Pacchetto.class);
    	List<Pacchetto> listaPacchetti =  queryRicerca.getResultList();
		
    	/**
    	 * definisco la lista di data type object pacchetto che torno al chiamante
    	 */
    	ArrayList<PacchettoDTO> pacchettiDaTornare = new ArrayList<PacchettoDTO>();
    	/**
    	 * copio dati
    	 */
    	for(int i=0; i<listaPacchetti.size();i++){
    		PacchettoDTO daAggiungere = new PacchettoDTO();
    		daAggiungere.setIdPacchetto(listaPacchetti.get(i).getIdPacchetto());
    		daAggiungere.setCosto(listaPacchetti.get(i).getCosto());
    		daAggiungere.setDataFine(listaPacchetti.get(i).getDataFine());
    		daAggiungere.setDataInizio(listaPacchetti.get(i).getDataInizio());
    		daAggiungere.setDescrizione(listaPacchetti.get(i).getDescrizione());
    		daAggiungere.setDisponibilitaAttuale(listaPacchetti.get(i).getDisponibilitaAttuale());
    		daAggiungere.setDisponibilitaMax(listaPacchetti.get(i).getDisponibilitaMax());
    		daAggiungere.setMail(listaPacchetti.get(i).getMail());
    		daAggiungere.setNome(listaPacchetti.get(i).getNome());
    		
    		pacchettiDaTornare.add(daAggiungere);
    	}
    	return pacchettiDaTornare;
  
    	
        
    }

}
