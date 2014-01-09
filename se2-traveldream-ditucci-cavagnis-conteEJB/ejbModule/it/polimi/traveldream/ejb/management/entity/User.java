package it.polimi.traveldream.ejb.management.entity;


import it.polimi.traveldream.ejb.management.dto.UserDTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Entity implementation class for Entity: UserEntity
 */
@Entity
@Table(name="USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING, length = 20)
@DiscriminatorValue("user")
@NamedQueries({
	@NamedQuery(name=User.FIND_ALL,
				query="SELECT u FROM User u ORDER BY u.registeredOn ASC")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "User.findAll"; 
	
	@Id
	private String email; //chiave primaria della tabella
	
    private String firstName;
      
    private String lastName;
	
    private String address;
    
	private String password; //non metterla in chiaro!
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date registeredOn; //data registrazione
	
	//tabella di join
	@ElementCollection(targetClass = Group.class)
    @CollectionTable(name = "USERS_GROUPS",
                    joinColumns = @JoinColumn(name = "email")) 
    @Enumerated(EnumType.STRING)
    @Column(name="groupname")
    private List<Group> groups;

	public User() {
		super();
	}
	
	/*
	 * Costruttore della classe User
	 */
	public User(UserDTO user){
		//la parte web manda un nuovo utente da creare con UserDTO
        //Data Transfer Object = DTO
        this.email        = user.getEmail();
        this.firstName    = user.getFirstName();
        this.lastName     = user.getLastName();   
        this.address     = user.getAddress(); 
        this.password     = DigestUtils.md5Hex(user.getPassword()); //qui la password � messa in chiaro ma non � da fare!
        this.registeredOn = new Date(); //timestamp della data di registrazione
    }
	
	
	public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getEmail() {
        return email;
    }
  
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAddress() {
        return this.address;
    }
  
    public void setAddress(String address) {
        this.address = address;
    }
  
    /*
     * Da non ritornare in chiaro 
     */
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public Date getRegisteredOn() {
        return registeredOn;
    }
 
    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }
 
    public List<Group> getGroups() {
        return groups;
    }
 
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
 
    @Override
    public String toString() {
        return "User [email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName +", address=" + address +", password=" + password
                + ", registeredOn=" + registeredOn + ", groups=" + groups + "]";
    }
    
    /**
	 * connessione pacchetto - acquista
	 */
	@ManyToMany
	@JoinTable(
			name="Acquista"
			, joinColumns={
					@JoinColumn(name="mail", nullable=false)
			}
			, inverseJoinColumns={
					@JoinColumn(name="idPacchetto", nullable=false)
			})
	private List<Pacchetto> pacchetti;
}

