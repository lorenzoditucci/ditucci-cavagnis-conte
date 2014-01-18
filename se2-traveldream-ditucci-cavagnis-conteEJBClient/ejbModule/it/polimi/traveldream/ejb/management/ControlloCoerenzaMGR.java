package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.exception.CoerenzaException;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

public interface ControlloCoerenzaMGR {
	
	public void controlloPacchetto(PacchettoDTO p) throws CoerenzaException;

	void controllaGiftList(GiftListDTO g) throws CoerenzaException;

}
