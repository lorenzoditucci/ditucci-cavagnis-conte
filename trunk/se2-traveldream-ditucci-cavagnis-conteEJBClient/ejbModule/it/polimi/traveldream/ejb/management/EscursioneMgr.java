package it.polimi.traveldream.ejb.management;

import java.util.List;

import javax.ejb.Local;

import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;


@Local
public interface EscursioneMgr {
	
	public void save(EscursioneDTO escursione);

	public void remove(int id);

	List<EscursioneDTO> cercaEscursionePerID(int idEscursioneDaCercare);

}
