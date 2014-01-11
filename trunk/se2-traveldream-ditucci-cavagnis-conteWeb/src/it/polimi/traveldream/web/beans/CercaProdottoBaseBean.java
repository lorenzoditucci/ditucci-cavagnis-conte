package it.polimi.traveldream.web.beans;

import java.util.ArrayList;

import it.polimi.traveldream.ejb.management.CercaGiftListMgr;
import it.polimi.traveldream.ejb.management.CercaProdottoBaseMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.HotelRicercaDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import it.polimi.traveldream.ejb.management.dto.VoloRicercaDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

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
    

	/**
     * Default constructor. 
     */
    public CercaProdottoBaseBean() {
        this.risultatoRicercaEscursione= new ArrayList<EscursioneDTO>();
        this.risultatoRicercaHotel = new ArrayList<HotelDTO>();
        this.risultatoRicercaVolo = new ArrayList<VoloDTO>();
        this.hotel = new HotelRicercaDTO();
        this.volo=new VoloRicercaDTO();
        
    }

    public String cercaHotel(){  
    
    	setRisultatoRicercaHotel(cercaProdottoBaseMgr.cercaHotel(hotel.getIdHotel()));
		System.out.println("Ho finito di cercare");

    	return "risultatiricercahotel?faces-redirect=true";
    }
    
    
    public String cercaVolo(){  
        
    	setRisultatoRicercaVolo(cercaProdottoBaseMgr.cercaVolo(volo.getIdVolo()));

    	return "risultatiricercavolo?faces-redirect=true";
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

	

 
}
