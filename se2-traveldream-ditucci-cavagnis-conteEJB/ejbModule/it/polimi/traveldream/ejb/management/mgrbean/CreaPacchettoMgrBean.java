package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import it.polimi.traveldream.ejb.management.CreaPacchettoMgr;
import it.polimi.traveldream.ejb.management.cercaPacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

/*
 * Bean stateful per mantenimento della sessione
 * durante la creazione di un pacchetto
 */

@Stateful
@Local(CreaPacchettoMgr.class)
@LocalBean
public class CreaPacchettoMgrBean implements CreaPacchettoMgr{
	public PacchettoDTO pacchettoInBean;
	public List<VoloDTO> voloInBean;
	
	/*
	 * Questo metodo viene chiamato automaticamente alla creazione 
	 * del bean e serve per inizializzare gli attributi che conserveranno
	 * i dati durante tutta la sessione della creazione del pacchetto
	 * */
	@PostConstruct
	public void initialize() {
		this.pacchettoInBean=new PacchettoDTO();
	    this.voloInBean=new ArrayList<VoloDTO>();
	}

	@Override
	public void instanziaPacchetto(PacchettoDTO pacchetto) {
		copiaDTOtoDTOStateful(pacchetto);
		System.out.println(pacchettoInBean.getNome()); //debug
	}

	/*
	 * copia il dto in arrivo dalla pagina web nel dto contenuto nello statfule
	 */
	private void copiaDTOtoDTOStateful(PacchettoDTO pacchetto) {
		this.pacchettoInBean.setNome(pacchetto.getNome());
		this.pacchettoInBean.setDescrizione(pacchetto.getDescrizione());
		this.pacchettoInBean.setDisponibilitaMax(pacchetto.getDisponibilitaMax());
		this.pacchettoInBean.setDataInizio(pacchetto.getDataInizio());
		this.pacchettoInBean.setDataFine(pacchetto.getDataFine());
		//E' stato appena creato e quindi non l'ha ancora comprato nessuno
		this.pacchettoInBean.setDisponibilitaAttuale(pacchetto.getDisponibilitaMax());
		//il costo è ancora da calcolare
		this.pacchettoInBean.setCosto(0.0);
	}



}
