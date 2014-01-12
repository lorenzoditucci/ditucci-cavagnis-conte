package it.polimi.traveldream.ejb.management.mgrbean;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;

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
}
