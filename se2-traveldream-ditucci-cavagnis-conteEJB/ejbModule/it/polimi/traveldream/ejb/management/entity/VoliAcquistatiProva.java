package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.VoliAcquistatiProvaDTO;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the VoliAcquistati database table.
 * 
 */
@Entity
@Table(name="VoliAcquistati")
@NamedQuery(name="VoliAcquistatiProva.findAll", query="SELECT v FROM VoliAcquistatiProva v")

public class VoliAcquistatiProva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(unique=true, nullable=false)
	private int idVoloAcquistato;
	
	@Column(nullable=false)
	private int idGiftList;
	
	@Column(nullable=false)
	private int idVolo;
	
	@Column(nullable=false)
	private Timestamp dataAcquisto;

	@Column(nullable=false, length=45)
	private String nomeAcquirente;

	public VoliAcquistatiProva() {
	}


	public Timestamp getDataAcquisto() {
		return this.dataAcquisto;
	}

	public void setDataAcquisto(Timestamp dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public String getNomeAcquirente() {
		return this.nomeAcquirente;
	}

	public void setNomeAcquirente(String nomeAcquirente) {
		this.nomeAcquirente = nomeAcquirente;
	}


	public int getIdGiftList() {
		return idGiftList;
	}


	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
	}


	public int getIdVolo() {
		return idVolo;
	}


	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}


	public int getIdVoloAcquistato() {
		return idVoloAcquistato;
	}


	public void setIdVoloAcquistato(int idVoloAcquistato) {
		this.idVoloAcquistato = idVoloAcquistato;
	}


	public static List<VoliAcquistatiProva> copiaToVoloAcquistatoProva(List<VoliAcquistatiProvaDTO> voli) {
		List<VoliAcquistatiProva> copia = new ArrayList<VoliAcquistatiProva>();
		for(int i=0; i< voli.size();i++){
			VoliAcquistatiProva daAggiungere = new VoliAcquistatiProva();
			daAggiungere.setDataAcquisto(voli.get(i).getDataAcquisto());
			daAggiungere.setIdGiftList(voli.get(i).getIdGiftList());
			daAggiungere.setIdVolo(voli.get(i).getIdVolo());
			daAggiungere.setIdVoloAcquistato(voli.get(i).getIdVoloAcquistato());
			daAggiungere.setNomeAcquirente(voli.get(i).getNomeAcquirente());
			
			copia.add(daAggiungere);
		}
		return copia;
	}

}