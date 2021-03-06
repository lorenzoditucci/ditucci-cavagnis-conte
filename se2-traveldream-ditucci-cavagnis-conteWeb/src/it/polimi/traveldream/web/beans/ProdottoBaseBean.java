package it.polimi.traveldream.web.beans;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import it.polimi.traveldream.ejb.management.ProdottoBaseMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.javaIdentifierType;

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
	private PernottamentoDTO pernottamentoDaAcquistare;
	private EscursioneDTO escursioneDaAcquistare;
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
    	
    	if(controllaTempo(volo.getDataPartenza())){
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Prodotto Base non Acquistabile", "il prodotto base non e' acquistabile in quanto scaduto!"));
    		return "";
    	}
    	
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
    	if(controllaTempo(escursione.getDataInizio())){
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Prodotto Base non Acquistabile", "il prodotto base non e' acquistabile in quanto scaduto!"));
    		return "";
    	}
    	setGiftListDaAcquistare(giftList);
    	setEscursioneDaAcquistare(escursione);
    	return "acquista_escursione?faces-redirect=true";
    }
    
    private boolean controllaTempo(Date dataInizio) {
		//Timestamp adesso = new Timestamp(Calendar.getInstance().getTime().getTime());
    	Date adesso = new Date();
    	adesso = new java.sql.Timestamp(adesso.getTime());
		System.out.println("data adesso "+adesso.getTime());
		if(dataInizio.after(adesso)){
			System.out.println(dataInizio.getTime()+" >>>> "+adesso.getTime());
			return false;
		}
		return true;
	}

	public String registraAcquistoEscursione(){
    	setDataAcquistoCalendar(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
    	mgr.registraAcquisto(escursioneDaAcquistare,giftListDaAcquistare,nomeAcquirente,dataAcquisto);
    	return "dettagliGiftList?faces-redirect=true";
    }
    
    public String acquistaProdottoBasePernottamento(PernottamentoDTO pernottamento,GiftListDTO giftList){
    	if(controllaTempo(pernottamento.getDataInizio())){
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Prodotto Base non Acquistabile", "il prodotto base non e' acquistabile in quanto scaduto!"));
    		return "";
    	}
    	setGiftListDaAcquistare(giftList);
    	setPernottamentoDaAcquistare(pernottamento);
    	
    	return "acquista_pernottamento?faces-redirect=true";
    }
    
    public String registraAcquistoPernottamento(){
    	setDataAcquistoCalendar(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
    	mgr.registraAcquisto(pernottamentoDaAcquistare, giftListDaAcquistare,nomeAcquirente,dataAcquisto);
    	return "dettagliGiftList?faces-redirect=true";
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

	public PernottamentoDTO getPernottamentoDaAcquistare() {
		return pernottamentoDaAcquistare;
	}

	public void setPernottamentoDaAcquistare(PernottamentoDTO pernottamentoDaAcquistare) {
		this.pernottamentoDaAcquistare = pernottamentoDaAcquistare;
	}

	public EscursioneDTO getEscursioneDaAcquistare() {
		return escursioneDaAcquistare;
	}

	public void setEscursioneDaAcquistare(EscursioneDTO escursioneDaAcquistare) {
		this.escursioneDaAcquistare = escursioneDaAcquistare;
	}

}
