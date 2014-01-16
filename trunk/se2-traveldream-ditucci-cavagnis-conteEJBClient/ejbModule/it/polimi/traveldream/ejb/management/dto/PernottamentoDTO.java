package it.polimi.traveldream.ejb.management.dto;

import java.sql.Timestamp;


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

}
