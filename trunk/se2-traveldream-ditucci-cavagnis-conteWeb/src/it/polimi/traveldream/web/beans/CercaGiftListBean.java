package it.polimi.traveldream.web.beans;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.CercaGiftListMgr;
import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
	@EJB private VisualizzaDettagliGLMgr visualizzaDettagliGLMgr;
	/**
	 * qui ci salvo il risultato della ricerca
	 */
	private ArrayList<GiftListDTO> risultatoRicerca;
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
        	 * metodo per la redirect
        	 */
        	if(getRisultatoRicerca().size()==0){
        		FacesContext.getCurrentInstance().addMessage("cercaGiftList:codiceGiftList", new FacesMessage(FacesMessage.SEVERITY_ERROR,"giftListNonTrovato", "GiftList non trovata!"));
        		return "";
        	}
        	return "giftList?faces-redirect=true";
		} catch (Exception e) {
			setRisultatoRicerca(new ArrayList<GiftListDTO>());
			getRisultatoRicerca().get(0).setNome("Non trovato, Riprova");
			return null;
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
}
