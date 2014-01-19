package it.polimi.traveldream.ejb.management;

import java.util.ArrayList;

import javax.ejb.Local;

import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;



@Local
public interface GiftListMgr {
	
	
	public void save(GiftListDTO giftList);
	
	public ArrayList<GiftListDTO> cercaGLperMail(String userEmail);

	void aggiungiPacchetto(GiftListDTO glDTO, PacchettoDTO pDTO); 
	
	

}
