package it.polimi.traveldream.ejb.management.mgrbean;

import it.polimi.traveldream.ejb.exception.CoerenzaException;
import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.entity.EscursioniAcquistate;
import it.polimi.traveldream.ejb.management.entity.GiftList;
import it.polimi.traveldream.ejb.management.entity.Pacchetto;
import it.polimi.traveldream.ejb.management.entity.PernottamentiAcquistati;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
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
	
	@EJB
	private ControlloCoerenzaMGRBean controlloCoerenzaMGRBean;
	
	
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
    		daAggiungere.setPernottamenti(PernottamentiAcquistati.copiaToPernottamentiAcquistatiDTO(listaGiftList.get(i).getPernottamenti()));
    		daAggiungere.setEscursioni(EscursioniAcquistate.copiaListaToDTO(listaGiftList.get(i).getEscursioni()));
    		daAggiungere.setPacchettiContenuti(PacchettoMgrBean.copiaListaQuery(listaGiftList.get(i).getPacchettiContenuti()));
    		daAggiungere.setVoli(VoloMgrBean.copiaToDTO(listaGiftList.get(i).getVoli()));
    		
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
    public void aggiungiPacchetto(GiftListDTO glDTO,PacchettoDTO pDTO) throws CoerenzaException{    	
    	GiftList gl = em.find(GiftList.class, glDTO.getIdGiftList());
    	Pacchetto p = em.find(Pacchetto.class, pDTO.getIdPacchetto());
    	p.setDisponibilitaAttuale(p.getDisponibilitaAttuale()-1);
    	List<Pacchetto> lista = new ArrayList<Pacchetto>();
    	lista.addAll(gl.getPacchettiContenuti());
    	List<PacchettoDTO> listaDTO = new ArrayList<PacchettoDTO>(glDTO.getPacchettiContenuti());
    	listaDTO.add(pDTO);
    	glDTO.setPacchettiContenuti(listaDTO);
    	controlloCoerenzaMGRBean.controllaGiftList(glDTO);
    
    	
    	if(!lista.contains(p)){
    		lista.add(p);
    		gl.setPacchettiContenuti(lista);
    	}
    	em.merge(gl);
    	em.merge(p);
    }
    
    @Override
    public void rimuovi(PacchettoDTO pDTO, GiftListDTO glDTO){
    	GiftList gl = em.find(GiftList.class, glDTO.getIdGiftList());
    	Pacchetto p = em.find(Pacchetto.class, pDTO.getIdPacchetto());
    	List<Pacchetto> lista = new ArrayList<Pacchetto>();
    	
    	lista.addAll(gl.getPacchettiContenuti());
    	if(lista.contains(p)){
    		lista.remove(p);
    		gl.setPacchettiContenuti(lista);
    	}
    	em.merge(gl);
    }

}
