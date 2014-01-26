package it.polimi.traveldream.ejb.management;

import java.util.List;

import javax.ejb.Local;

import it.polimi.traveldream.ejb.management.dto.HotelDTO;

@Local
public interface HotelMgr {
	
	public void save(HotelDTO volo);

	public void remove(int idHotel);

	public List<HotelDTO> cercaHotelPerID(int idHotelDaCercare);

	public boolean controllaAppertenenzaPacchetto(HotelDTO h);

	public void aggiornaModificheHotel(HotelDTO hotel);

	List<HotelDTO> cercaHotelPerCitta(String citta);

}
