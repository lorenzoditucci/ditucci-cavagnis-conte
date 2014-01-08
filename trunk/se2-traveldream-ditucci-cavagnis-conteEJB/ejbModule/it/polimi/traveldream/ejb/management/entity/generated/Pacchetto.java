package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pacchetto database table.
 * 
 */
@Entity
@Table(name="pacchetto")
@NamedQuery(name="Pacchetto.findAll", query="SELECT p FROM Pacchetto p")
public class Pacchetto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_pacchetto", unique=true, nullable=false)
	private int idPacchetto;

	@Temporal(TemporalType.DATE)
	@Column(name="data_fine", nullable=false)
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inizio", nullable=false)
	private Date dataInizio;

	@Column(nullable=false, length=255)
	private String descrizione;

	@Column(name="disponibilita_attuale", nullable=false)
	private int disponibilitaAttuale;

	@Column(name="disponibilita_max", nullable=false)
	private int disponibilitaMax;

	@Column(nullable=false, length=45)
	private String nome;

	//uni-directional many-to-many association to Citta
	@ManyToMany
	@JoinTable(
		name="destinazione"
		, joinColumns={
			@JoinColumn(name="id_pacchetto", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_citta", nullable=false)
			}
		)
	private List<Citta> cittas;

	//uni-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="mail_cliente")
	private Cliente cliente;

	//uni-directional many-to-one association to Dipendente
	@ManyToOne
	@JoinColumn(name="mail_dipendente")
	private Dipendente dipendente;

	//bi-directional many-to-many association to Escursione
	@ManyToMany
	@JoinTable(
		name="escursionipacchetto"
		, joinColumns={
			@JoinColumn(name="id_pacchetto", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_escursione", nullable=false)
			}
		)
	private List<Escursione> escursiones;

	//bi-directional many-to-one association to Pernottamento
	@OneToMany(mappedBy="pacchetto")
	private List<Pernottamento> pernottamentos;

	//bi-directional many-to-many association to Volo
	@ManyToMany(mappedBy="pacchettos")
	private List<Volo> volos;

	public Pacchetto() {
	}

	public int getIdPacchetto() {
		return this.idPacchetto;
	}

	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
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

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getDisponibilitaAttuale() {
		return this.disponibilitaAttuale;
	}

	public void setDisponibilitaAttuale(int disponibilitaAttuale) {
		this.disponibilitaAttuale = disponibilitaAttuale;
	}

	public int getDisponibilitaMax() {
		return this.disponibilitaMax;
	}

	public void setDisponibilitaMax(int disponibilitaMax) {
		this.disponibilitaMax = disponibilitaMax;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Citta> getCittas() {
		return this.cittas;
	}

	public void setCittas(List<Citta> cittas) {
		this.cittas = cittas;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Dipendente getDipendente() {
		return this.dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public List<Escursione> getEscursiones() {
		return this.escursiones;
	}

	public void setEscursiones(List<Escursione> escursiones) {
		this.escursiones = escursiones;
	}

	public List<Pernottamento> getPernottamentos() {
		return this.pernottamentos;
	}

	public void setPernottamentos(List<Pernottamento> pernottamentos) {
		this.pernottamentos = pernottamentos;
	}

	public Pernottamento addPernottamento(Pernottamento pernottamento) {
		getPernottamentos().add(pernottamento);
		pernottamento.setPacchetto(this);

		return pernottamento;
	}

	public Pernottamento removePernottamento(Pernottamento pernottamento) {
		getPernottamentos().remove(pernottamento);
		pernottamento.setPacchetto(null);

		return pernottamento;
	}

	public List<Volo> getVolos() {
		return this.volos;
	}

	public void setVolos(List<Volo> volos) {
		this.volos = volos;
	}

}