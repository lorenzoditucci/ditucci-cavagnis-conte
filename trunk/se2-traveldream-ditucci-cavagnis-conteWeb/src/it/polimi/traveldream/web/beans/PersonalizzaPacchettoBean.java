package it.polimi.traveldream.web.beans;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;
import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.weld.context.ejb.Ejb;


@ManagedBean(name="personalizzaPacchettoBean")
@SessionScoped
public class PersonalizzaPacchettoBean {
	
	@EJB
	private VisualizzaDettagliGLMgr glMgr;
	
	@EJB
	private VoloMgr voloMgr;

	private PacchettoDTO pacchetto;
	private PacchettoDTO pacchettoOriginaleDto;
	
	private int idVolo;
	private List<VoloDTO> voliCercati;

	public PacchettoDTO getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(PacchettoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}
	
	
	
	public int getIdVolo() {
		return idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}
	
	

	public List<VoloDTO> getVoliCercati() {
		return voliCercati;
	}

	public void setVoliCercati(List<VoloDTO> voliCercati) {
		this.voliCercati = voliCercati;
	}

	public String personalizza(PacchettoDTO p){
		this.pacchetto = p;
		this.pacchetto.setPernotti(getPernottamenti(p));
		this.pacchettoOriginaleDto = p;
		this.pacchettoOriginaleDto.setPernotti(getPernottamenti(p));
		return "personalizza";
	}
	
	public List<PernottamentoDTO> getPernottamenti(PacchettoDTO p) {
		List<PernottamentoDTO> pernottamenti = new ArrayList<PernottamentoDTO>();
		pernottamenti = glMgr.cercaPernottamentiDaPacchetto(p);
		return pernottamenti;
	}
	
	public void rimuoviVolo(VoloDTO volo){
		this.pacchetto.getVoli().remove(volo);
		return;
	}
	
	public void rimuoviPernottamento(PernottamentoDTO pernottamento){
		this.pacchetto.getPernotti().remove(pernottamento);
		return;
	}
	
	public void rimuoviEscursione(EscursioneDTO escursione){
		this.pacchetto.getEscursioni().remove(escursione);
		return;
	}
	
	public void cercaVolo(){
		this.voliCercati = voloMgr.cercaVoloPerID(idVolo);
	}
	
	public String aggiungiVolo(VoloDTO volo){
		if(!pacchetto.getVoli().contains(volo)){
			pacchetto.getVoli().add(volo);
		}
			
		return "personalizza";
	}
	
}
