package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.PernottamentiAcquistatiDTO;

import java.util.List;

public interface PernottamentiAcquistatiMgr {

	List<PernottamentiAcquistatiDTO> cercaPernottamentiAcquistati(int idGiftList);

}
