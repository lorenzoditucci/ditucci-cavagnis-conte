package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mail_cliente", unique=true, nullable=false, length=45)
	private String mailCliente;

	@Column(nullable=false, length=45)
	private String cognome;

	@Column(nullable=false, length=45)
	private String indirizzo;

	@Column(nullable=false, length=45)
	private String nome;

	@Column(nullable=false, length=45)
	private String password;

	public Cliente() {
	}

	public String getMailCliente() {
		return this.mailCliente;
	}

	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}