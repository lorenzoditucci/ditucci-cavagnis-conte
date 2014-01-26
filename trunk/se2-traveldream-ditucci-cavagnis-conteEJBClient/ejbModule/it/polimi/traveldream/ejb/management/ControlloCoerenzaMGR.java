package it.polimi.traveldream.ejb.management;

import javax.ejb.Local;

import it.polimi.traveldream.ejb.exception.CoerenzaException;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

@Local
public interface ControlloCoerenzaMGR {
	
	public void controlloPacchetto(PacchettoDTO p) throws CoerenzaException;

	void controllaGiftList(GiftListDTO g) throws CoerenzaException;

}
