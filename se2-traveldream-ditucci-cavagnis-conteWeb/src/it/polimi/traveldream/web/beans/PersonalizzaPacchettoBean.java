package it.polimi.traveldream.web.beans;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.polimi.traveldream.ejb.management.EscursioneMgr;
import it.polimi.traveldream.ejb.management.GiftListMgr;
import it.polimi.traveldream.ejb.management.HotelMgr;
import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;
import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.weld.context.ejb.Ejb;


@ManagedBean(name="personalizzaPacchettoBean")
@SessionScoped
public class PersonalizzaPacchettoBean {
	
	@EJB
	private VisualizzaDettagliGLMgr glMgr;
	
	@EJB
	private VoloMgr voloMgr;

	@EJB
	private EscursioneMgr escursioneMgr;
	
	@EJB
	private HotelMgr hotelMgr;
	/*
	 * Dati del pacchetto
	 */
	private PacchettoDTO pacchetto;
	private PacchettoDTO pacchettoOriginaleDto;
	
	private int idVolo;
	private List<VoloDTO> voliCercati;
	
	private int idEscursione;
	private List<EscursioneDTO> escursioniCercate;
	
	private int idHotel;
	private Date dataInizio;
	private Date dataFine;
	private HotelDTO hotel;
	private PernottamentoDTO pernottamentoDTO;

	public PacchettoDTO getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(PacchettoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}
	
	
	
	public PernottamentoDTO getPernottamentoDTO() {
		return pernottamentoDTO;
	}

	public void setPernottamentoDTO(PernottamentoDTO pernottamentoDTO) {
		this.pernottamentoDTO = pernottamentoDTO;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public int getIdVolo() {
		return idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}
	
	

	public int getIdEscursione() {
		return idEscursione;
	}

	public void setIdEscursione(int idEscursione) {
		this.idEscursione = idEscursione;
	}

	public List<EscursioneDTO> getEscursioniCercate() {
		return escursioniCercate;
	}

	public void setEscursioniCercate(List<EscursioneDTO> escursioniCercate) {
		this.escursioniCercate = escursioniCercate;
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
		if(voliCercati.size()==0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nessun volo trovato.","secondo mex" ));
		}
		return;
	}
	
	public String aggiungiVolo(VoloDTO volo){
		if(!pacchetto.getVoli().contains(volo)){
			pacchetto.getVoli().add(volo);
		}
			
		return "personalizza";
	}
	
	public void cercaEscursione(){
		this.escursioniCercate = escursioneMgr.cercaEscursionePerID(idEscursione);
		return;
	}
	
	public String aggiungiEscursione(EscursioneDTO escursione){
		if(!pacchetto.getEscursioni().contains(escursione)){
			this.pacchetto.getEscursioni().add(escursione);
		}
			
		return "personalizza";
	}
	
	public void creaPernottamento(){
		List<HotelDTO> hotels = hotelMgr.cercaHotelPerID(idHotel);
		if(hotels.isEmpty()){
			return;
		}
		pernottamentoDTO = new PernottamentoDTO();
		pernottamentoDTO.setDataInizio(new Timestamp(this.dataInizio.getTime()));
		pernottamentoDTO.setDataFine(new Timestamp(this.dataFine.getTime()));
		pernottamentoDTO.setHotel(hotels.get(0));
		pernottamentoDTO.setPacchetto(this.pacchetto);
		return;
		
	}
	
	public String aggiungiPernottamento(){
		if(!pacchetto.getPernotti().contains(pernottamentoDTO)){
			pacchetto.getPernotti().add(pernottamentoDTO);
		}
		return "personalizza";
	}
	
}
