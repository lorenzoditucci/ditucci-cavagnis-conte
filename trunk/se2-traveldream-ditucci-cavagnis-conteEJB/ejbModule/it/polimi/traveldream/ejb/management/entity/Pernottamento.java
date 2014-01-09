package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Pernottamento database table.
 * 
 */
@Entity
@Table(name="Pernottamento")
@NamedQuery(name="Pernottamento.findAll", query="SELECT p FROM Pernottamento p")
public class Pernottamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PernottamentoPK id;

	@Column(nullable=false)
	private Timestamp dataInizio;

	@Column(nullable=false)
	private int numeroNotti;

	public Pernottamento() {
	}

	public PernottamentoPK getId() {
		return this.id;
	}

	public void setId(PernottamentoPK id) {
		this.id = id;
	}

	public Timestamp getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public int getNumeroNotti() {
		return this.numeroNotti;
	}

	public void setNumeroNotti(int numeroNotti) {
		this.numeroNotti = numeroNotti;
	}

}