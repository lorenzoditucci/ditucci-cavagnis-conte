package it.polimi.traveldream.ejb.management.mgrbean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import it.polimi.traveldream.ejb.management.AcquistaPacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.UserDTO;
import it.polimi.traveldream.ejb.management.entity.Acquista;
import it.polimi.traveldream.ejb.management.entity.Escursione;
import it.polimi.traveldream.ejb.management.entity.Hotel;
import it.polimi.traveldream.ejb.management.entity.Pacchetto;
import it.polimi.traveldream.ejb.management.entity.Pernottamento;
import it.polimi.traveldream.ejb.management.entity.User;
import it.polimi.traveldream.ejb.management.entity.Volo;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 * Session Bean implementation class AcquistaPacchettoMgrBean
 */
@Stateless
@LocalBean
public class AcquistaPacchettoMgrBean implements AcquistaPacchettoMgr {

	
	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
    /**
     * Default constructor. 
     */
    public AcquistaPacchettoMgrBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean aggiungiAcquisto(UserDTO user, PacchettoDTO pDTO) {
		Pacchetto p = em.find(Pacchetto.class, pDTO.getIdPacchetto());
		User u = em.find(User.class, user.getEmail());
		if(p.getDisponibilitaAttuale() == p.getDisponibilitaMax()){
			pacchettiContenutiAcquistati(p);
		}
		
		if(p.getDisponibilitaAttuale()>0){
			Acquista newAcquisto = new Acquista();
			
			newAcquisto.setUser(u);
			newAcquisto.setPacchetto(p);
			newAcquisto.setDataAcquisto(new Timestamp(Calendar.getInstance().getTime().getTime()));
			
			em.persist(newAcquisto);
			//Modifico la disponibilità attuale del pacchetto
			p.setDisponibilitaAttuale(p.getDisponibilitaAttuale()-1);
			em.merge(p);
		}else {
			System.out.println("Acquisto non disponibile");
			return false;
		}
		
		return true;
		
	}
	
	private void pacchettiContenutiAcquistati(Pacchetto p){
		List<Escursione> escursioni = new ArrayList<Escursione>();
		List<Volo> voli = new ArrayList<Volo>();
		List<Pernottamento> pernottamenti = new ArrayList<Pernottamento>();
		escursioni.addAll(p.getEscursioni());
		voli.addAll(p.getVoli());
		// cerco i pernottamenti contenuti nel pacchetto
		TypedQuery<Pernottamento> queryRicerca = em.createNamedQuery("Pernottamento.cercaPernottamentoIdPacchetto", Pernottamento.class);
		pernottamenti.addAll(queryRicerca.setParameter("idPacchetto", p.getIdPacchetto()).getResultList());
		//setto a 1 stato "acquistato delle escursioni
		for(int i=0; i<escursioni.size();i++){
			escursioni.get(i).setAcquistato((byte)1);
			em.merge(escursioni.get(i));
		}
		//setto a 1 stato "acquistato dei voli
		for (int i = 0; i < voli.size();i++){
			voli.get(i).setAcquistato((byte)1);
			em.merge(voli.get(i));
		}
		//setto a 1 stato "acquistato" degli Hotel
		for(int i=0; i<pernottamenti.size(); i++){
			Hotel h = pernottamenti.get(i).getHotel();
			h.setAcquistato((byte)1);
		}
		
		return;
		
	}

    
    
}
