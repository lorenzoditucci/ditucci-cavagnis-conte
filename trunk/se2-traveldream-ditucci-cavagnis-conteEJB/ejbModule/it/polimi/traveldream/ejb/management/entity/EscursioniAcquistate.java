package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.EscursioniAcquistateDTO;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the EscursioniAcquistate database table.
 * 
 */
@Entity
@Table(name="EscursioniAcquistate")
@NamedQuery(name="EscursioniAcquistate.findAll", query="SELECT e FROM EscursioniAcquistate e")
public class EscursioniAcquistate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(unique=true,nullable=false)
	private int idEscursioniAcquistate;
	
	@Column(nullable=false)
	private int idGiftList;
	
	@Column(nullable=false)
	private int idEscursione;
	
	@Column(nullable=false)
	private Timestamp dataAcquisto;

	@Column(nullable=false, length=45)
	private String nomeAcquirente;

	public EscursioniAcquistate() {
	}

	public int getIdEscursioniAcquistate() {
		return idEscursioniAcquistate;
	}

	public void setIdEscursioniAcquistate(int idEscursioniAcquistate) {
		this.idEscursioniAcquistate = idEscursioniAcquistate;
	}

	public int getIdGiftList() {
		return idGiftList;
	}

	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
	}

	public int getIdEscursione() {
		return idEscursione;
	}

	public void setIdEscursione(int idEscursione) {
		this.idEscursione = idEscursione;
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

	public static List<EscursioniAcquistate> copiaToEscursione(List<EscursioniAcquistateDTO> escursioni) {
		List<EscursioniAcquistate> copia = new ArrayList<EscursioniAcquistate>();
		for(EscursioniAcquistateDTO e:escursioni){
			EscursioniAcquistate daAggiungere = new EscursioniAcquistate();
			daAggiungere.setDataAcquisto(e.getDataAcquisto());
			daAggiungere.setIdEscursione(e.getIdEscursione());
			daAggiungere.setIdEscursioniAcquistate(e.getIdEscursioniAcquistate());
			daAggiungere.setIdGiftList(e.getIdGiftList());
			daAggiungere.setNomeAcquirente(e.getNomeAcquirente());
			
			copia.add(daAggiungere);
		}
		return copia;
	}

	public static List<EscursioniAcquistateDTO> copiaListaToDTO(List<EscursioniAcquistate> escursioni) {
		List<EscursioniAcquistateDTO> copia = new ArrayList<EscursioniAcquistateDTO>();
		for(EscursioniAcquistate e: escursioni){
			EscursioniAcquistateDTO daAggiungere = new EscursioniAcquistateDTO();
			daAggiungere.setDataAcquisto(e.getDataAcquisto());
			daAggiungere.setIdEscursione(e.getIdEscursione());
			daAggiungere.setIdEscursioniAcquistate(e.getIdEscursioniAcquistate());
			daAggiungere.setIdGiftList(e.getIdGiftList());
			daAggiungere.setNomeAcquirente(e.getNomeAcquirente());
			
			copia.add(daAggiungere);
		}
		return copia;
	}

}