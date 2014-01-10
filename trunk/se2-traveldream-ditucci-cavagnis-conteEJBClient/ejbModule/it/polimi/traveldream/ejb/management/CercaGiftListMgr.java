package it.polimi.traveldream.ejb.management;


import it.polimi.traveldream.ejb.management.dto.GiftListDTO;

import javax.ejb.Local;

@Local
public interface CercaGiftListMgr {

	public GiftListDTO cerca(int ricerca);

}
