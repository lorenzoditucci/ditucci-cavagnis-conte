package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.HotelDTO;

import java.util.ArrayList;

import javax.ejb.Local;

@Local
public interface CercaProdottoBaseMgr {

	ArrayList<HotelDTO> cercaHotel(String nome);

}
