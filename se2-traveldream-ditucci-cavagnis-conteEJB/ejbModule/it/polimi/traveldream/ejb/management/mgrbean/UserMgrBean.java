package it.polimi.traveldream.ejb.management.mgrbean;

import it.polimi.traveldream.ejb.management.UserMgr;
import it.polimi.traveldream.ejb.management.dto.UserDTO;
import it.polimi.traveldream.ejb.management.entity.Group;
import it.polimi.traveldream.ejb.management.entity.User;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserBean
 */
//session bean che riceve i dati della registrazione
@Stateless
public class UserMgrBean implements UserMgr {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	
	//oggetto USERDTO mi arriva dalla Web
	@Override
	public void save(UserDTO user) {
		User newUser = new User(user);
		List<Group> groups = new ArrayList<Group>(); //ad ogni utente possono corrispondere pi� gruppi di appartenenza
		groups.add(Group.USER); //aggiunto USER ai gruppi dell'utente appena creato
		newUser.setGroups(groups);
		em.persist(newUser); //sulla base di dati viene registrata la persistenza
	}

	
/*
 * Alcuni metodi utili nell'implementazione della classe	
 */
/*
 * Metodo che permette ad utente e amministratore di aggiornare i propri dati
 */
	@Override
	@RolesAllowed({Group._USER,Group._EMPLOYEE}) //gruppi che possono chiamare il metodo
	public void update(UserDTO user) {
		em.merge(new User(user));
	}


	@Override
	@RolesAllowed({Group._USER,Group._EMPLOYEE})
	public UserDTO getUserDTO() {
		UserDTO userDTO = convertToDTO(getPrincipalUser()); //getPrincipal � l'utente corrente
		return userDTO;
	}

	@Override
	@RolesAllowed({Group._USER})
	public void unregister() {
		remove(getPrincipalUser());
	}


	public User find(String email) {
		//cercare nel database
		//passo classe e id sull'entity menager
    	return em.find(User.class, email);
    }
	

    //uso della namedquery
    public List<User> getAllUsers() {
    	//User.find_all nome della query
    	//ritorna il risultato della query
    	//la named query � un modo comedo di avere query cablate nel codice
    	return em.createNamedQuery(User.FIND_ALL, User.class).getResultList();
    }

    public void remove(String email) {
		User user = find(email);
        em.remove(user);
	}
    
    public void remove(User user) {
    	em.remove(user);
	}
    
    //restituisce l'utente corrente
    public User getPrincipalUser() {
    	return find(getPrincipalEmail());
    }
	
    //usato internamente
    public String getPrincipalEmail() {
    	//oggetto context ejb, chiedo al container il CallerPrincipal, ovvero l'utente loggato
    	return context.getCallerPrincipal().getName(); //identificativo dell'utente loggato
    	//ritorna l'oggetto user che chiama questi metodi
    }
    
//conversione di un User in un oggetto trasmissibile
    private UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		return userDTO;
	}
    
}

