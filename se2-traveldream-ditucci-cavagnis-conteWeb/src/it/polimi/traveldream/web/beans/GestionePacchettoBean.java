package it.polimi.traveldream.web.beans;

import java.sql.Timestamp;
import java.util.Date;

import it.polimi.traveldream.ejb.management.CreaPacchettoMgr;
import it.polimi.traveldream.ejb.management.GestionePacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@ManagedBean(name="gestionePacchettoBean")
@SessionScoped
public class GestionePacchettoBean {
	
	@EJB
	private GestionePacchettoMgr gestionePacchettoMgr;
	
	@NotNull
	private int idPacchettoDaCercare;
	
	@NotNull
	private int idVoloDaCercare;
	
	@NotNull
	@Future
	private Date dataInizio;

	@NotNull
	@Future
	private Date dataFine;
	
	@NotNull
	private int idHotelDaCercare;
	
	@NotNull
	private int idEscursioneDaCercare;
	
	
	private PacchettoDTO pacchetto;
	
	private String avviso;
	
	public PacchettoDTO getPacchetto() {
		return pacchetto;
	}


	public void setPacchetto(PacchettoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}


	public String cercaPacchetto(){
		this.pacchetto=gestionePacchettoMgr.cercaPacchettoId(idPacchettoDaCercare);
		
		if(this.pacchetto==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Pacchetto non trovato", "Non presente"));
		    return "";
		}else{
			return "dettagliPacchetto";	
		}	
	}
	
	public String eliminaPacchetto(){
		if(gestionePacchettoMgr.eliminaPacchettoId(pacchetto)){
			
			return "index.xhtml";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Non è possibile eliminare", "Non presente"));
		    return "";
		}
		
	}
	
	
	public String cercaEAggiungiVolo(){
		VoloDTO volo=new VoloDTO();
		System.out.println("sono dentro al metodo");
		  
    	if(gestionePacchettoMgr.cercaVolo(idVoloDaCercare).isEmpty()){
    		
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Non trovato", "La ricerca non ha prodotto risultati"));
    		return null;
    	}else{
    		volo=gestionePacchettoMgr.cercaVolo(idVoloDaCercare).get(0);
    		if(giaContenuto(volo)){
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Già in lista", "La ricerca non ha prodotto risultati"));
        		return null;
    		}
    		
    		getPacchetto().getVoli().add(volo);
    	
    		return null;
    	}
	}
	
	private boolean giaContenuto(VoloDTO volo) {
		for(int i=0; i< getPacchetto().getVoli().size(); i++)
		{
			if(getPacchetto().getVoli().get(i).getIdVolo()==volo.getIdVolo())
				return true;
		}		
		return false;
	}
	
	
	public String cercaEAggiungiPernottamento(){
		PernottamentoDTO pernottamento=new PernottamentoDTO();
  
    	if(gestionePacchettoMgr.cercaHotel(idHotelDaCercare).isEmpty()){
    		
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Non trovato", "La ricerca non ha prodotto risultati"));
    		return null;
    	}else{	
    		HotelDTO hotelScelto=gestionePacchettoMgr.cercaHotel(idHotelDaCercare).get(0);
    		
    		if(hotelGiaContenuto(hotelScelto)){
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Già in lista", "La ricerca non ha prodotto risultati"));
        		return null;
    		}
    		
    		if(dataInizio.getTime()>=dataFine.getTime()){
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Intervallo date errato", "Intervallo date errato"));
        		return null;
    		}

    		//creare il pernottamento
    		pernottamento.setDataInizio(new Timestamp(this.dataInizio.getTime()));
    		pernottamento.setDataFine(new Timestamp(this.dataFine.getTime()));
    		pernottamento.setHotel(hotelScelto);
    		
    		//aggiungilo in lista
    		getPacchetto().getPernotti().add(pernottamento);
    		return null;
    	}	
		
	}


	private boolean hotelGiaContenuto(HotelDTO hotelScelto) {
		for(int i=0; i<getPacchetto().getPernotti().size(); i++){
			if(getPacchetto().getPernotti().get(i).getHotel().getIdHotel()==hotelScelto.getIdHotel())
				return true;
		}
		return false;
	}
	
	public String cercaEAggiungiEscursione(){
		EscursioneDTO escursione = new EscursioneDTO();
		  
    	if(gestionePacchettoMgr.cercaEscursione(idEscursioneDaCercare).isEmpty()){
    		
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Non trovato", "La ricerca non ha prodotto risultati"));
    		return null;
    	}else{
    		escursione=gestionePacchettoMgr.cercaEscursione(idEscursioneDaCercare).get(0);
    		if(escursioneGiaContenuta(escursione)){
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Già in lista", "La ricerca non ha prodotto risultati"));
        		return null;
    		}
    		getPacchetto().getEscursioni().add(escursione);
    		return null;
    		
    	}
	}


	private boolean escursioneGiaContenuta(EscursioneDTO escursione) {
		for(int i = 0; i<getPacchetto().getEscursioni().size(); i++){
			if(getPacchetto().getEscursioni().get(i).getIdEscursione()==escursione.getIdEscursione()){
				return true;
			}
		}
		return false;
	}


	public String inviaComunicazione(){
		System.out.println(avviso);
		return null;
	}

	public int getIdPacchettoDaCercare() {
		return idPacchettoDaCercare;
	}

	public void setIdPacchettoDaCercare(int idPacchettoDaCercare) {
		this.idPacchettoDaCercare = idPacchettoDaCercare;
	}


	public String getAvviso() {
		return avviso;
	}


	public void setAvviso(String avviso) {
		this.avviso = avviso;
	}


	public int getIdVoloDaCercare() {
		return idVoloDaCercare;
	}


	public void setIdVoloDaCercare(int idVoloDaCercare) {
		this.idVoloDaCercare = idVoloDaCercare;
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


	public int getIdHotelDaCercare() {
		return idHotelDaCercare;
	}


	public void setIdHotelDaCercare(int idHotelDaCercare) {
		this.idHotelDaCercare = idHotelDaCercare;
	}


	public int getIdEscursioneDaCercare() {
		return idEscursioneDaCercare;
	}


	public void setIdEscursioneDaCercare(int idEscursioneDaCercare) {
		this.idEscursioneDaCercare = idEscursioneDaCercare;
	}

}
