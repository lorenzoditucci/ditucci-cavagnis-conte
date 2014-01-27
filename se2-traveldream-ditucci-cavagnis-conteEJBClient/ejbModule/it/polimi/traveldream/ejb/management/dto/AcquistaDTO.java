package it.polimi.traveldream.ejb.management.dto;



import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class AcquistaDTO {
	
	@NotEmpty
	private int idAcquista;

	@NotEmpty
	private Timestamp dataAcquisto;

	@NotEmpty
	private UserDTO user;
	
	@NotEmpty 	
	private PacchettoDTO pacchetto;


	public int getIdAcquista() {
		return idAcquista;
	}


	public void setIdAcquista(int idAcquista) {
		this.idAcquista = idAcquista;
	}


	public Timestamp getDataAcquisto() {
		return dataAcquisto;
	}


	public void setDataAcquisto(Timestamp dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}


	public UserDTO getUser() {
		return user;
	}


	public void setUser(UserDTO user) {
		this.user = user;
	}


	public PacchettoDTO getPacchetto() {
		return pacchetto;
	}


	public void setPacchetto(PacchettoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}
	
	

}
