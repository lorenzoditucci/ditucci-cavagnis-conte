package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.PernottamentiAcquistatiDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PernottamentiAcquistati")
@NamedQuery(name="PernottamentiAcquistati.findAll", query="SELECT p FROM PernottamentiAcquistati p")
public class PernottamentiAcquistati implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(unique=true, nullable=false)
	private int idPernottamentoAcquistato;
				
	
	@Column(nullable=false)
	private int idGiftList;
	
	@Column(nullable=false)
	private int idPernottamento;
	
	@Column(nullable=false)
	private Timestamp dataAcquisto;

	@Column(nullable=false, length=45)
	private String nomeAcquirente;

	public int getIdPernottamentoAcquistato() {
		return idPernottamentoAcquistato;
	}

	public void setIdPernottamentoAcquistato(int idPernottamentoAcquistato) {
		this.idPernottamentoAcquistato = idPernottamentoAcquistato;
	}

	public int getIdGiftList() {
		return idGiftList;
	}

	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
	}

	public int getIdPernottamento() {
		return idPernottamento;
	}

	public void setIdPernottamento(int idPernottamento) {
		this.idPernottamento = idPernottamento;
	}

	public Timestamp getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(Timestamp dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public String getNomeAcquirente() {
		return nomeAcquirente;
	}

	public void setNomeAcquirente(String nomeAcquirente) {
		this.nomeAcquirente = nomeAcquirente;
	}

	public static List<PernottamentiAcquistati> copiaToPernottamentiAcquistati(List<PernottamentiAcquistatiDTO> list) {
		List<PernottamentiAcquistati> copia = new ArrayList<PernottamentiAcquistati>();
		for(int i=0; i<list.size();i++){
			PernottamentiAcquistati daAggiungere = new PernottamentiAcquistati();
			daAggiungere.setDataAcquisto(list.get(i).getDataAcquisto());
			daAggiungere.setIdGiftList(list.get(i).getIdGiftList());
			daAggiungere.setIdPernottamento(list.get(i).getIdPernottamento());
			daAggiungere.setIdPernottamentoAcquistato(list.get(i).getIdPernottamentoAcquistato());
			daAggiungere.setNomeAcquirente(list.get(i).getNomeAcquirente());
			copia.add(daAggiungere);
		}
		return copia;
	}

	public static List<PernottamentiAcquistatiDTO> copiaToPernottamentiAcquistatiDTO(List<PernottamentiAcquistati> pernottamenti) {
		List<PernottamentiAcquistatiDTO> copia = new ArrayList<PernottamentiAcquistatiDTO>();
		for(int i=0;i<pernottamenti.size();i++){
			PernottamentiAcquistatiDTO daAggiungere = new PernottamentiAcquistatiDTO();
			daAggiungere.setDataAcquisto(pernottamenti.get(i).getDataAcquisto());
			daAggiungere.setIdGiftList(pernottamenti.get(i).getIdGiftList());
			daAggiungere.setIdPernottamento(pernottamenti.get(i).getIdPernottamento());
			daAggiungere.setIdPernottamentoAcquistato(pernottamenti.get(i).getIdPernottamentoAcquistato());
			daAggiungere.setNomeAcquirente(pernottamenti.get(i).getNomeAcquirente());
			copia.add(daAggiungere);
		}
		/*for(PernottamentiAcquistati p:pernottamenti){
			PernottamentiAcquistatiDTO daAggiungere = new PernottamentiAcquistatiDTO();
			daAggiungere.setDataAcquisto(p.getDataAcquisto());
			daAggiungere.setIdGiftList(p.getIdGiftList());
			daAggiungere.setIdPernottamento(p.getIdPernottamento());
			daAggiungere.setIdPernottamentoAcquistato(p.getIdPernottamentoAcquistato());
			daAggiungere.setNomeAcquirente(p.getNomeAcquirente());
			copia.add(daAggiungere);
		}*/
		return copia;
	}


	
}
