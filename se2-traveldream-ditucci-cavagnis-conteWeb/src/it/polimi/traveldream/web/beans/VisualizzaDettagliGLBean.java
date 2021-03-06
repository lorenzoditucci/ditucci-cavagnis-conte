package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.management.EscursioneMgr;
import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.PernottamentiAcquistatiMgr;
import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;
import it.polimi.traveldream.ejb.management.VoliAcquistatiProvaMGR;
import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="visualizzaDettagliGLBean")
@SessionScoped

public class VisualizzaDettagliGLBean {
	
	@EJB
	private VisualizzaDettagliGLMgr visualizzaDettagliGLMgrMgr;
	
	@EJB
	private VoliAcquistatiProvaMGR voliAcquistatiProvaMGR;
	@EJB
	private GiftListMgr glMgr;
	@EJB
	private VoloMgr voloMgr;
	@EJB
	private EscursioneMgr escursioneMgr;
	@EJB
	private PernottamentiAcquistatiMgr perMgr; 
	
	private GiftListDTO giftList;
	
	private List<VoloDTO> voliAcquistati;
	
	
	public VisualizzaDettagliGLBean(){
		this.giftList = new GiftListDTO();
	}
	
	
	public String dettagli(GiftListDTO giftList){
		this.setGiftList(giftList);
		List<PernottamentoDTO> pernottamenti = new ArrayList<PernottamentoDTO>();
		for(int i=0; i<giftList.getPacchettiContenuti().size();i++){
			pernottamenti = visualizzaDettagliGLMgrMgr.cercaPernottamentiDaPacchetto(giftList.getPacchettiContenuti().get(i));
			giftList.getPacchettiContenuti().get(i).setPernotti(pernottamenti);
		}
		return "visualizza";
	}

	public List<VoloDTO> getVoliAcquistati() {
		return voliAcquistati;
	}


	public void setVoliAcquistati(List<VoloDTO> voliAcquistati) {
		this.voliAcquistati = voliAcquistati;
	}


	public GiftListDTO getGiftList() {
		return giftList;
	}

	public void setGiftList(GiftListDTO giftList) {
		this.giftList = giftList;
	}
	
	public List<PacchettoDTO> getPacchetti(){
		return giftList.getPacchettiContenuti();
	}
	
	public List<PernottamentoDTO> getPernottamentiDiPacchetto(PacchettoDTO pacchetto){
		List<PernottamentoDTO> pernottamenti = new ArrayList<PernottamentoDTO>();
		pernottamenti = visualizzaDettagliGLMgrMgr.cercaPernottamentiDaPacchetto(pacchetto);
		return pernottamenti;
	}
	
	public String rimuoviPacchetto(PacchettoDTO p) {
		//controllo che non ci siano prodotti base acquistati.
		//controllo sui voli
		
		giftList.setVoli(voliAcquistatiProvaMGR.cercaVoliAcquistati(giftList.getIdGiftList()));
		
		for(int i=0; i<giftList.getVoli().size() ;i++){
			for(int j=0; i<p.getVoli().size();i++){
				if(giftList.getVoli().get(i).getIdVolo() == p.getVoli().get(j).getIdVolo()){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Non è possibile rimuovere"
							+ "il pacchetto. Un volo è già stato acquistato", ""));
					return null;
				}
			}
		}
		
		//controllo le escursioni
		for(int i=0; i<giftList.getEscursioni().size() ;i++){
			for(int j=0; i<p.getEscursioni().size();i++){
				if(giftList.getEscursioni().get(i).getIdEscursione() == p.getEscursioni().get(j).getIdEscursione()){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Non è possibile rimuovere"
							+ "il pacchetto. Una escursione è già stata acquistata", ""));
					return null;
				}
			}
		}
		
		System.out.println("Grandezza p.pernotti: "+ p.getPernotti().size());
		System.out.println("grandezza gl.pernottamenti acquistati: "+ giftList.getPernottamenti().size());
		//controllo i pernottamenti
		for(int i=0; i<giftList.getPernottamenti().size() ;i++){
			for(int j=0; i<p.getPernotti().size();i++){
				if(giftList.getPernottamenti().get(i).getIdPernottamento() == p.getPernotti().get(j).getIdPernottametto()){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Non è possibile rimuovere"
							+ "il pacchetto. Un pernottamento è già stato acquistato", ""));
					return null;
				}
			}
		}
		
		glMgr.rimuovi(p,this.giftList);
		return "gestione";
	}

	public List<VoloDTO> ottieniVoli(){
		List<VoloDTO> lista = new ArrayList<VoloDTO>();
		for(int i=0; i<giftList.getVoli().size();i++){
			lista.add(voloMgr.cercaVoloPerID(giftList.getVoli().get(i).getIdVolo()).get(0));
		}
		
		return lista;
		
	}
	
	public String acquirenteVolo(int id){
		for(int i = 0; i<giftList.getVoli().size();i++){
			if(id==giftList.getVoli().get(i).getIdVolo())
				return giftList.getVoli().get(i).getNomeAcquirente();
		}
		return "null";
	}
	
	public Timestamp ottieniDataAcquistoVolo(int id){
		for(int i = 0; i<giftList.getVoli().size();i++){
			if(id==giftList.getVoli().get(i).getIdVolo())
				return giftList.getVoli().get(i).getDataAcquisto();
		}
		return null;
	}
	
	public List<EscursioneDTO> ottieniEscursioni(){
		List<EscursioneDTO> lista = new ArrayList<EscursioneDTO>();
		for(int i=0; i<giftList.getEscursioni().size();i++){
			lista.add(escursioneMgr.cercaEscursionePerID(giftList.getEscursioni().get(i).getIdEscursione()).get(0));
		}
		
		return lista;
		
	}
	
	public String acquirenteEscursione(int id){
		for(int i = 0; i<giftList.getEscursioni().size();i++){
			if(id==giftList.getEscursioni().get(i).getIdEscursione())
				return giftList.getEscursioni().get(i).getNomeAcquirente();
		}
		return "null";
	}
	
	public Timestamp ottieniDataAcquistoEscursione(int id){
		for(int i = 0; i<giftList.getEscursioni().size();i++){
			if(id==giftList.getEscursioni().get(i).getIdEscursione())
				return giftList.getEscursioni().get(i).getDataAcquisto();
		}
		return null;
	}
	
	public List<PernottamentoDTO> ottieniPernottamenti(){
		List<PernottamentoDTO> lista = new ArrayList<PernottamentoDTO>();
		for(int i=0; i<giftList.getPernottamenti().size();i++){
			lista.add(perMgr.pernottamentoPerID(giftList.getPernottamenti().get(i).getIdPernottamento()).get(0));
		}
		
		return lista;
		
	}
	
	public String acquirentePernottamento(int id){
		for(int i = 0; i<giftList.getPernottamenti().size();i++){
			if(id==giftList.getPernottamenti().get(i).getIdPernottamento())
				return giftList.getPernottamenti().get(i).getNomeAcquirente();
		}
		return "null";
	}
	
	public Timestamp ottieniDataAcquistoPernottamento(int id){
		for(int i = 0; i<giftList.getPernottamenti().size();i++){
			if(id==giftList.getPernottamenti().get(i).getIdPernottamento())
				return giftList.getPernottamenti().get(i).getDataAcquisto();
		}
		return null;
	}
}
