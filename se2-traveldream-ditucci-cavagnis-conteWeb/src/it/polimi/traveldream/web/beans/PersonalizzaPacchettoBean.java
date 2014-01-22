package it.polimi.traveldream.web.beans;

import java.util.ArrayList;
import java.util.List;

import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="personalizzaPacchettoBean")
@SessionScoped
public class PersonalizzaPacchettoBean {
	
	@EJB
	private VisualizzaDettagliGLMgr glMgr;

	private PacchettoDTO pacchetto;

	public PacchettoDTO getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(PacchettoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}
	
	public String personalizza(PacchettoDTO p){
		this.pacchetto = p;
		return "personalizza";
	}
	
	public List<PernottamentoDTO> getPernottamenti(){
		List<PernottamentoDTO> pernottamenti = new ArrayList<PernottamentoDTO>();
		pernottamenti = glMgr.cercaPernottamentiDaPacchetto(pacchetto);
		return pernottamenti;
	}
	
	
}
