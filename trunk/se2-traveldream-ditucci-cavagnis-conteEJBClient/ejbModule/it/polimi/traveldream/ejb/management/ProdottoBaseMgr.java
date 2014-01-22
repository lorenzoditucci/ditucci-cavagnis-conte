package it.polimi.traveldream.ejb.management;

import java.sql.Timestamp;

import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

public interface ProdottoBaseMgr {

	void registraAcquisto(VoloDTO volo, GiftListDTO giftList, String nomeAcquirente, Timestamp dataAcquisto);

	void registraAcquisto(EscursioneDTO escursione, GiftListDTO giftList);

	void registraAcquisto(PernottamentoDTO pernottamento, GiftListDTO giftList);

	void registraAcquisto(PernottamentoDTO pernottamentoDaAcquistare,
			GiftListDTO giftListDaAcquistare, String nomeAcquirente,
			Timestamp dataAcquisto);

}
