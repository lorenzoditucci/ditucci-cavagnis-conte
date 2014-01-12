package it.polimi.traveldream.ejb.management;

import javax.ejb.Local;

import it.polimi.traveldream.ejb.management.dto.VoloDTO;


@Local
public interface VoloMgr {
	
	public void save(VoloDTO volo);

	public void remove(int idVolo);

}