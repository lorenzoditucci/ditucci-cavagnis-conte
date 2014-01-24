package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;
import org.eclipse.persistence.internal.jpa.EntityManagerFactoryDelegate;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;

import java.io.Serializable;

import javax.persistence.*;

import org.eclipse.persistence.internal.jpa.EntityManagerFactoryDelegate;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the Pacchetto database table.
 * 
 */
@Entity
@Table(name="Pacchetto")
@NamedQueries({
	@NamedQuery(name="Pacchetto.findAll", query="SELECT p FROM Pacchetto p ORDER BY p.idPacchetto DESC"),
	@NamedQuery(name="cercaDaEmail", query="SELECT p FROM Pacchetto p where p.mail = :email"),
	@NamedQuery(name="cercaPacchettiId", query="SELECT p FROM Pacchetto p where p.idPacchetto = :id"),
	@NamedQuery(name="CercaPacchettiIDEMail", query="SELECT p FROM Pacchetto p where (p.idPacchetto = :id AND p.mail= :mail)")
	
})

public class Pacchetto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue
	private int idPacchetto;

	@Column(nullable=false)
	private double costo;

	@Column(nullable=false)
	private Timestamp dataFine;

	@Column(nullable=false)
	private Timestamp dataInizio;

	@Column(nullable=false, length=45)
	private String descrizione;

	@Column(nullable=false)
	private int disponibilitaAttuale;

	@Column(nullable=false)
	private int disponibilitaMax;

	@Column(nullable=false, length=45)
	private String mail;

	@Column(nullable=false, length=45)
	private String nome;

	public Pacchetto() {
	}
	
	public Pacchetto(PacchettoDTO p){
		this.costo = p.getCosto();
		this.descrizione = p.getDescrizione();
		this.nome = p.getNome();
		this.dataInizio = new Timestamp(p.getDataInizio().getTime());
		this.dataFine = new Timestamp(p.getDataFine().getTime());
		this.disponibilitaAttuale = p.getDisponibilitaAttuale();
		this.disponibilitaMax = p.getDisponibilitaMax();
		
		
		this.cittaDestinazione = Citta.copiaToCitta(p.getCittaDestinazione());
		this.escursioni = Escursione.copiaToEscursione(p.getEscursioni());
		if(p.getGiftLists()!=null)
			this.giftLists = GiftList.copiaToGiftList(p.getGiftLists());
		this.voli = Volo.copiaToVolo(p.getVoli());
		//if(p.getUser()!=null)
			//this.users.add(User.copiaToUser(p.getUser()));
		
	}
	
	public static List<Pacchetto> copiaToPacchetto(List<PacchettoDTO> lista){
		List<Pacchetto> copia = new ArrayList<Pacchetto>();
		for(int i=0; i<lista.size(); i++){
			Pacchetto daAggiungere = new Pacchetto(lista.get(i));
			copia.add(daAggiungere);	
		}
		return copia;
	}

	
	@OneToMany(cascade=CascadeType.REMOVE)
	private List<Pernottamento> pernottiList;
			
	public List<Pernottamento> getPernottiList() {
	return pernottiList;
		}
	
	public void setPernottiList(List<Pernottamento> pernottiList) {
	this.pernottiList = pernottiList;
	}
	
	public int getIdPacchetto() {
		return this.idPacchetto;
	}

	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Timestamp getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}

	public Timestamp getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getDisponibilitaAttuale() {
		return this.disponibilitaAttuale;
	}

	public void setDisponibilitaAttuale(int disponibilitaAttuale) {
		this.disponibilitaAttuale = disponibilitaAttuale;
	}

	public int getDisponibilitaMax() {
		return this.disponibilitaMax;
	}

	public void setDisponibilitaMax(int disponibilitaMax) {
		this.disponibilitaMax = disponibilitaMax;
	}


	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	/**
	 * connessione con i voli (voliPacchetto)
	 */
	
	@ManyToMany
		@JoinTable(
				name="VoliPacchetto"
				, joinColumns={
						@JoinColumn(name="idPacchetto", nullable=false, referencedColumnName="idPacchetto")
				}
				, inverseJoinColumns={
						@JoinColumn(name="idVolo", nullable=false, referencedColumnName="idVolo")
				})
		private List<Volo> voli;
	
	public List<Volo> getVoli() {
		return voli;
	}

	public List<Escursione> getEscursioni() {
		return escursioni;
	}

	public List<Citta> getCittaDestinazione() {
		return cittaDestinazione;
	}


	/**
	 * connessione con escursioni (escursioniPacchetto)
	 */
	
	@ManyToMany
		@JoinTable(
				name="EscursioniPacchetto"
				, joinColumns={
						@JoinColumn(name="idPacchetto", nullable=false, referencedColumnName="idPacchetto")
				}
				, inverseJoinColumns={
						@JoinColumn(name="idEscursione", nullable=false, referencedColumnName="idEscursione")
				})
		private List<Escursione> escursioni;
	
	public List<GiftList> getGiftLists() {
		return giftLists;
	}

	public void setGiftLists(List<GiftList> giftLists) {
		this.giftLists = giftLists;
	}


	/**
	 * connessione giftlist - Contiene
	 */
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy= "pacchettiContenuti")
	/*@JoinTable(
			name="Contiene"
			, joinColumns={
					@JoinColumn(name="idPacchetto", nullable=false, referencedColumnName="idPacchetto")
			}
			, inverseJoinColumns={
					@JoinColumn(name="idGiftList", nullable=false, referencedColumnName="idGiftList")
			})*/
	private List<GiftList> giftLists;
	
	/**
	 * connessione citta' - destinazione
	 */
	@ManyToMany
	@JoinTable(
			name="Destinazione"
			, joinColumns={
					@JoinColumn(name="idPacchetto", nullable=false, referencedColumnName="idPacchetto")
			}
			, inverseJoinColumns={
					@JoinColumn(name="idCitta", nullable=false, referencedColumnName="idCitta")
			})
	private List<Citta> cittaDestinazione;
	
	/**
	 * associazione con User - acquista
	 */
	
	/*	@OneToMany(cascade = CascadeType.ALL)
		private List<User> users;
		*/
	

	public void setVoli(List<Volo> voli) {
		this.voli=voli;
	}

	public void setEscursioni(List<Escursione> escursioni) {
		this.escursioni=escursioni;
	}

	public void setCittaDestinazione(List<Citta> citta) {
		this.cittaDestinazione=citta;
	}

	public PacchettoDTO convertiInDTO() {
		PacchettoDTO pacchetto = new PacchettoDTO();
		pacchetto.setIdPacchetto(this.getIdPacchetto());
		pacchetto.setNome(this.getNome());
		pacchetto.setDescrizione(this.getDescrizione());
		pacchetto.setDataFine(this.getDataFine());
		pacchetto.setDataInizio(this.getDataInizio());
		pacchetto.setCosto(this.getCosto());
		pacchetto.setDisponibilitaAttuale(this.getDisponibilitaAttuale());
		pacchetto.setDisponibilitaMax(this.getDisponibilitaMax());
		pacchetto.setMail(this.getMail());
		
		//voli
		pacchetto.setVoli(Volo.copiaToVoloDTO(this.getVoli()));
		//escursioni
		pacchetto.setEscursioni(Escursione.copiaToEscursioneDTO(this.getEscursioni()));
		//citta
		pacchetto.setCittaDestinazione(Citta.copiaToCittaDTO(this.getCittaDestinazione()));
		
		
		return pacchetto;
	}
		
	
}