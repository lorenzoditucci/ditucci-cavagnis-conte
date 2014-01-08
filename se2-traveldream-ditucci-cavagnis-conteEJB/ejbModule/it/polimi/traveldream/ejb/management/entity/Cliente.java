package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Cliente database table.
 * 
 */
@Entity
@Table(name="Cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="`mail-cliente`", unique=true, nullable=false, length=45)
	private String mail_cliente;

	@Column(nullable=false, length=45)
	private String cognome;

	@Column(nullable=false, length=45)
	private String indirizzo;

	@Column(nullable=false, length=45)
	private String nome;

	@Column(nullable=false, length=45)
	private String password;

	//bi-directional many-to-one association to GiftList
	@OneToMany(mappedBy="cliente")
	private List<GiftList> giftLists;

	public Cliente() {
	}

	public String getMail_cliente() {
		return this.mail_cliente;
	}

	public void setMail_cliente(String mail_cliente) {
		this.mail_cliente = mail_cliente;
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

	public List<GiftList> getGiftLists() {
		return this.giftLists;
	}

	public void setGiftLists(List<GiftList> giftLists) {
		this.giftLists = giftLists;
	}

	public GiftList addGiftList(GiftList giftList) {
		getGiftLists().add(giftList);
		giftList.setCliente(this);

		return giftList;
	}

	public GiftList removeGiftList(GiftList giftList) {
		getGiftLists().remove(giftList);
		giftList.setCliente(null);

		return giftList;
	}

}