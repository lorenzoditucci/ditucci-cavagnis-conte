package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the VoliAcquistati database table.
 * 
 */
@Entity
@Table(name="VoliAcquistati")
@NamedQuery(name="VoliAcquistati.findAll", query="SELECT v FROM VoliAcquistati v")
public class VoliAcquistati implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VoliAcquistatiPK id;

	@Column(nullable=false)
	private Timestamp dataAcquisto;

	@Column(nullable=false, length=45)
	private String nomeAcquirente;

	public VoliAcquistati() {
	}

	public VoliAcquistatiPK getId() {
		return this.id;
	}

	public void setId(VoliAcquistatiPK id) {
		this.id = id;
	}

	public Timestamp getDataAcquisto() {
		return this.dataAcquisto;
	}

	public void setDataAcquisto(Timestamp dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public String getNomeAcquirente() {
		return this.nomeAcquirente;
	}

	public void setNomeAcquirente(String nomeAcquirente) {
		this.nomeAcquirente = nomeAcquirente;
	}

}