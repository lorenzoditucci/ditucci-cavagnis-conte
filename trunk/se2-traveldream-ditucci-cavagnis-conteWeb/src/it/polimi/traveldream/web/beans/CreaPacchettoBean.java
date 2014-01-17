package it.polimi.traveldream.web.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.polimi.traveldream.ejb.management.CreaPacchettoMgr;
import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@ManagedBean(name="creaPacchettoBean")
@SessionScoped
public class CreaPacchettoBean {
	
	@EJB
	private CreaPacchettoMgr creaPacchettoMgr;
	
	/*Pacchetto da creare*/
	private PacchettoDTO pacchetto;
	
	@NotNull
	@Future
	private Date dataInizio;


	@NotNull
	@Future
	private Date dataFine;
	
	@NotNull
	private int idVoloDaCercare;
	
	@NotNull
	private int idHotelDaCercare;
	
	@NotNull
	@Future
	private Date dataInizioPernottamento;
	
	@NotNull
	@Future
	private Date dataFinePernottamento;
	
	private VoloDTO volo;
	
	private PernottamentoDTO pernottamento;

	/*Lista di voli da aggiungere*/
	private List<VoloDTO> voli;
	
	private List<PernottamentoDTO> pernottamenti;
	
	/*
	 * costruttore che inizializza i DTO
	 * */
	public CreaPacchettoBean() {
		this.pacchetto= new PacchettoDTO();
		this.voli= new ArrayList<VoloDTO>();
		this.pernottamenti=new ArrayList<PernottamentoDTO>();
	}
	
	
	public String inizializzaPacchetto(String mailDipendente){ 
		
		pacchetto.setDataInizio(new Timestamp(dataInizio.getTime()));
		pacchetto.setDataFine(new Timestamp(dataFine.getTime()));
		pacchetto.setMail(mailDipendente);

		if(dataInizio.after(dataFine) || dataInizio.equals(dataFine)){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Intervallo date errato", "Non ha senso!!!"));
		     return "";
		}
	
		creaPacchettoMgr.instanziaPacchetto(getPacchetto());
		
		return "aggiungiVoliInPacchetto"; 
	}

