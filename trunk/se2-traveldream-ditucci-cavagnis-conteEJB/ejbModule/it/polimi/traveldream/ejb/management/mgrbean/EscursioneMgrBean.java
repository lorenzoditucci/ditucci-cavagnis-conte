package it.polimi.traveldream.ejb.management.mgrbean;

import it.polimi.traveldream.ejb.management.EscursioneMgr;
import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.entity.Escursione;
import it.polimi.traveldream.ejb.management.entity.Volo;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EscursioneMgrBean implements EscursioneMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;

	@Override
	public void save(EscursioneDTO escursione) {
		Escursione newEscursione = new Escursione(escursione);
		em.persist(newEscursione); 
		}

}
