package it.polimi.traveldream.ejb.management.mgrbean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.faces.bean.SessionScoped;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
import com.sun.xml.ws.runtime.dev.Session;

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
    	/*
    	 * utilizzo come query la default della entity in quanto mi servono tutti i pacchetti.
    	 * La query di default e' stata leggermente modificata in modo da permettere l'ordine inverso
    	 * 
    	 */
    	Timestamp adesso = new Timestamp(Calendar.getInstance().getTime().getTime());
    	String queryRicerca = "SELECT p "
    			+ "FROM Pacchetto p WHERE p.mail IN("
    			+ "SELECT i.email "
    			+ "FROM  Impiegato i ) "
    			+ "AND p.dataInizio > :adesso "
    			+ " ORDER BY p.idPacchetto DESC";
    	TypedQuery<Pacchetto> q = em.createQuery(queryRicerca, Pacchetto.class);
    	q.setParameter("adesso", adesso);
    	List<Pacchetto> listaPacchetti = q.getResultList();
    	
    	
    	/*
    	 * ritorno direttamente la copia della lista di pacchetti -risultato della query-
    	 */
    	return copiaListaQuery(listaPacchetti);     
    }

    /**
     * Dati x copiare la list di pacchetti che vengono dalla query in una lista di DTO che torno
     * al chiamante.
	 * 
	 */
	public static ArrayList<PacchettoDTO> copiaListaQuery(List<Pacchetto> listaPacchetti) {
		ArrayList<PacchettoDTO> copia = new ArrayList<PacchettoDTO>();
		for(int i=0; i<listaPacchetti.size();i++){
    		PacchettoDTO daAggiungere = new PacchettoDTO();

    		daAggiungere = listaPacchetti.get(i).convertiInDTO();
    		
    		copia.add(daAggiungere);
    	}
		return copia;
	}
	
	@Override
	public ArrayList<PacchettoDTO> prendiAcquistati(String emailUtente){
		System.out.println("Sono dentro prendiAcquistati - emailUtente = "+emailUtente);
		/**
		 * fittizia, la query va cambiata!!
		 */
		TypedQuery<Pacchetto> queryRicerca = em.createNamedQuery("cercaDaEmail", Pacchetto.class);
		queryRicerca.setParameter("email", emailUtente);
		List<Pacchetto> listaPacchetti = queryRicerca.getResultList();
		return copiaListaQuery(listaPacchetti);	
	}

	@Override
	public ArrayList<PacchettoDTO> prendiPerId(int idPacchetto) {
		TypedQuery<Pacchetto> query = em.createNamedQuery("cercaPacchettiId", Pacchetto.class);
		query.setParameter("id", idPacchetto);
		List<Pacchetto> listaPacchetti = query.getResultList();
		return copiaListaQuery(listaPacchetti);
	}

	@Override
	public ArrayList<PacchettoDTO> prendiPerIdEMail(int id, String mailCreatore) {
		TypedQuery<Pacchetto> query = em.createNamedQuery("CercaPacchettiIDEMail", Pacchetto.class);
		query.setParameter("id", id);
		query.setParameter("mail", mailCreatore);
		List<Pacchetto> listaPacchetti = query.getResultList();
		return copiaListaQuery(listaPacchetti);
	}

}