	public String cercaEAggiungiVolo(){
		volo=new VoloDTO();
  
    	if(creaPacchettoMgr.cercaVolo(idVoloDaCercare).isEmpty()){
    		
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Non trovato", "La ricerca non ha prodotto risultati"));
    		return null;
    	}else{
    		this.volo=creaPacchettoMgr.cercaVolo(idVoloDaCercare).get(0);
    		if(giaContenuto()){
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Giˆ in lista", "La ricerca non ha prodotto risultati"));
        		return null;
    		}
    		getVoli().add(volo);
    		return null;
    	}	
	}
	
	
	public String aggiungiVoliInPacchetto(){
		/*controllo sulla correttezza della lista di voli
		 * da inviare allo stateful
		*/
		if(!dateECittaConseguenti()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore nella lista dei voli", "La ricerca non ha prodotto risultati"));
			return null;
		}
		
		//vado sul bean stateful
		if(!creaPacchettoMgr.inserisciVoliInPacchettoInstanziato(getVoli())){
			//non coerenti
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Date non coerenti al pacchetto", "La ricerca non ha prodotto risultati"));
			return null;	
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Date coerenti al pacchetto", "La ricerca non ha prodotto risultati"));
		
		/*Prossima pagina: selezione degli hotel*/
		return "aggiungiHotelInPacchetto";
	}
	
	public String cercaEAggiungiPernottamento(){
		this.pernottamento=new PernottamentoDTO();
  
    	if(creaPacchettoMgr.cercaHotel(idHotelDaCercare).isEmpty()){
    		
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Non trovato", "La ricerca non ha prodotto risultati"));
    		return null;
    	}else{	
    		HotelDTO hotelScelto=creaPacchettoMgr.cercaHotel(idHotelDaCercare).get(0);
    		
    		if(hotelGiaContenuto(hotelScelto)){
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Giˆ in lista", "La ricerca non ha prodotto risultati"));
        		return null;
    		}
    		
    		if(dataInizioPernottamento.getTime()>=dataFinePernottamento.getTime()){
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Intervallo date errato", "Intervallo date errato"));
        		return null;
    		}

    		//creare il pernottamento
    		pernottamento.setDataInizio(new Timestamp(this.dataInizioPernottamento.getTime()));
    		pernottamento.setDataFine(new Timestamp(this.dataFinePernottamento.getTime()));
    		pernottamento.setHotel(hotelScelto);
    		
    		//aggiungilo in lista
    		getPernottamenti().add(pernottamento);
    		return null;
    	}	
		
	}
	
	
	public String aggiungiPernottamentiInPacchetto(){
		if(!dateConseguentiPernottamenti()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore temporale nella lista dei pernottamenti", "La ricerca non ha prodotto risultati"));
			return null;
		}
		
		//vado sul bean stateful
		if(!creaPacchettoMgr.inserisciPernottamentiInPacchettoInstanziato(getPernottamenti())){
			//non coerenti
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Date non coerenti al pacchetto", "La ricerca non ha prodotto risultati"));
			return null;	
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Date coerenti al pacchetto", "La ricerca non ha prodotto risultati"));
		
		/*Prossima pagina: selezione degli hotel*/
		return "aggiungiEscursioniInPacchetto";
	}

	private boolean dateConseguentiPernottamenti() {
		if(this.getPernottamenti().size()<1)
			return false;
		
		for(int i=0; i<getPernottamenti().size()-1; i++){
			if(getPernottamenti().get(i).getDataFine().getTime()>getPernottamenti().get(i+1).getDataInizio().getTime())
				return false;	
		}
		
		return true;
	}


	private boolean hotelGiaContenuto(HotelDTO hotelScelto) {
		for(int i=0; i<pernottamenti.size(); i++){
			if(pernottamenti.get(i).getHotel().getIdHotel()==hotelScelto.getIdHotel())
				return true;
		}
		return false;
	}


	/*
	 * Verifica che i voli siano conseguenti e che sia una catena
	 * */
	private boolean dateECittaConseguenti() {
		
		if(getVoli().size()<2)
			return false;
		
		for(int i=0; i<getVoli().size()-1; i++){
			//date conseguenti
			if(getVoli().get(i).getDataArrivo().after(getVoli().get(i+1).getDataPartenza()))
				return false;
			//cittˆ conseguenti
			if(!getVoli().get(i).getCittaArrivo().equals(getVoli().get(i+1).getCittaPartenza()))
				return false;
		}
		
		//circolaritˆ dei voli
		if(!getVoli().get(0).getCittaPartenza().equals(getVoli().get(getVoli().size()-1).getCittaArrivo()))
			return false;
			
			return true;
		
	}


	private boolean giaContenuto() {
		for(int i=0; i< getVoli().size(); i++)
		{
			if(getVoli().get(i).getIdVolo()==volo.getIdVolo())
				return true;
		}		
		return false;
	}


	public PacchettoDTO getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(PacchettoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}

	public List<VoloDTO> getVoli() {
		return voli;
	}

	public void setVoli(List<VoloDTO> voli) {
		this.voli = voli;
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
	
	public int getIdVoloDaCercare() {
		return idVoloDaCercare;
	}


	public void setIdVoloDaCercare(int idVoloDaCercare) {
		this.idVoloDaCercare = idVoloDaCercare;
	}


	public VoloDTO getVolo() {
		return volo;
	}


	public void setVolo(VoloDTO volo) {
		this.volo = volo;
	}
	
	
	public int getIdHotelDaCercare() {
		return idHotelDaCercare;
	}


	public void setIdHotelDaCercare(int idHotelDaCercare) {
		this.idHotelDaCercare = idHotelDaCercare;
	}


	public PernottamentoDTO getPernottamento() {
		return pernottamento;
	}


	public void setPernottamento(PernottamentoDTO pernottamento) {
		this.pernottamento = pernottamento;
	}


	public List<PernottamentoDTO> getPernottamenti() {
		return pernottamenti;
	}


	public void setPernottamenti(List<PernottamentoDTO> pernottamenti) {
		this.pernottamenti = pernottamenti;
	}
	
	public Date getDataInizioPernottamento() {
		return dataInizioPernottamento;
	}


	public void setDataInizioPernottamento(Date dataInizioPernottamento) {
		this.dataInizioPernottamento = dataInizioPernottamento;
	}


	public Date getDataFinePernottamento() {
		return dataFinePernottamento;
	}


	public void setDataFinePernottamento(Date dataFinePernottamento) {
		this.dataFinePernottamento = dataFinePernottamento;
	}


}
