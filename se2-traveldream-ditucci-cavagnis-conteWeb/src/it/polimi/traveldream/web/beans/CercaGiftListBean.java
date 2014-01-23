package it.polimi.traveldream.web.beans;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.CercaGiftListMgr;
import it.polimi.traveldream.ejb.management.PernottamentiAcquistatiMgr;
import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;
import it.polimi.traveldream.ejb.management.VoliAcquistatiProvaMGR;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentiAcquistatiDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoliAcquistatiProvaDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Pattern;

/**
 * Bean utilizzato per cercare i dati all'interno del database giftlist
 */
@ManagedBean(name="cercaGiftListBean")
@javax.faces.bean.SessionScoped
public class CercaGiftListBean {

	/**
	 * il manager della ricerca e' colui che effettua le vere operazioni tra cui le query
	 */
	@EJB
	private CercaGiftListMgr cercaMGR;
	//private GiftListDTO ricercaGiftList;//CHE MINCHIA E'?
	@EJB 
	private VisualizzaDettagliGLMgr visualizzaDettagliGLMgr;
	
	@EJB
	private VoliAcquistatiProvaMGR voliAcquistatiProvaMGR;
	
	@EJB
	private PernottamentiAcquistatiMgr pernottamentiAcquistatiMgr;
	/**
	 * qui ci salvo il risultato della ricerca
	 */
	/* risultato della ricerca - le giftlists*/
	private ArrayList<GiftListDTO> risultatoRicerca; 
	/* qua ci sono i voli acquistati in formato voliacquistatiprovadto*/
	private List<VoliAcquistatiProvaDTO> voliAcquistati;
	/* qua ci sono i VOLO acqustati*/
	private List<VoloDTO> risultatiVoliAcquistati;
	/* qua ci sono i VOLO liberi*/
	private List<VoloDTO> risultatiVoliLiberi;
	
	/*PERNOTTAMENTIACQUISTATIDTO pernottamenti acquistati*/
	private List<PernottamentiAcquistatiDTO> pernottamentiAcquistati;
	/* pernottamenti realmente acquistati*/
	private List<PernottamentoDTO> risultatiPernottamentiAcquistati;
	/* pernottamenti ancora liberi*/
	private List<PernottamentoDTO> risultatiPernottamentiLiberi;
	
	/**
	 * parametro che passo contente l'id della gift list da cercare.
	 */
	@Pattern(regexp="[0123456789]+",
		    message="Il codice giftList deve essere un numero!")
	private String idRicerca; //stringa per passare
    /**
     * Default constructor. 
     */
    public CercaGiftListBean() {
      //  ricercaGiftList= new GiftListDTO();
        setRisultatoRicerca(new ArrayList<GiftListDTO>());
    }

        	 public String cerca(){
        	    	setRisultatoRicerca(cercaMGR.cerca(Integer.parseInt(idRicerca)));
        	    	aggiornaVoliAcquistati();
        	    	if(getRisultatoRicerca().size()==0){
        	    		FacesContext.getCurrentInstance().addMessage("cercaGiftList:codiceGiftList", new FacesMessage(FacesMessage.SEVERITY_ERROR,"giftListNonTrovato", "GiftList non trovata!"));
        	    		return "";
        	    	}
        	    	return "giftList?faces-redirect=true";
        	    }

			public void aggiornaVoliAcquistati() {
				setVoliAcquistati(voliAcquistatiProvaMGR.cercaVoliAcquistati(Integer.parseInt(idRicerca)));
				smistaVoli();
			}    
    
