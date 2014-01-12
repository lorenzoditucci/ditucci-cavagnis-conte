package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import java.util.ArrayList;
import java.util.Date;

public interface cercaPacchettoMgr {

	ArrayList<PacchettoDTO> cercaPacchettiParam(Date dataPartenza,Date dataRitorno, Double costo,
			String nomePacchetto);

}
