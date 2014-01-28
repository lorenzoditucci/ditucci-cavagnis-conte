package it.polimi.traveldream.ejb.management.dto;

/**
 * done
 */


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;









import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.NotEmpty;

public class EscursioneDTO {
	
	private int idEscursione;

	@NotNull
	private int acquistato;

	@NotEmpty
	private String citta;

	@NotNull
	@Min(1)
	private double costo;

	@NotNull
	@Future
	private Date dataFine;

	@NotNull
	@Future
	private Date dataInizio;

	@NotEmpty
	private String descrizione;

	@NotEmpty
	private String nome;

	public int getAcquistato() {
		return acquistato;
	}

	public void setAcquistato(int acquistato) {
		this.acquistato = acquistato;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdEscursione() {
		return idEscursione;
	}

	public void setIdEscursione(int idEscursione) {
		this.idEscursione = idEscursione;
	}
	
	@Override
	public boolean equals(Object altro){
		if(altro == null || altro.getClass() != this.getClass()){
			return false;
		}
		
		EscursioneDTO altraEscursioneDTO = (EscursioneDTO)altro;
		if(this.idEscursione == altraEscursioneDTO.getIdEscursione()){
			return true;
		}
		return false;
		
	}
	

	
	
}
