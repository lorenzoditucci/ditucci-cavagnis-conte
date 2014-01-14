package it.polimi.traveldream.web.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.polimi.traveldream.ejb.management.CreaPacchettoMgr;
import it.polimi.traveldream.ejb.management.VoloMgr;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
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
	
	private VoloDTO volo;

	/*Lista di voli da aggiungere*/
	private List<VoloDTO> voli;
	
	/*
	 * costruttore che inizializza i DTO
	 * */
	public CreaPacchettoBean() {
		this.pacchetto= new PacchettoDTO();
		this.voli= new ArrayList<VoloDTO>();
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
		System.out.println(idVoloDaCercare);
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
		System.out.println("TUTTO OK");
		
		//vado sul bean stateful
		if(creaPacchettoMgr.inserisciVoliInPacchettoInstanziato(getVoli())){
			//ritorna la pagina successiva
			
			return null;
		}
		
		return null;
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

}
