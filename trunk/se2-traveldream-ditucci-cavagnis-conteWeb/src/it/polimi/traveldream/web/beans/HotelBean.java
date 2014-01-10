package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.HotelMgr;
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
}
