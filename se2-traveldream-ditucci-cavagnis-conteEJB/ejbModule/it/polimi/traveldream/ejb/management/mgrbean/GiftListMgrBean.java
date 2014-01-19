package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.entity.GiftList;
import it.polimi.traveldream.ejb.management.entity.Pacchetto;
import it.polimi.traveldream.ejb.management.entity.Volo;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class GiftListMgrBean
 */
@Stateless
@LocalBean
public class GiftListMgrBean implements GiftListMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
    /**
     * Default constructor. 
     */
	
	
	
    public GiftListMgrBean() {
        
    }
    
    
    @Override
    public ArrayList<GiftListDTO> cercaGLperMail(String userEmail) {
    	
    	TypedQuery<GiftList> queryRicerca = em.createNamedQuery("cercaGiftListPerMail", GiftList.class);
    	List<GiftList> listaGiftList =  queryRicerca.setParameter("mail", userEmail).getResultList();
		
    	return copiaListaQuery(listaGiftList);     
    }
    
    public static ArrayList<GiftListDTO> copiaListaQuery(List<GiftList> listaGiftList) {
		ArrayList<GiftListDTO> copia = new ArrayList<GiftListDTO>();
		for(int i=0; i<listaGiftList.size();i++){
    		GiftListDTO daAggiungere = new GiftListDTO();
    		daAggiungere.setIdGiftList(listaGiftList.get(i).getIdGiftList());
    		daAggiungere.setNome(listaGiftList.get(i).getNome());
    		daAggiungere.setMailCliente(listaGiftList.get(i).getMailCliente());
    		daAggiungere.setHotels(HotelMgrBean.copiaListaToDTO(listaGiftList.get(i).getHotels()));
    		daAggiungere.setEscursioni(EscursioneMgrBean.copiaListaToDTO(listaGiftList.get(i).getEscursioni()));
    		daAggiungere.setPacchetti(PacchettoMgrBean.copiaListaQuery(listaGiftList.get(i).getPacchetti()));
    		daAggiungere.setPacchettiContenuti(PacchettoMgrBean.copiaListaQuery(listaGiftList.get(i).getPacchettiContenuti()));
    		daAggiungere.setVoli(VoloMgrBean.copiaListaToDTO(listaGiftList.get(i).getVoli()));
    		
    		copia.add(daAggiungere);
    	}
		return copia;
    }
    
    @Override
    public void save(GiftListDTO giftList){
    	GiftList newGiftList = new GiftList(giftList);
		em.persist(newGiftList); 
    	
    }
    
    @Override
    public void aggiungiPacchetto(GiftListDTO glDTO,PacchettoDTO pDTO){
    	Pacchetto p = new Pacchetto(pDTO);
    	GiftList gl = new GiftList(glDTO);
    	
    	
    	gl.getPacchetti().add(p);
    	em.persist(gl);
    	
    }

}
