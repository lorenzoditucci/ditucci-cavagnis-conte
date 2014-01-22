package it.polimi.traveldream.web.beans;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.CercaGiftListMgr;
import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;
import it.polimi.traveldream.ejb.management.VoliAcquistatiProvaMGR;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
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
    	try {
    		setRisultatoRicerca(cercaMGR.cerca(Integer.parseInt(idRicerca)));
        	System.out.println("sto cercando...");
        	/**
        	 * cerco i voli che NON sono stati acquistati
        	 */
        	System.out.println("id ricerca = "+Integer.parseInt(idRicerca));
        	setVoliAcquistati(voliAcquistatiProvaMGR.cercaVoliAcquistati(Integer.parseInt(idRicerca)));
        	smistaVoli();
        	System.out.println(voliAcquistati.get(0).getIdVolo());
        	/**
        	 * metodo per la redirect
        	 */
        	if(getRisultatoRicerca().size()==0){
        		FacesContext.getCurrentInstance().addMessage("cercaGiftList:codiceGiftList", new FacesMessage(FacesMessage.SEVERITY_ERROR,"giftListNonTrovato", "GiftList non trovata!"));
        		return "";
        	}
        	return "giftList?faces-redirect=true";
		} catch (Exception e) {
			setRisultatoRicerca(new ArrayList<GiftListDTO>());
			//getRisultatoRicerca().get(0).setNome("Non trovato, Riprova");
			return null;
		}	
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
}
