package it.polimi.traveldream.ejb.management;

import java.util.List;

import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.Local;

@Local
public interface CreaPacchettoMgr {

	public void instanziaPacchetto(PacchettoDTO pacchetto);

	public List<VoloDTO> cercaVolo(int idVoloDaCercare);

	public boolean inserisciVoliInPacchettoInstanziato(List<VoloDTO> voli);

	public List<HotelDTO> cercaHotel(int idHotelDaCercare);

	public boolean inserisciPernottamentiInPacchettoInstanziato(
			List<PernottamentoDTO> pernottamenti);

	public List<EscursioneDTO> cercaEscursione(int idEscursioneDaCercare);

	public boolean inserisciEscursioniInPacchettoInstanziato(
			List<EscursioneDTO> escursioni);

	public PacchettoDTO ottieniPacchettoDaConfermare() throws CloneNotSupportedException;
	
}
