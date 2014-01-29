package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import javax.ejb.Local;


@Local
public interface CreaPacchettoPersonalizzatoMgr {

	int salvaPacchettoPersonalizzato(PacchettoDTO pacchetto);

}
