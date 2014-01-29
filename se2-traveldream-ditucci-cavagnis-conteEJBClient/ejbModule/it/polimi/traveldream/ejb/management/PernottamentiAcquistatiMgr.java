package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.PernottamentiAcquistatiDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;

import java.util.List;

public interface PernottamentiAcquistatiMgr {

	List<PernottamentiAcquistatiDTO> cercaPernottamentiAcquistati(int idGiftList);

	List<PernottamentoDTO> pernottamentoPerID(int id);

}
