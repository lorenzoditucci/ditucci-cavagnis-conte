package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.entity.GiftList;
import it.polimi.traveldream.ejb.management.entity.Pernottamento;

@Stateful
@LocalBean
public class VisualizzaDettagliGLMgrBean implements VisualizzaDettagliGLMgr {
	
	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
	public VisualizzaDettagliGLMgrBean() {
	// TODO Auto-generated constructor stub
}
	
	
	@Override 
	public List<PernottamentoDTO> cercaPernottamentiDaPacchetto(PacchettoDTO pacchetto){
		TypedQuery<Pernottamento> queryRicerca = em.createNamedQuery("Pernottamento.cercaPernottamentoIdPacchetto", Pernottamento.class);
		if(pacchetto == null){
			System.out.println("nullo");
			return null;
		}else{
			System.out.println(pacchetto.getIdPacchetto());
		
		List<Pernottamento> listaPernottamenti = queryRicerca.setParameter("idPacchetto", pacchetto.getIdPacchetto()).getResultList();
		
		return Pernottamento.copiaToPernottamentoDTO(listaPernottamenti);
		}
	}
	
}
