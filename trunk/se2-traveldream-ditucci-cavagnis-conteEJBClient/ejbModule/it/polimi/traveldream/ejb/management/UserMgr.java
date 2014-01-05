package it.polimi.traveldream.ejb.management;

import it.polimi.traveldream.ejb.management.dto.UserDTO;

import javax.ejb.Local;

@Local
public interface UserMgr {
	
	public void save(UserDTO user);
	
	public void update(UserDTO user);
	
	public void unregister();
	
	public UserDTO getUserDTO();

}
