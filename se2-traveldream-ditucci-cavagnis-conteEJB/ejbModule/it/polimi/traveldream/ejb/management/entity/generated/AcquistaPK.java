package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the acquista database table.
 * 
 */
@Embeddable
public class AcquistaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="mail_cliente", insertable=false, updatable=false, unique=true, nullable=false, length=45)
	private String mailCliente;

	@Column(name="id_pacchetto", insertable=false, updatable=false, unique=true, nullable=false)
	private int idPacchetto;

	public AcquistaPK() {
	}
	public String getMailCliente() {
		return this.mailCliente;
	}
	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}
	public int getIdPacchetto() {
		return this.idPacchetto;
	}
	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AcquistaPK)) {
			return false;
		}
		AcquistaPK castOther = (AcquistaPK)other;
		return 
			this.mailCliente.equals(castOther.mailCliente)
			&& (this.idPacchetto == castOther.idPacchetto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mailCliente.hashCode();
		hash = hash * prime + this.idPacchetto;
		
		return hash;
	}
}