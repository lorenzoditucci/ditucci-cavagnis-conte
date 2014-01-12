package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the Volo database table.
 * 
 */
@Entity
@Table(name="Volo")
@NamedQueries({
	@NamedQuery(name="Volo.findAll", query="SELECT v FROM Volo v"),
	@NamedQuery(name="Volo.cercaVoloId", query="SELECT v FROM Volo v WHERE v.idVolo= :idVolo")
})

public class Volo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(unique=true, nullable=false)
	private int idVolo;

	@Column(nullable=false)
	private int acquistato;

	@Column(nullable=false, length=45)
	private String cittaArrivo;

	@Column(nullable=false, length=45)
	private String cittaPartenza;

	@Column(nullable=false, length=45)
	private String compagnia;

	@Column(nullable=false)
	private double costo;

	@Column(nullable=false)
	private Timestamp dataFine;

	@Column(nullable=false)
	private Timestamp dataInizio;

	public Volo() {
	}

	public Volo(VoloDTO volo) {
		this.dataInizio=new Timestamp(volo.getDataPartenza().getTime());
		this.dataFine=new Timestamp(volo.getDataArrivo().getTime());
		this.costo=volo.getCosto();
		this.cittaArrivo=volo.getCittaArrivo();
		this.cittaPartenza= volo.getCittaPartenza();
		this.compagnia= volo.getCompagnia();
		this.acquistato=0; /*a 0 di default in creazione*/
	}
	
	public static List<Volo> copiaToVolo(List<VoloDTO> lista){
		List<Volo> copia = new ArrayList<Volo>();
		for(int i=0; i<lista.size(); i++){
			Volo daAggiungere = new Volo(lista.get(i));
			copia.add(daAggiungere);	
		}
		return copia;
	}

	public int getIdVolo() {
		return this.idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public int getAcquistato() {
		return this.acquistato;
	}

	public void setAcquistato(byte acquistato) {
		this.acquistato = acquistato;
	}

	public String getCittaArrivo() {
		return this.cittaArrivo;
	}

	public void setCittaArrivo(String cittaArrivo) {
		this.cittaArrivo = cittaArrivo;
	}

	public String getCittaPartenza() {
		return this.cittaPartenza;
	}

	public void setCittaPartenza(String cittaPartenza) {
		this.cittaPartenza = cittaPartenza;
	}

	public String getCompagnia() {
		return this.compagnia;
	}

	public void setCompagnia(String compagnia) {
		this.compagnia = compagnia;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public Timestamp getDataFine() {
		return dataFine;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}

	public Timestamp getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}



	
	/**
	 * associazione many to many con Gift List
	 */
	
		@ManyToMany(mappedBy="voli")
		private List<GiftList> giftLists;
		
	/**
	 * associazione con Pacchetto (volinpacchetto)
	 */
		@ManyToMany(mappedBy="voli")
		private List<Pacchetto> pacchetti;
}