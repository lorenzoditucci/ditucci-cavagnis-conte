package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.HotelMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="hotelBean")
@RequestScoped
public class HotelBean {
	
	@EJB
	private HotelMgr hotelMgr;

	private HotelDTO hotel;
	
	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public HotelBean() {
		hotel = new HotelDTO();
	}
	
	public String aggiungiHotel() {
		hotelMgr.save(hotel);	
		return "aggiungiprodottobase";
	}
	
	public String rimuoviHotel(HotelDTO h) {
		
		if(hotelMgr.controllaAppertenenzaPacchetto(h)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Il pb fa parte di un pacchetto!",  "Non puoi rimuovere un prodotto base che fa parte di un pacchetto");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
		}
		
		
			if(h.getAcquistato()==0){
				hotelMgr.remove(h.getIdHotel());
				return "cercahotel";		
			}				
			else{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",  "Non puoi rimuovere un prodotto base che fa parte di un pacchetto acquistato");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return "";
			}
				
			
		}
}
