package it.polimi.traveldream.web.beans;

import java.util.ArrayList;

import it.polimi.traveldream.ejb.management.CercaGiftListMgr;
import it.polimi.traveldream.ejb.management.CercaProdottoBaseMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.EscursioneRicercaDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.HotelRicercaDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.dto.VoloRicercaDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Bean utilizzato per cercare i dati all'interno del database dei prodotti base
 */
@ManagedBean(name="cercaProdottoBaseBean")
@javax.faces.bean.SessionScoped
public class CercaProdottoBaseBean {

	@EJB
	private CercaProdottoBaseMgr cercaProdottoBaseMgr;

	/*
	 * Liste per il risultato della ricerca
	 */
	private ArrayList<HotelDTO> risultatoRicercaHotel;
	private ArrayList<VoloDTO> risultatoRicercaVolo;
	private ArrayList<EscursioneDTO> risultatoRicercaEscursione;
	
	/*
	 * Parametri di ricerca per l'Hotel
	 */
	private HotelRicercaDTO hotel;
	private VoloRicercaDTO volo;
	private EscursioneRicercaDTO escursione;

	/**
     * Default constructor. 
     */
    public CercaProdottoBaseBean() {
        this.risultatoRicercaEscursione= new ArrayList<EscursioneDTO>();
        this.risultatoRicercaHotel = new ArrayList<HotelDTO>();
        this.risultatoRicercaVolo = new ArrayList<VoloDTO>();
        this.hotel = new HotelRicercaDTO();
        this.volo = new VoloRicercaDTO();
        this.escursione = new EscursioneRicercaDTO();
        
    }

    public String cercaHotel(){  
    	setRisultatoRicercaHotel(cercaProdottoBaseMgr.cercaHotel(hotel.getIdHotel()));
    	
    	if(getRisultatoRicercaHotel().isEmpty()){
    		FacesContext.getCurrentInstance().addMessage("ricercaHotel:idHotel", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attenzione!", "La ricerca non ha prodotto risultati"));
    		return "";
    	}
    	
    	return "risultatiricercahotel?faces-redirect=true";
    }
    
    
    public String cercaVolo(){  
        
    	setRisultatoRicercaVolo(cercaProdottoBaseMgr.cercaVolo(volo.getIdVolo()));
    	
    	if(getRisultatoRicercaVolo().isEmpty()){
    		FacesContext.getCurrentInstance().addMessage("ricercaVolo:idVolo", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attenzione!", "La ricerca non ha prodotto risultati"));
    		return "";
    	}
    	
    	return "risultatiricercavolo?faces-redirect=true";
    }
    
    public String cercaEscursione(){
    	setRisultatoRicercaEscursione(cercaProdottoBaseMgr.cercaEscursione(escursione.getIdEscursione()));
    	
    	if(getRisultatoRicercaEscursione().isEmpty()){
    		FacesContext.getCurrentInstance().addMessage("ricercaEscursione:idEscursione", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attenzione!", "La ricerca non ha prodotto risultati"));
    		return "";
    	}

    	return "risultatiricercaescursione?faces-redirect=true";
    }
    
    /*
     * Getter e setter per le liste di ricerca
     */
    
    public ArrayList<HotelDTO> getRisultatoRicercaHotel() {
		return risultatoRicercaHotel;
	}

	public void setRisultatoRicercaHotel(ArrayList<HotelDTO> risultatoRicercaHotel) {
		this.risultatoRicercaHotel = risultatoRicercaHotel;
	}

	public ArrayList<VoloDTO> getRisultatoRicercaVolo() {
		return risultatoRicercaVolo;
	}

	public void setRisultatoRicercaVolo(ArrayList<VoloDTO> risultatoRicercaVolo) {
		this.risultatoRicercaVolo = risultatoRicercaVolo;
	}

	public ArrayList<EscursioneDTO> getRisultatoRicercaEscursione() {
		return risultatoRicercaEscursione;
	}

	public void setRisultatoRicercaEscursione(
			ArrayList<EscursioneDTO> risultatoRicercaEscursione) {
		this.risultatoRicercaEscursione = risultatoRicercaEscursione;
	}
	public HotelRicercaDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelRicercaDTO hotel) {
		this.hotel = hotel;
	}

	public VoloRicercaDTO getVolo() {
		return volo;
	}

	public void setVolo(VoloRicercaDTO volo) {
		this.volo = volo;
	}

	public EscursioneRicercaDTO getEscursione() {
		return escursione;
	}

	public void setEscursione(EscursioneRicercaDTO escursione) {
		this.escursione = escursione;
	}

	

 
}
