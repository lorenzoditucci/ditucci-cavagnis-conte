package it.polimi.traveldream.ejb.management.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class GiftListDTO {
	@NotEmpty
	private int idGiftList;

	public int getIdGiftList() {
		return idGiftList;
	}

	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
	}
}
