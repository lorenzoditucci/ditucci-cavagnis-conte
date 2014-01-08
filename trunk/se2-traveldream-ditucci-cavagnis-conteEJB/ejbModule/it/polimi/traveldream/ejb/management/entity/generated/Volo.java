package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the volo database table.
 * 
 */
@Entity
@Table(name="volo")
@NamedQuery(name="Volo.findAll", query="SELECT v FROM Volo v")
public class Volo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_volo", unique=true, nullable=false)
	private int idVolo;

	@Column(nullable=false)
	private int acquistato;

	@Column(name="citta_arrivo", nullable=false, length=45)
	private String cittaArrivo;

	@Column(name="citta_partenza", nullable=false, length=45)
	private String cittaPartenza;

	@Column(nullable=false, length=45)
	private String compagnia;

	@Column(nullable=false)
	private double costo;

	@Temporal(TemporalType.DATE)
	@Column(name="data_fine", nullable=false)
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inizio", nullable=false)
	private Date dataInizio;

	//bi-directional many-to-many association to Pacchetto
	@ManyToMany
	@JoinTable(
		name="volipacchetto"
		, joinColumns={
			@JoinColumn(name="id_volo", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_pacchetto", nullable=false)
			}
		)
	private List<Pacchetto> pacchettos;

	public Volo() {
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

	public void setAcquistato(int acquistato) {
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

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public List<Pacchetto> getPacchettos() {
		return this.pacchettos;
	}

	public void setPacchettos(List<Pacchetto> pacchettos) {
		this.pacchettos = pacchettos;
	}

}