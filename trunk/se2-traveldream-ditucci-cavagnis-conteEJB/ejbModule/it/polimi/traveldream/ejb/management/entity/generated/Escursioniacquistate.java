package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the escursioniacquistate database table.
 * 
 */
@Entity
@Table(name="escursioniacquistate")
@NamedQuery(name="Escursioniacquistate.findAll", query="SELECT e FROM Escursioniacquistate e")
public class Escursioniacquistate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EscursioniacquistatePK id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_acquisto", nullable=false)
	private Date dataAcquisto;

	@Column(name="nome_acquirente", nullable=false, length=45)
	private String nomeAcquirente;

	//uni-directional many-to-one association to Escursione
	@ManyToOne
	@JoinColumn(name="id_escursione", nullable=false, insertable=false, updatable=false)
	private Escursione escursione;

	//uni-directional many-to-one association to Giftlist
	@ManyToOne
	@JoinColumn(name="id_giftlist", nullable=false, insertable=false, updatable=false)
	private Giftlist giftlist;

	public Escursioniacquistate() {
	}

	public EscursioniacquistatePK getId() {
		return this.id;
	}

	public void setId(EscursioniacquistatePK id) {
		this.id = id;
	}

	public Date getDataAcquisto() {
		return this.dataAcquisto;
	}

	public void setDataAcquisto(Date dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public String getNomeAcquirente() {
		return this.nomeAcquirente;
	}

	public void setNomeAcquirente(String nomeAcquirente) {
		this.nomeAcquirente = nomeAcquirente;
	}

	public Escursione getEscursione() {
		return this.escursione;
	}

	public void setEscursione(Escursione escursione) {
		this.escursione = escursione;
	}

	public Giftlist getGiftlist() {
		return this.giftlist;
	}

	public void setGiftlist(Giftlist giftlist) {
		this.giftlist = giftlist;
	}

}