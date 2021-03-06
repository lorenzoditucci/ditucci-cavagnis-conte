package it.polimi.traveldream.ejb.management;

import java.util.List;

import it.polimi.traveldream.ejb.management.dto.CittaDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.Local;


@Local
public interface CittaMgr {
	
	public void save(CittaDTO citta);

	public void remove(int idCitta);

	public List<String> findAllCittaPerAutoCompletamento();

}
