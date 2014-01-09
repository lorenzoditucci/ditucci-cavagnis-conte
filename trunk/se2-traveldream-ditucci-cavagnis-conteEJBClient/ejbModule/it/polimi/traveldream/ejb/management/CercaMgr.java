package it.polimi.traveldream.ejb.management;


import it.polimi.traveldream.ejb.management.dto.GiftListDTO;

import javax.ejb.Local;

@Local
public interface CercaMgr {

	public void cerca(GiftListDTO ricerca);

}
