package it.polimi.traveldream.web.beans;


import it.polimi.traveldream.ejb.exception.CoerenzaException;
import it.polimi.traveldream.ejb.management.ControlloCoerenzaMGR;
import it.polimi.traveldream.ejb.management.CreaPacchettoPersonalizzatoMgr;
import it.polimi.traveldream.ejb.management.EscursioneMgr;
import it.polimi.traveldream.ejb.management.HotelMgr;
import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.VisualizzaDettagliGLMgr;
import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.pacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


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
	
	@EJB
	private ControlloCoerenzaMGR coerenzaMGR;
	
	@EJB
	private CreaPacchettoPersonalizzatoMgr creaPacchettoPersonalizzatoMgr;
	
	@EJB
	private UserMgr userMgr;
	
	@EJB
	private pacchettoMgr pacchettoMgr;
	
	private String userEmail;
	
	@ManagedProperty(value = "#{selezionaPacchettoBean}")
	private SelezionaPacchettoBean selezionaPacchettoBean;
	/*
	 * Dati del pacchetto
	 */
	private PacchettoDTO pacchetto;
	private PacchettoDTO pacchettoOriginaleDto;
	
	/*
	 * Dati riguardanti il volo
	 */
	private int idVolo;
	private List<VoloDTO> voliCercati;
	private String cittaPartenza;
	private String cittaArrivo;
	
	/*
	 * dati riguardanti le escursioni
	 */
	private String cittaEscursione;
	private List<EscursioneDTO> escursioniCercate;
	
	/*
	 * dati per la creazione del pernottamento
	 */
	private int idHotel;
	private Date dataInizio;
	private Date dataFine;
	private List<HotelDTO> hotelCercati;
	private PernottamentoDTO pernottamentoDTO;
	private String cittaHotel;
	
	
	
	
	
	
	public PersonalizzaPacchettoBean(){
	}

	
	
	public SelezionaPacchettoBean getSelezionaPacchettoBean() {
		return selezionaPacchettoBean;
	}



	public void setSelezionaPacchettoBean(
			SelezionaPacchettoBean selezionaPacchettoBean) {
		this.selezionaPacchettoBean = selezionaPacchettoBean;
	}



	public String getCittaEscursione() {
		return cittaEscursione;
	}



	public void setCittaEscursione(String cittaEscursione) {
		this.cittaEscursione = cittaEscursione;
	}



	public List<HotelDTO> getHotelCercati() {
		return hotelCercati;
	}



	public void setHotelCercati(List<HotelDTO> hotelCercati) {
		this.hotelCercati = hotelCercati;
	}



	public String getCittaHotel() {
		return cittaHotel;
	}



	public void setCittaHotel(String cittaHotel) {
		this.cittaHotel = cittaHotel;
	}



	public String getCittaPartenza() {
		return cittaPartenza;
	}


	public void setCittaPartenza(String cittaPartenza) {
		this.cittaPartenza = cittaPartenza;
	}


	public String getCittaArrivo() {
		return cittaArrivo;
	}


	public void setCittaArrivo(String cittaArrivo) {
		this.cittaArrivo = cittaArrivo;
	}


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

	/*public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}*/

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
		PacchettoDTO pacchettoDTO = new PacchettoDTO();
		List<VoloDTO> newVoliDtos =  new ArrayList<VoloDTO>();
		newVoliDtos.addAll(p.getVoli());
		pacchettoDTO.setVoli(newVoliDtos);
		List<PernottamentoDTO> newPernottamentiDtos =  new ArrayList<PernottamentoDTO>();
		newPernottamentiDtos.addAll(getPernottamenti(p));
		pacchettoDTO.setPernotti(newPernottamentiDtos);
		List<EscursioneDTO> newEscursioniDtos =  new ArrayList<EscursioneDTO>();
		newEscursioniDtos.addAll(p.getEscursioni());
		pacchettoDTO.setEscursioni(newEscursioniDtos);
		this.pacchettoOriginaleDto = pacchettoDTO;
		this.pacchettoOriginaleDto.setPernotti(getPernottamenti(p));
		return "personalizza";
	}
	
	private List<PernottamentoDTO> getPernottamenti(PacchettoDTO p) {
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
		this.voliCercati = voloMgr.cercaVoloPerPartenzaArrivo(cittaPartenza, cittaArrivo);
		if(voliCercati.size()==0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nessun volo trovato.","" ));
		}
		return;
	}
	
	public String aggiungiVolo(VoloDTO volo){
		if(pacchetto.getVoli().contains(volo)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Il volo scelto è già presente nel pacchetto","" ));
			return null;
		}
		pacchetto.getVoli().add(volo);	
		return "personalizza";
	}
	
	public void cercaEscursione(){
		this.escursioniCercate = escursioneMgr.cercaEscursionePerCitta(cittaEscursione);
		if(escursioniCercate.size()==0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nessuna escursione trovata","" ));
		}
		return;
	}
	
	public String aggiungiEscursione(EscursioneDTO escursione){
		if(!pacchetto.getEscursioni().contains(escursione)){
			this.pacchetto.getEscursioni().add(escursione);
		}
			
		return "personalizza";
	}
	
	private void creaPernottamento(HotelDTO hotel){
		if(dataInizio.before(dataFine)){
			pernottamentoDTO = new PernottamentoDTO();
			pernottamentoDTO.setDataInizio(new Timestamp(this.dataInizio.getTime()));
			pernottamentoDTO.setDataFine(new Timestamp(this.dataFine.getTime()));
			pernottamentoDTO.setHotel(hotel);
			pernottamentoDTO.setPacchetto(this.pacchetto);
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore. "
					+ "La data di inizio pernottamento deve precedere quella di fine ", ""));  
		}
		return;
	}
	
	public void cercaHotel(){
		this.hotelCercati = hotelMgr.cercaHotelPerCitta(cittaHotel);
		if(hotelCercati.size()==0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nessun volo trovato.","" ));
		}
		return;
	}

	
	public String aggiungiPernottamento(HotelDTO hotel){
		this.creaPernottamento(hotel);
		if(!pacchetto.getPernotti().contains(pernottamentoDTO)){
			pacchetto.getPernotti().add(pernottamentoDTO);
		}
		
		return "personalizza";
	}
	
	
	public String confermaPacchetto(){
		try {
			coerenzaMGR.controlloPacchetto(pacchetto);
		} catch (CoerenzaException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore."
					+ "Il pacchetto non è coerente. " + e.getMessaggi().get(0), ""));
			return null;
		}
		
		if(diverso()){
			pacchetto.setMail(userMgr.getUserDTO().getEmail());
			int id = creaPacchettoPersonalizzatoMgr.salvaPacchettoPersonalizzato(pacchetto);
			return selezionaPacchettoBean.selezionaPacchettoDTO(pacchettoMgr.prendiPerId(id).get(0));
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore."
				+ "Il pacchetto non è stato salvato perchè non hai effettuato nessuna modifica. ", ""));
		return selezionaPacchettoBean.selezionaPacchettoDTO(pacchetto);
	}
	
	private boolean diverso(){
		System.out.println("entro in diversi");
		boolean diversi = false;
		// controllo che tutti i voli siano diversi
		for(int i=0; i<pacchetto.getVoli().size() && !diversi;i++){
			boolean trovato = false;
			for(int j=0; j<pacchettoOriginaleDto.getVoli().size() && !trovato;j++){
				if(pacchetto.getVoli().get(i).equals(pacchettoOriginaleDto.getVoli().get(j))){
					trovato = true;
				}
			}	
			System.out.println("");
			if(!trovato){
				diversi = true;
			}
			
		}
		
		//controllo le escursioni
		if(pacchetto.getEscursioni().size() != pacchettoOriginaleDto.getEscursioni().size()){
			diversi = true;
		}
		for(int i=0; i<pacchetto.getEscursioni().size() && !diversi;i++){
			boolean trovato = false;
			for(int j=0; j<pacchettoOriginaleDto.getEscursioni().size() && !trovato;j++){
				if(pacchetto.getEscursioni().get(i).equals(pacchettoOriginaleDto.getEscursioni().get(j))){
					trovato = true;
				}
			}
			if(!trovato){
				diversi = true;
			}
			
		}
		
		//controllo i pernottamenti
		for(int i=0; i<pacchetto.getPernotti().size() && !diversi;i++){
			boolean trovato = false;
			for(int j=0; j<pacchettoOriginaleDto.getPernotti().size() && !trovato;j++){
				if(pacchetto.getPernotti().get(i).equals(pacchettoOriginaleDto.getPernotti().get(j))){
					trovato = true;
				}
			}
			if(!trovato){
				diversi = true;
			}
		}
		return diversi;
	}
	
}
