package it.polimi.traveldream.ejb.management.dto;


import com.sun.istack.internal.NotNull;

public class CittaDTO {
	
	@NotNull
	private int idCitta;

	@NotNull
	private String nome;

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
