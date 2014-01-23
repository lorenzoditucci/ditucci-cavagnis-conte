package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the Pernottamento database table.
 * 
 */
@Entity
@Table(name="Pernottamento")
@NamedQueries({
	@NamedQuery(name="Pernottamento.findAll", query="SELECT p FROM Pernottamento p"),
	@NamedQuery(name="Pernottamento.cercaPernottamentoIdPacchetto", query="SELECT p FROM Pernottamento p WHERE p.pacchetto.idPacchetto= :idPacchetto"),
	@NamedQuery(name="Pernottamento.cercaPernottamentoIdHotel", query="SELECT p FROM Pernottamento p WHERE p.hotel.idHotel= :idHotel")
	
})

public class Pernottamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(unique=true, nullable=false)
	private int idPernottametto;
	
	@Column(nullable=false)
	private Timestamp dataInizio;

	@Column(nullable=false)
	private Timestamp dataFine;
	
	@OneToOne(optional = false)
	private Hotel hotel;
	
	@OneToOne(optional = false, cascade=CascadeType.REMOVE) 	
	private Pacchetto pacchetto;

	public Pernottamento() {
	}
	
	/**
	 * Costruttore che, preso ingresso un pernottamentoDTO, crea un pernottamento uguale.
	 */
	
	public Pernottamento(PernottamentoDTO pernottamento) {
		this.idPernottametto = pernottamento.getIdPernottametto();
		this.dataInizio=pernottamento.getDataInizio();
		this.dataFine=pernottamento.getDataFine();
		if(this.hotel != null){
			this.hotel = new Hotel(pernottamento.getHotel());
		}
		if(this.pacchetto != null){
			this.pacchetto = new Pacchetto(pernottamento.getPacchetto());
		}
	}

	public int getIdPernottametto() {
		return idPernottametto;
	}
	
	public void setIdPernottametto(int idPernottametto) {
		this.idPernottametto = idPernottametto;
	}
	
	public Timestamp getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Timestamp getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Timestamp numeroNotti) {
		this.dataFine = numeroNotti;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Pacchetto getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(Pacchetto pacchetto) {
		this.pacchetto = pacchetto;
	}

	public static List<PernottamentoDTO> copiaToPernottamentoDTO(
			List<Pernottamento> lista) {
			ArrayList<PernottamentoDTO> copia = new ArrayList<PernottamentoDTO>();
			for(int i=0; i<lista.size(); i++){
				PernottamentoDTO daAggiungere=new PernottamentoDTO();
				daAggiungere.setDataInizio(lista.get(i).getDataInizio());
				daAggiungere.setDataFine(lista.get(i).getDataFine());
				daAggiungere.setIdPernottametto(lista.get(i).getIdPernottametto());
				
				HotelDTO hotel = new HotelDTO();
				hotel.setIdHotel(lista.get(i).getHotel().getIdHotel());
				hotel.setNome(lista.get(i).getHotel().getNome());
				hotel.setDescrizione(lista.get(i).getHotel().getDescrizione());
				hotel.setCitta(lista.get(i).getHotel().getCitta());
				hotel.setIndirizzo(lista.get(i).getHotel().getIndirizzo());
				hotel.setClasse(lista.get(i).getHotel().getClasse());
				hotel.setCosto(lista.get(i).getHotel().getCosto());
				hotel.setAcquistato(lista.get(i).getHotel().getAcquistato());
				
				daAggiungere.setHotel(hotel);
				copia.add(daAggiungere);	
			}
			return copia;
		
	}
	
	public static List<Pernottamento> copiaToPernottamento(List<PernottamentoDTO> lista){
		List<Pernottamento> copia = new ArrayList<Pernottamento>();
		
		for(int i = 0; i<lista.size(); i++){
			Pernottamento  daAggiungere = new Pernottamento(lista.get(i));
			
			copia.add(daAggiungere);
		}
		
		return copia; 
	}
	
	public List<PernottamentiAcquistati> getGiftLists() {
		return giftLists;
	}

	public void setGiftLists(List<PernottamentiAcquistati> giftLists) {
		this.giftLists = giftLists;
	}

	/**
	 * collegamento al ponte pernottamentiAcquistati
	 */
	@OneToMany(cascade=CascadeType.REMOVE)
	private List<PernottamentiAcquistati> giftLists;
	
	

}