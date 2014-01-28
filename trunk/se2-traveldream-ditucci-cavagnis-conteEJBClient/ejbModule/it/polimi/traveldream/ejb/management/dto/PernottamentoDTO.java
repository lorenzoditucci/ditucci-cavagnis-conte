package it.polimi.traveldream.ejb.management.dto;

import java.sql.Timestamp;
import java.util.Comparator;

import com.sun.istack.internal.NotNull;

public class PernottamentoDTO {
	
	
	private int idPernottametto;
	
	@NotNull
	private Timestamp dataInizio;

	@NotNull
	private Timestamp dataFine;
	
	@NotNull
	private HotelDTO hotel;
	
	@NotNull
	private PacchettoDTO pacchetto;

	public int getIdPernottametto() {
		return idPernottametto;
	}

	public void setIdPernottametto(int idPernottametto) {
		this.idPernottametto = idPernottametto;
	}

	public Timestamp getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Timestamp getDataFine() {
		return dataFine;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public PacchettoDTO getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(PacchettoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}
	
	@Override
	public boolean equals(Object altro){
		if(altro == null || altro.getClass() != this.getClass()){
			return false;
		}
		
		PernottamentoDTO nuovoDto = (PernottamentoDTO) altro;
		if(nuovoDto.getIdPernottametto() == this.getIdPernottametto()){
			return true;
		}
		
		return false;
		
	}
	
	
	
	public static Comparator <PernottamentoDTO>
	ordinaPerDataInizio = new Comparator <PernottamentoDTO>()
	{
		public int compare(PernottamentoDTO p1, PernottamentoDTO p2)
		{
			if(p1.getDataInizio().before(p2.getDataInizio()))
				return -1;
			if(p1.getDataInizio().after(p2.getDataInizio()))
				return 1;
			return 0;
		}
	};
	

}
