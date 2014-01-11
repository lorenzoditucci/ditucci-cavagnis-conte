package it.polimi.traveldream.web.beans;

import java.util.ArrayList;

import it.polimi.traveldream.ejb.management.CercaGiftListMgr;
import it.polimi.traveldream.ejb.management.CercaProdottoBaseMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

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
	private HotelDTO hotel;

    /**
     * Default constructor. 
     */
    public CercaProdottoBaseBean() {
        this.risultatoRicercaEscursione= new ArrayList<EscursioneDTO>();
        this.risultatoRicercaHotel = new ArrayList<HotelDTO>();
        this.risultatoRicercaVolo = new ArrayList<VoloDTO>();
        this.hotel = new HotelDTO();
        
    }

    public String cercaHotel(){  
    	System.out.println(hotel.getNome());
    	
    	setRisultatoRicercaHotel(cercaProdottoBaseMgr.cercaHotel(hotel.getNome(),hotel.getDescrizione(),hotel.getClasse()));
		System.out.println("Ho finito di cercare");

    	return "risultatiricercahotel?faces-redirect=true";
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


	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

 
}
