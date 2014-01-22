package it.polimi.traveldream.ejb.management.mgrbean;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import it.polimi.traveldream.ejb.management.ProdottoBaseMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.entity.GiftList;
import it.polimi.traveldream.ejb.management.entity.PernottamentiAcquistati;
import it.polimi.traveldream.ejb.management.entity.Pernottamento;
import it.polimi.traveldream.ejb.management.entity.VoliAcquistatiProva;
import it.polimi.traveldream.ejb.management.entity.Volo;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sun.util.calendar.ZoneInfo;

import com.sun.xml.ws.runtime.dev.Session;

/**
 * Session Bean implementation class ProdottoBaseMgrBean
 */
@Stateless
@Local(ProdottoBaseMgr.class)
@LocalBean
public class ProdottoBaseMgrBean implements ProdottoBaseMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
    /**
     * Default constructor. 
     */
    public ProdottoBaseMgrBean() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ProdottoBaseMgr#registraAcquistoVolo(VoloDTO)
     */
    @Override
    public void registraAcquisto(VoloDTO volo,GiftListDTO giftList, String nomeAcquirente, Timestamp dataAcquisto) {
    	/**
    	 * aggiungo il volo alla lista e faccio update della giftList
    	 * devo anche controllare che il booleano di acquistato sia ad uno?
    	 */
    	
    	/*
    	Volo v; //= new Volo();
		v = em.find(Volo.class, volo.getIdVolo());
    	controllaAcquisto(v);
    	GiftList g;// = new GiftList();
    	g = em.find(GiftList.class, giftList.getIdGiftList());
    	if(!g.getVoli().contains(v)){
    		//g.getVoli().add(v);
    		
    		VoliAcquistati daAggiungere = new VoliAcquistati();
    		daAggiungere.setDataAcquisto(new Timestamp(0));
    		daAggiungere.setNomeAcquirente("carlo");
    		
    		VoliAcquistatiPK daAggiungerePk = new VoliAcquistatiPK();
    		daAggiungerePk.setIdGiftList(giftList.getIdGiftList());
    		daAggiungerePk.setIdVolo(volo.getIdVolo());
    		
    		daAggiungere.setId(daAggiungerePk);
    		
    		System.out.println("DATA ACQUISTA" + daAggiungere.getDataAcquisto());
    		
    		g.getVoli().add(daAggiungere);
    		
    		em.persist(daAggiungere);
    		em.merge(g);
    		
    		/* PROVA SALVATAGGIO TABELLA PONTE*/
    		
    		//em.persist(acquisto);
    		/*
    		VoliAcquistatiPK acquistoPk = new VoliAcquistatiPK();
    		acquistoPk.setIdGiftList(giftList.getIdGiftList());
    		acquistoPk.setIdVolo(v.getIdVolo());
    		
    		String nome = new String("carlo");
    		Date data = new Date(Date.UTC(2014, 8, 21, 00, 00, 00));
    		VoliAcquistati acquisto = new VoliAcquistati();
    		acquisto.setDataAcquisto(new Timestamp(data.getTime()));
    		acquisto.setNomeAcquirente(nome);
    		
    		acquisto.setId(acquistoPk);
    		em.persist(acquisto);
    		//em.merge(g);
    		}
    		 */
    	
    	Volo v = new Volo();
    	v = em.find(Volo.class, volo.getIdVolo());
    	controllaAcquisto(v);
    	
    	GiftList g = new GiftList();
    	g = em.find(GiftList.class,giftList.getIdGiftList());
    	
    	if(!g.getVoli().contains(v)){
    		VoliAcquistatiProva acquistato = new VoliAcquistatiProva();
    		acquistato.setIdVolo(v.getIdVolo());
    		acquistato.setIdGiftList(g.getIdGiftList());
    		acquistato.setDataAcquisto(dataAcquisto);
    		acquistato.setNomeAcquirente(nomeAcquirente);
    		em.persist(acquistato);
    		g.getVoli().add(acquistato);
    		em.merge(g);
    	}
    	
    	
    	
    }
    @Override
	public void registraAcquisto(PernottamentoDTO pernottamentoDaAcquistare,GiftListDTO giftListDaAcquistare, String nomeAcquirente,Timestamp dataAcquisto) {
		Pernottamento p = new Pernottamento();
		p = em.find(Pernottamento.class, pernottamentoDaAcquistare.getIdPernottametto());
		controllaAcquisto(p);
		
		GiftList g = new GiftList();
		g = em.find(GiftList.class, giftListDaAcquistare.getIdGiftList());
		
		if(!g.getPernottamenti().contains(p)){
			PernottamentiAcquistati acquistato = new PernottamentiAcquistati();
			acquistato.setDataAcquisto(dataAcquisto);
			acquistato.setIdGiftList(g.getIdGiftList());
			acquistato.setIdPernottamento(pernottamentoDaAcquistare.getIdPernottametto());
			acquistato.setNomeAcquirente(nomeAcquirente);
			em.persist(acquistato);
			g.getPernottamenti().add(acquistato);
			em.merge(g);
			
		}
		
	}
    
    private void controllaAcquisto(Pernottamento p) {
		if(p.getHotel().getAcquistato()==0){
			p.getHotel().setAcquistato((byte)1);
			em.merge(p);
		}
		
	}

	/**
     * controlla se il volo registrato e' gia' stato acquistato e nel caso lo registra
     * @param v2
     */
	private void controllaAcquisto(Volo v) {
		if(v.getAcquistato()==0){
			v.setAcquistato((byte)1);
			em.merge(v);
		}
		
	}

	@Override
	public void registraAcquisto(EscursioneDTO escursione, GiftListDTO giftList) {
		controllaAcquisto(escursione);
		giftList.getEscursioni().add(escursione);
		em.merge(escursione);
		
	}

	private void controllaAcquisto(EscursioneDTO escursione) {
		if(escursione.getAcquistato()==0){
			escursione.setAcquistato(1);
			em.merge(escursione);
		}
		
	}

	@Override
	public void registraAcquisto(PernottamentoDTO pernottamento,GiftListDTO giftList) {
		/**
		 * COMPLETARE- ERRORE DB ESISTE HOTELS, NOI QUA PARLIAMO DI PERNOTTAMENTI!!!
		 */
	}

	
    

}
