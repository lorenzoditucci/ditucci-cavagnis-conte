package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dipendente database table.
 * 
 */
@Entity
@Table(name="dipendente")
@NamedQuery(name="Dipendente.findAll", query="SELECT d FROM Dipendente d")
public class Dipendente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mail_dipendente", unique=true, nullable=false, length=45)
	private String mailDipendente;

	@Column(nullable=false, length=45)
	private String cognome;

	@Column(nullable=false, length=45)
	private String indirizzo;

	@Column(nullable=false, length=10)
	private String matricola;

	@Column(nullable=false, length=45)
	private String nome;

	@Column(nullable=false, length=45)
	private String password;

	public Dipendente() {
	}

	public String getMailDipendente() {
		return this.mailDipendente;
	}

	public void setMailDipendente(String mailDipendente) {
		this.mailDipendente = mailDipendente;
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

	public String getMatricola() {
		return this.matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
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