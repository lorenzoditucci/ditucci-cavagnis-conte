package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Pacchetto database table.
 * 
 */
@Entity
@Table(name="Pacchetto")
@NamedQuery(name="Pacchetto.findAll", query="SELECT p FROM Pacchetto p")
public class Pacchetto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="`id-pacchetto`", unique=true, nullable=false)
	private int id_pacchetto;

	@Column(nullable=false)
	private double costo;

	@Column(name="`data-fine`", nullable=false)
	private Timestamp data_fine;

	@Column(name="`data-inizio`", nullable=false)
	private Timestamp data_inizio;

	@Column(nullable=false, length=45)
	private String descrizione;

	@Column(name="`disponibilita-attuale`", nullable=false)
	private int disponibilita_attuale;

	@Column(name="`disponibilita-max`", nullable=false)
	private int disponibilita_max;

	@Column(name="`mail-cliente`", nullable=false, length=45)
	private String mail_cliente;

	@Column(name="`mail-dipendente`", nullable=false, length=45)
	private String mail_dipendente;

	@Column(nullable=false, length=45)
	private String nome;

	//bi-directional many-to-many association to Escursione
	@ManyToMany(mappedBy="pacchettos")
	private List<Escursione> escursiones;

	public Pacchetto() {
	}

	public int getId_pacchetto() {
		return this.id_pacchetto;
	}

	public void setId_pacchetto(int id_pacchetto) {
		this.id_pacchetto = id_pacchetto;
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

	public int getDisponibilita_attuale() {
		return this.disponibilita_attuale;
	}

	public void setDisponibilita_attuale(int disponibilita_attuale) {
		this.disponibilita_attuale = disponibilita_attuale;
	}

	public int getDisponibilita_max() {
		return this.disponibilita_max;
	}

	public void setDisponibilita_max(int disponibilita_max) {
		this.disponibilita_max = disponibilita_max;
	}

	public String getMail_cliente() {
		return this.mail_cliente;
	}

	public void setMail_cliente(String mail_cliente) {
		this.mail_cliente = mail_cliente;
	}

	public String getMail_dipendente() {
		return this.mail_dipendente;
	}

	public void setMail_dipendente(String mail_dipendente) {
		this.mail_dipendente = mail_dipendente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Escursione> getEscursiones() {
		return this.escursiones;
	}

	public void setEscursiones(List<Escursione> escursiones) {
		this.escursiones = escursiones;
	}

}