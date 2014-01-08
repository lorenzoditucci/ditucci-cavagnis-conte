package it.polimi.traveldream.ejb.management.entity.generated;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the giftlist database table.
 * 
 */
@Entity
@Table(name="giftlist")
@NamedQuery(name="Giftlist.findAll", query="SELECT g FROM Giftlist g")
public class Giftlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_giftlist", unique=true, nullable=false)
	private int idGiftlist;

	@Temporal(TemporalType.DATE)
	@Column(name="data_creazione", nullable=false)
	private Date dataCreazione;

	@Column(nullable=false, length=45)
	private String nome;

	//uni-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="mail_cliente", nullable=false)
	private Cliente cliente;

	//uni-directional many-to-many association to Pacchetto
	@ManyToMany
	@JoinTable(
		name="contiene"
		, joinColumns={
			@JoinColumn(name="id_giftlist", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_pacchetto", nullable=false)
			}
		)
	private List<Pacchetto> pacchettos;

	public Giftlist() {
	}

	public int getIdGiftlist() {
		return this.idGiftlist;
	}

	public void setIdGiftlist(int idGiftlist) {
		this.idGiftlist = idGiftlist;
	}

	public Date getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
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

	public List<Pacchetto> getPacchettos() {
		return this.pacchettos;
	}

	public void setPacchettos(List<Pacchetto> pacchettos) {
		this.pacchettos = pacchettos;
	}

}