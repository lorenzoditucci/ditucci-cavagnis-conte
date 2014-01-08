package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Escursione database table.
 * 
 */
@Entity
@Table(name="Escursione")
@NamedQuery(name="Escursione.findAll", query="SELECT e FROM Escursione e")
public class Escursione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="`id-escursione`", unique=true, nullable=false)
	private int id_escursione;

	@Column(nullable=false)
	private byte acquistato;

	@Column(nullable=false, length=45)
	private String citta;

	@Column(nullable=false)
	private double costo;

	@Column(name="`data-fine`", nullable=false)
	private Timestamp data_fine;

	@Column(name="`data-inizio`", nullable=false)
	private Timestamp data_inizio;

	@Column(nullable=false, length=45)
	private String descrizione;

	@Column(nullable=false, length=45)
	private String nome;

	//bi-directional many-to-many association to Pacchetto
	@ManyToMany
	@JoinTable(
		name="EscursioniPacchetto"
		, joinColumns={@JoinColumn(name="id-escursione",
									referencedColumnName="id-escursione")

			}
		, inverseJoinColumns={@JoinColumn(name="id-pacchetto",
											referencedColumnName="id-pacchetto")

			}
		)
	private List<Pacchetto> pacchettos;

	public Escursione() {
	}

	public int getId_escursione() {
		return this.id_escursione;
	}

	public void setId_escursione(int id_escursione) {
		this.id_escursione = id_escursione;
	}

	public byte getAcquistato() {
		return this.acquistato;
	}

	public void setAcquistato(byte acquistato) {
		this.acquistato = acquistato;
	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Timestamp getData_fine() {
		return this.data_fine;
	}

	public void setData_fine(Timestamp data_fine) {
		this.data_fine = data_fine;
	}

	public Timestamp getData_inizio() {
		return this.data_inizio;
	}

	public void setData_inizio(Timestamp data_inizio) {
		this.data_inizio = data_inizio;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pacchetto> getPacchettos() {
		return this.pacchettos;
	}

	public void setPacchettos(List<Pacchetto> pacchettos) {
		this.pacchettos = pacchettos;
	}

}