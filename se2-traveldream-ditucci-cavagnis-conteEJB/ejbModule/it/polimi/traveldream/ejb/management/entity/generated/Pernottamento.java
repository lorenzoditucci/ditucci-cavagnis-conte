package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pernottamento database table.
 * 
 */
@Entity
@Table(name="pernottamento")
@NamedQuery(name="Pernottamento.findAll", query="SELECT p FROM Pernottamento p")
public class Pernottamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PernottamentoPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inizio", nullable=false)
	private Date dataInizio;

	@Column(name="numero_notti", nullable=false)
	private int numeroNotti;

	//uni-directional many-to-one association to Hotel
	@ManyToOne
	@JoinColumn(name="id_hotel", nullable=false, insertable=false, updatable=false)
	private Hotel hotel;

	//bi-directional many-to-one association to Pacchetto
	@ManyToOne
	@JoinColumn(name="id_pacchetto", nullable=false, insertable=false, updatable=false)
	private Pacchetto pacchetto;

	public Pernottamento() {
	}

	public PernottamentoPK getId() {
		return this.id;
	}

	public void setId(PernottamentoPK id) {
		this.id = id;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public int getNumeroNotti() {
		return this.numeroNotti;
	}

	public void setNumeroNotti(int numeroNotti) {
		this.numeroNotti = numeroNotti;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Pacchetto getPacchetto() {
		return this.pacchetto;
	}

	public void setPacchetto(Pacchetto pacchetto) {
		this.pacchetto = pacchetto;
	}

}