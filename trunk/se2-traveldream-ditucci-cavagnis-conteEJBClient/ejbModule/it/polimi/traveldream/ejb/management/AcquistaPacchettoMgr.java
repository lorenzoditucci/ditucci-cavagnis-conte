package it.polimi.traveldream.ejb.management;

import javax.ejb.Local;

import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.UserDTO;

@Local
public interface AcquistaPacchettoMgr {
	
	public void aggiungiAcquisto(UserDTO user, PacchettoDTO pacchettoSelezionato);

}
