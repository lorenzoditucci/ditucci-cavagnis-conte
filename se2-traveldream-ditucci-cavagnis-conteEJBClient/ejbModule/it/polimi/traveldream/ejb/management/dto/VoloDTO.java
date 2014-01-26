package it.polimi.traveldream.ejb.management.dto;



import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;



public class VoloDTO implements Comparable<VoloDTO> {

	private int idVolo;
	
	@NotEmpty
	private String compagnia;

	@NotEmpty
    private String cittaPartenza;
	
	@NotEmpty
    private String cittaArrivo;
	
	@NotNull
	@Future
    private Date dataPartenza;
	
	@NotNull
	@Future
    private Date dataArrivo;
	
	@NotNull
	@Min(1)
    private Double costo;
	
	private int acquistato;
    
	public String getCompagnia() {
		return compagnia;
	}

	public void setCompagnia(String compagnia) {
		this.compagnia = compagnia;
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
	public Date getDataPartenza() {
		return dataPartenza;
	}

	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	public Date getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}


	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public int getIdVolo() {
		return idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public int getAcquistato() {
		return acquistato;
	}

	public void setAcquistato(int acquistato) {
		this.acquistato = acquistato;
	}

	@Override
	public boolean equals(Object altro){
		if(altro == null || altro.getClass() != this.getClass()){
			return false;
		}
		
		VoloDTO altroVolo = (VoloDTO) altro;
		
		if(this.idVolo == altroVolo.getIdVolo()){
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(VoloDTO v) {
		
		if(this.getDataPartenza().before(v.getDataPartenza()))
			return -1;
		
		if(this.getDataPartenza().equals(v.getDataPartenza()))
			return 0;
		
		return 1;
	}
	
	/*
	 * comparatore per ordinare i voli in base alla data.
	 */
	
	public static Comparator <VoloDTO>
	ordinaPerDataPartenza = new Comparator <VoloDTO>()
	{
		public int compare(VoloDTO v1, VoloDTO v2)
		{
			if(v1.getDataPartenza().before(v2.getDataPartenza()))
				return -1;
			if(v1.getDataPartenza().after(v2.getDataPartenza()))
				return 1;
			return 0;
		}
	};

	
	
}
