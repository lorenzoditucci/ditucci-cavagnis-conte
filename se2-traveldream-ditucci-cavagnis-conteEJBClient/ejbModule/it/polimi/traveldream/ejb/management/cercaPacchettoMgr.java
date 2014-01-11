package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import java.util.ArrayList;

public interface cercaPacchettoMgr {

	ArrayList<PacchettoDTO> cercaPacchettiParam(String emailPacchetto,
			String nomePacchetto);

}
