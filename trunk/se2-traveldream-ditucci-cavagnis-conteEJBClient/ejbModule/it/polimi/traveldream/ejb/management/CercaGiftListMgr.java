package it.polimi.traveldream.ejb.management;


import java.util.ArrayList;

import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.Local;

@Local
public interface CercaGiftListMgr {

	public ArrayList<GiftListDTO> cerca(int ricerca);


}
