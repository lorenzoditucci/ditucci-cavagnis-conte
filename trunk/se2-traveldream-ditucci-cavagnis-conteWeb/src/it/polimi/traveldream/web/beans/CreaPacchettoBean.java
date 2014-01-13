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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@ManagedBean(name="creaPacchettoBean")
@RequestScoped
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
	
	/*Lista di voli da aggiungere*/
	private List<VoloDTO> voli;
	
	/*
	 * costruttore che inizializza i DTO
	 * */
	public CreaPacchettoBean() {
		this.pacchetto= new PacchettoDTO();
		this.voli= new ArrayList<VoloDTO>();
	}
	
	
	public String inizializzaPacchetto(){
		System.out.println(pacchetto.getNome()); //per debug
		pacchetto.setDataInizio(new Timestamp(dataInizio.getTime()*1000));
		pacchetto.setDataFine(new Timestamp(dataFine.getTime()*1000));

		if(dataInizio.after(dataFine) || dataInizio.equals(dataFine)){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Intervallo date errato", "Non ha senso!!!"));
		     return "";
		}
		
		
		creaPacchettoMgr.instanziaPacchetto(getPacchetto());
		
		return ""; //rimane sulla pagina corrente
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

}
