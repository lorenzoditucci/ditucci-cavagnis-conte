package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.exception.CoerenzaException;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

@Local
public interface GestionePacchettoMgr {

	public PacchettoDTO cercaPacchettoId(int idPacchettoDaCercare);

	public boolean eliminaPacchettoId(PacchettoDTO pacchetto);

	public List<VoloDTO> cercaVolo(int idVoloDaCercare);

	public List<HotelDTO> cercaHotel(int idHotelDaCercare);

	public List<EscursioneDTO> cercaEscursione(int idEscursioneDaCercare);

	public boolean controllaCoerenza(PacchettoDTO pacchetto) throws CoerenzaException;

	public boolean controllaChePacchettoNonFacciaParteDiGL(
			PacchettoDTO pacchetto);


	public List<String> ottieniAcquirentiDelPacchetto(PacchettoDTO pacchetto);

	
}
