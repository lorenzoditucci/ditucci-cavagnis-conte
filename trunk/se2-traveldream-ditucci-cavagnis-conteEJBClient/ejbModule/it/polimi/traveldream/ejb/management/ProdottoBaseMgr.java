package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

public interface ProdottoBaseMgr {

	void registraAcquisto(VoloDTO volo, GiftListDTO giftList);

	void registraAcquisto(EscursioneDTO escursione, GiftListDTO giftList);

	void registraAcquisto(PernottamentoDTO pernottamento, GiftListDTO giftList);

}
