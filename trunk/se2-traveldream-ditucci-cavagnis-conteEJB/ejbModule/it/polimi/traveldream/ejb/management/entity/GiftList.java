package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the GiftList database table.
 * 
 */
@Entity
@Table(name="GiftList")
@NamedQuery(name="GiftList.findAll", query="SELECT g FROM GiftList g")
public class GiftList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="`id-giftList`", unique=true, nullable=false)
	private int id_giftList;

	@Column(name="`data-creazione`", nullable=false)
	private Timestamp data_creazione;

	@Column(nullable=false, length=45)
	private String nome;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="mail-cliente",
				referencedColumnName="mail-cliente"
		)
	private Cliente cliente;

	public GiftList() {
	}

	public int getId_giftList() {
		return this.id_giftList;
	}

	public void setId_giftList(int id_giftList) {
		this.id_giftList = id_giftList;
	}

	public Timestamp getData_creazione() {
		return this.data_creazione;
	}

	public void setData_creazione(Timestamp data_creazione) {
		this.data_creazione = data_creazione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}