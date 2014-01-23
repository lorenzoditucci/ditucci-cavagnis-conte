package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.EscursioniAcquistateDTO;

import java.util.List;

public interface EscursioniAcquistateMgr {

	List<EscursioniAcquistateDTO> cercaEscursioniAcquistate(int parseInt);

}
