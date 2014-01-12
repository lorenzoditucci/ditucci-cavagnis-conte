package it.polimi.traveldream.ejb.management.dto;




import java.util.List;

import com.sun.istack.internal.NotNull;

public class CittaDTO {
	
	@NotNull
	private int idCitta;

	@NotNull
	private String nome;
	
	
	public List<PacchettoDTO> getPacchetti() {
		return pacchetti;
	}

	public void setPacchetti(List<PacchettoDTO> pacchetti) {
		this.pacchetti = pacchetti;
	}

	private List<PacchettoDTO> pacchetti;

	public int getIdCitta() {
		return idCitta;
	}

	public void setIdCitta(int idCitta) {
		this.idCitta = idCitta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
