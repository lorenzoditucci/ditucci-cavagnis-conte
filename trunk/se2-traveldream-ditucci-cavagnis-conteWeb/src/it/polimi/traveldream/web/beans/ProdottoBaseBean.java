package it.polimi.traveldream.web.beans;

import java.sql.Timestamp;
import java.util.Calendar;

import it.polimi.traveldream.ejb.management.ProdottoBaseMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * lo uso per gestire i prodotti base acquistabili
 * @author ditu
 */
@ManagedBean(name="prodottoBaseBean")
@SessionScoped
public class ProdottoBaseBean {

	@EJB
	private ProdottoBaseMgr mgr;
    /**
     * Default constructor. 
     */
	
	private VoloDTO voloDaAcquistare;
	private GiftListDTO giftListDaAcquistare;
	private String nomeAcquirente;
	private Timestamp dataAcquisto;
	
    public ProdottoBaseBean() {
        // TODO Auto-generated constructor stub
    }
    
    public String acquistaProdottoBaseVolo(VoloDTO volo,GiftListDTO giftList){
    	/**
    	 * non so se andra' messo qua o ci mettiamo una pagina nel mezzo
    	 * nel caso spostiamo il tutto in un altra funzione
    	 */
    	
    	/**
    	 * devo registrare che il prodotto base e' stato acquistato
    	 */
    	
    	setGiftListDaAcquistare(giftList);
    	setVoloDaAcquistare(volo);
    	
    	
    	return "acquista_volo?faces-redirect=true";
    }
    /**
     * registro il volo dopo aver ricevuto la conferma
     * @return
     */
    public String registraAcquistoVolo(){
    	setDataAcquistoCalendar(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
    	mgr.registraAcquisto(voloDaAcquistare,giftListDaAcquistare,nomeAcquirente,dataAcquisto);
    	return "dettagliGiftList?faces-redirect=true";
    }
    
    
    public String acquistaProdottoBaseEscursione(EscursioneDTO escursione,GiftListDTO giftList){
    	mgr.registraAcquisto(escursione,giftList);
    	return "acquista?faces-redirect=true";
    }
    
    public String acquistaProdottoBasePernottamento(PernottamentoDTO pernottamento,GiftListDTO giftList){
    	mgr.registraAcquisto(pernottamento, giftList);
    	return "acquista?faces-redirect=true";
    }

	public VoloDTO getVoloDaAcquistare() {
		return voloDaAcquistare;
	}

	public void setVoloDaAcquistare(VoloDTO voloDaAcquistare) {
		this.voloDaAcquistare = voloDaAcquistare;
	}

	public GiftListDTO getGiftListDaAcquistare() {
		return giftListDaAcquistare;
	}

	public void setGiftListDaAcquistare(GiftListDTO giftListDaAcquistare) {
		this.giftListDaAcquistare = giftListDaAcquistare;
	}

	public String getNomeAcquirente() {
		return nomeAcquirente;
	}

	public void setNomeAcquirente(String nomeAcquirente) {
		this.nomeAcquirente = nomeAcquirente;
	}

	public Timestamp getDataAcquistoCalendar() {
		return dataAcquisto;
	}

	public void setDataAcquistoCalendar(Timestamp dataAcquistoCalendar) {
		this.dataAcquisto = dataAcquistoCalendar;
	}

}
