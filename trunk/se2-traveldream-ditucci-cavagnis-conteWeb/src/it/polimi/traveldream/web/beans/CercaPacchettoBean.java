package it.polimi.traveldream.web.beans;


import it.polimi.traveldream.ejb.management.cercaPacchettoMgr;
import it.polimi.traveldream.ejb.management.dto.CittaDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.crypto.Data;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
 * Session Bean implementation class CercaPacchettoBean
 */
@ManagedBean(name="cercaPacchettoBean")
@SessionScoped
public class CercaPacchettoBean {
	
	@EJB
	private cercaPacchettoMgr mgr;
	
	private ArrayList<PacchettoDTO> risultato;
	private int idPacchetto;
	private String nomePacchetto;
	private Date dataPartenza;
	private Date dataRitorno;
	private Double costo;
	private String nomeCitta;
	
	

    /**
     * Default constructor. 
     */
    public CercaPacchettoBean() {
        // TODO Auto-generated constructor stub
    }



	public int getIdPacchetto() {
		return idPacchetto;
	}



	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}



	public ArrayList<PacchettoDTO> getRisultato() {
		return risultato;
	}



	public void setRisultato(ArrayList<PacchettoDTO> risultato) {
		this.risultato = risultato;
	}



	public String getNomePacchetto() {
		return nomePacchetto;
	}



	public void setNomePacchetto(String nomePacchetto) {
		this.nomePacchetto = nomePacchetto;
	}
	
	public String cercaPacchetti(){
		if(costo == null && nomePacchetto == null && dataPartenza==null && dataRitorno == null ){
			FacesContext.getCurrentInstance().addMessage("ricercaPacchetto:nome-pacchetto", new FacesMessage(FacesMessage.SEVERITY_ERROR,"campiVuoti", "campi vuoti - ricerca nulla!"));
			return "";
		}
		else{
			if(dataPartenza != null && dataRitorno != null && dataPartenza.getTime()>dataRitorno.getTime()){
				FacesContext.getCurrentInstance().addMessage("ricercaPacchetto:data-inizio", new FacesMessage(FacesMessage.SEVERITY_ERROR,"TPartenza>TRitorno","La data partenza non puo' essere maggiore della data di arrivo"));
				return "";
			}
		}
		//setRisultato(mgr.cercaPacchettiParam(dataPartenza,dataRitorno,costo, nomePacchetto));
		if(nomeCitta.equals("")){
			setRisultato(mgr.cercaPacchettiParam(dataPartenza,dataRitorno,costo, nomePacchetto));
		}else{
			ArrayList<PacchettoDTO> risultato = mgr.cercaPacchettiParam(dataPartenza, dataRitorno, costo, nomePacchetto);
			ArrayList<PacchettoDTO> risultatoFiltrato = new ArrayList<PacchettoDTO>();
			for(PacchettoDTO p:risultato){
				int flag=0;
				for(int i=0;i<p.getCittaDestinazione().size() && flag==0;i++){
					if(p.getCittaDestinazione().get(i).getNome().equals(getNomeCitta())){
						System.out.println("pacchetto "+p.getNome()+"citta pacchetto == "+p.getCittaDestinazione().get(i).getNome()+" && parametro =="+getNomeCitta());
						risultatoFiltrato.add(p);
						flag = 1;
					}
				}
			}
			setRisultato(risultatoFiltrato);
		}
		
		return "/user/risultatiRicercaPacchetti.xhtml";
	}







	public Date getDataPartenza() {
		return dataPartenza;
	}



	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}



	public Date getDataRitorno() {
		return dataRitorno;
	}



	public void setDataRitorno(Date dataRitorno) {
		this.dataRitorno = dataRitorno;
	}



	public Double getCosto() {
		return costo;
	}



	public void setCosto(Double costo) {
		this.costo = costo;
	}



	public String getNomeCitta() {
		return nomeCitta;
	}



	public void setNomeCitta(String nomeCitta) {
		this.nomeCitta = nomeCitta;
	}

}
