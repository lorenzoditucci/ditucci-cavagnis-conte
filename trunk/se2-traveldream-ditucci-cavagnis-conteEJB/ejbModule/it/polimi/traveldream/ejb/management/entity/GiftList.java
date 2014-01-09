package it.polimi.traveldream.ejb.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


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
	@Column(unique=true, nullable=false)
	private int idGiftList;

	@Column(nullable=false, length=45)
	private String mailCliente;

	@Column(nullable=false, length=45)
	private String nome;

	//bi-directional many-to-many association to Hotel
	@ManyToMany
	@JoinTable(
		name="HotelAcquistati"
		, joinColumns={
			@JoinColumn(name="idGiftList", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="idHotel", nullable=false)
			}
		)
	private List<Hotel> hotels;
	
	//bi-directional many-to-many association to Volo
		@ManyToMany
		@JoinTable(
			name="VoliAcquistati"
			, joinColumns={
				@JoinColumn(name="idGiftList", nullable=false)
				}
			, inverseJoinColumns={
				@JoinColumn(name="idVolo", nullable=false)
				}
			)
		private List<Volo> voli;

	public GiftList() {
	}

	public int getIdGiftList() {
		return this.idGiftList;
	}

	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
	}

	public String getMailCliente() {
		return this.mailCliente;
	}

	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Hotel> getHotels() {
		return this.hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

}