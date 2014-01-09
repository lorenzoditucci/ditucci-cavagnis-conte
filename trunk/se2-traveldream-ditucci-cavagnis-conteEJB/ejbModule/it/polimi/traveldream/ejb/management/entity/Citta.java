package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Citta database table.
 * 
 */
@Entity
@Table(name="Citta")
@NamedQuery(name="Citta.findAll", query="SELECT c FROM Citta c")
public class Citta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idCitta;

	@Column(nullable=false, length=45)
	private String nome;

	public Citta() {
	}

	public int getIdCitta() {
		return this.idCitta;
	}

	public void setIdCitta(int idCitta) {
		this.idCitta = idCitta;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}