    private boolean eAcquistato(VoloDTO volo){
    	for(int i=0; i<voliAcquistati.size();i++){
    		if(volo.getIdVolo()==voliAcquistati.get(i).getIdVolo()){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * divide i voli in acquistati e non acquistati
     */
    private void smistaVoli() {
    	risultatiVoliAcquistati = new ArrayList<VoloDTO>();
    	risultatiVoliLiberi = new ArrayList<VoloDTO>();
    	
    	
		
		for(GiftListDTO g:risultatoRicerca){
			for(PacchettoDTO p:g.getPacchettiContenuti()){
				for(VoloDTO v: p.getVoli()){
					if(eAcquistato(v)){
						risultatiVoliAcquistati.add(v);
					}else{
						risultatiVoliLiberi.add(v);
					}
				}
			}
		}
		
	}

    public void aggiornaPernottamentiDiPacchetto(){
    	/* salvo i dto dei pacchettoAcquistato */
    	setPernottamentiAcquistati(pernottamentiAcquistatiMgr.cercaPernottamentiAcquistati(Integer.parseInt(idRicerca)));
    	smistaPernottamenti();
    	
    	
    }
    
	private void smistaPernottamenti() {
		setRisultatiPernottamentiAcquistati(new ArrayList<PernottamentoDTO>());
		setRisultatiPernottamentiLiberi(new ArrayList<PernottamentoDTO>());
		for(GiftListDTO g:risultatoRicerca){
			for(PacchettoDTO p:g.getPacchettiContenuti()){
				for(PernottamentoDTO pernottamento: getPernottamentiDiPacchetto(p)){
					if(eAcquistato(pernottamento)){
						getRisultatiPernottamentiAcquistati().add(pernottamento);
					}else{
						getRisultatiPernottamentiLiberi().add(pernottamento);
					}
				}
			}
			System.out.println("pacchetti liberi size= "+getRisultatiPernottamentiLiberi().size());
		}
		
	}

	private boolean eAcquistato(PernottamentoDTO pernottamento) {
		for(int i=0;i<pernottamentiAcquistati.size();i++){
			if(pernottamento.getIdPernottametto()==pernottamentiAcquistati.get(i).getIdPernottamento()){
				return true;
			}
		}
		return false;
	}

	public List<PernottamentoDTO> getPernottamentiDiPacchetto(PacchettoDTO pacchetto){
		List<PernottamentoDTO> pernottamenti = new ArrayList<PernottamentoDTO>();
		pernottamenti = visualizzaDettagliGLMgr.cercaPernottamentiDaPacchetto(pacchetto);
		return pernottamenti;
	}

	public String getIdRicerca() {
		return idRicerca;
	}

	public void setIdRicerca(String idRicerca) {
		this.idRicerca = idRicerca;
	}

	public ArrayList<GiftListDTO> getRisultatoRicerca() {
		return risultatoRicerca;
	}

	public void setRisultatoRicerca(ArrayList<GiftListDTO> risultatoRicerca) {
		this.risultatoRicerca = risultatoRicerca;
	}

	public List<VoliAcquistatiProvaDTO> getVoliAcquistati() {
		return voliAcquistati;
	}

	public void setVoliAcquistati(List<VoliAcquistatiProvaDTO> list) {
		this.voliAcquistati = list;
	}

	public List<VoloDTO> getRisultatiVoliAcquistati() {
		return risultatiVoliAcquistati;
	}

	public void setRisultatiVoliAcquistati(List<VoloDTO> risultatiVoliAcquistati) {
		this.risultatiVoliAcquistati = risultatiVoliAcquistati;
	}

	public List<VoloDTO> getRisultatiVoliLiberi() {
		return risultatiVoliLiberi;
	}

	public void setRisultatiVoliLiberi(List<VoloDTO> risultatiVoliLiberi) {
		this.risultatiVoliLiberi = risultatiVoliLiberi;
	}

	public List<PernottamentiAcquistatiDTO> getPernottamentiAcquistati() {
		return pernottamentiAcquistati;
	}

	public void setPernottamentiAcquistati(List<PernottamentiAcquistatiDTO> pernottamentiAcquistati) {
		this.pernottamentiAcquistati = pernottamentiAcquistati;
	}

	public List<PernottamentoDTO> getRisultatiPernottamentiAcquistati() {
		return risultatiPernottamentiAcquistati;
	}

	public void setRisultatiPernottamentiAcquistati(
			List<PernottamentoDTO> risultatiPernottamentiAcquistati) {
		this.risultatiPernottamentiAcquistati = risultatiPernottamentiAcquistati;
	}

	public List<PernottamentoDTO> getRisultatiPernottamentiLiberi() {
		return risultatiPernottamentiLiberi;
	}

	public void setRisultatiPernottamentiLiberi(
			List<PernottamentoDTO> risultatiPernottamentiLiberi) {
		this.risultatiPernottamentiLiberi = risultatiPernottamentiLiberi;
	}


}
