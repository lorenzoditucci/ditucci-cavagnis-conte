package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import java.util.ArrayList;

public interface pacchettoMgr {

	ArrayList<PacchettoDTO> prendiTutti();

	ArrayList<PacchettoDTO> prendiAcquistati(String userEmail);

}