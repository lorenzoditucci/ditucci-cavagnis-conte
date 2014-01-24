package it.polimi.traveldream.ejb.management;

import java.util.List;

import javax.ejb.Local;

import it.polimi.traveldream.ejb.management.dto.VoloDTO;


@Local
public interface VoloMgr {
	
	public void save(VoloDTO volo);

	public void remove(int idVolo);

	List<VoloDTO> cercaVoloPerID(int idVoloDaCercare);

	public boolean controllaAppertenenzaPacchetto(VoloDTO v);

	public void aggiornaModificheVolo(VoloDTO volo);

}