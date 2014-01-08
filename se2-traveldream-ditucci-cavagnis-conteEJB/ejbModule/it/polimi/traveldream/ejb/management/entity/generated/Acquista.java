package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the acquista database table.
 * 
 */
@Entity
@Table(name="acquista")
@NamedQuery(name="Acquista.findAll", query="SELECT a FROM Acquista a")
public class Acquista implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AcquistaPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_acquisto", nullable=false)
	private Date dataAcquisto;

	//uni-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="mail_cliente", nullable=false, insertable=false, updatable=false)
	private Cliente cliente;

	//uni-directional many-to-one association to Pacchetto
	@ManyToOne
	@JoinColumn(name="id_pacchetto", nullable=false, insertable=false, updatable=false)
	private Pacchetto pacchetto;

	public Acquista() {
	}

	public AcquistaPK getId() {
		return this.id;
	}

	public void setId(AcquistaPK id) {
		this.id = id;
	}

	public Date getDataAcquisto() {
		return this.dataAcquisto;
	}

	public void setDataAcquisto(Date dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pacchetto getPacchetto() {
		return this.pacchetto;
	}

	public void setPacchetto(Pacchetto pacchetto) {
		this.pacchetto = pacchetto;
	}

}