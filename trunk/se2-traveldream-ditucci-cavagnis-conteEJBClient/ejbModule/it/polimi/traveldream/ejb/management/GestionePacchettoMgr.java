package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import java.util.ArrayList;

import javax.ejb.Local;

@Local
public interface GestionePacchettoMgr {

	public PacchettoDTO cercaPacchettoId(int idPacchettoDaCercare);
	
}
