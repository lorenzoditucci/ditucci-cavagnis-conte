package it.polimi.traveldream.ejb.management;

import java.util.List;

import javax.ejb.Local;

import it.polimi.traveldream.ejb.management.dto.HotelDTO;

@Local
public interface HotelMgr {
	
	public void save(HotelDTO volo);

	public void remove(int idHotel);

	List<HotelDTO> cercaHotelPerID(int idHotelDaCercare);

}
