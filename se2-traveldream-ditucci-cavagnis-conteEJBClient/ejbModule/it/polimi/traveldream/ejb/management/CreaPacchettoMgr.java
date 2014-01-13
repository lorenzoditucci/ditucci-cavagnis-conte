package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import javax.ejb.Local;

@Local
public interface CreaPacchettoMgr {

	public void instanziaPacchetto(PacchettoDTO pacchetto);
	
}
