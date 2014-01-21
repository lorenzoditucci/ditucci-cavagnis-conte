package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;

import java.util.List;

import javax.ejb.Local;

@Local
public interface VisualizzaDettagliGLMgr {

	List<PernottamentoDTO> cercaPernottamentiDaPacchetto(PacchettoDTO pacchetto);
	
}
