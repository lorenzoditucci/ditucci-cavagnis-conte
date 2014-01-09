package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the EscursioniAcquistate database table.
 * 
 */
@Entity
@Table(name="EscursioniAcquistate")
@NamedQuery(name="EscursioniAcquistate.findAll", query="SELECT e FROM EscursioniAcquistate e")
public class EscursioniAcquistate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EscursioniAcquistatePK id;

	@Column(nullable=false)
	private Timestamp dataAcquisto;

	@Column(nullable=false, length=45)
	private String nomeAcquirente;

	public EscursioniAcquistate() {
	}

	public EscursioniAcquistatePK getId() {
		return this.id;
	}

	public void setId(EscursioniAcquistatePK id) {
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