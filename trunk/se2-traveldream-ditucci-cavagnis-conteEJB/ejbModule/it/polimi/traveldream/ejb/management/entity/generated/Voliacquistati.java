package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the voliacquistati database table.
 * 
 */
@Entity
@Table(name="voliacquistati")
@NamedQuery(name="Voliacquistati.findAll", query="SELECT v FROM Voliacquistati v")
public class Voliacquistati implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VoliacquistatiPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_acquisto", nullable=false)
	private Date dataAcquisto;

	@Column(name="nome_acquirente", nullable=false, length=45)
	private String nomeAcquirente;

	//uni-directional many-to-one association to Giftlist
	@ManyToOne
	@JoinColumn(name="id_giftlist", nullable=false, insertable=false, updatable=false)
	private Giftlist giftlist;

	//uni-directional many-to-one association to Volo
	@ManyToOne
	@JoinColumn(name="id_volo", nullable=false, insertable=false, updatable=false)
	private Volo volo;

	public Voliacquistati() {
	}

	public VoliacquistatiPK getId() {
		return this.id;
	}

	public void setId(VoliacquistatiPK id) {
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

	public Giftlist getGiftlist() {
		return this.giftlist;
	}

	public void setGiftlist(Giftlist giftlist) {
		this.giftlist = giftlist;
	}

	public Volo getVolo() {
		return this.volo;
	}

	public void setVolo(Volo volo) {
		this.volo = volo;
	}

}