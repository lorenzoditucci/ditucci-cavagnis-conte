package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.AcquistaDTO;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the Acquista database table.
 * 
 */
@Entity
@Table(name="Acquisti")
@NamedQueries({
	@NamedQuery(name="Acquista.findAll", query="SELECT a FROM Acquista a"),
	//@NamedQuery(name="Acquistati.cercaAcquirentiPacchetto", query="SELECT a FROM Acquista a WHERE a.id.idPacchetto = :idPacchetto")
	@NamedQuery(name = "Acquista.cercaPerUtente", query = "SELECT a FROM Acquista a WHERE a.user = :user ORDER BY a.dataAcquisto"),
})
public class Acquista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(unique=true,nullable=false)
	private int idAcquista;

	@Column(length=45)
	private Timestamp dataAcquisto;

	public Acquista() {
	}

	@OneToOne(optional = false)
	private User user;
	
	@OneToOne(optional = false) 	
	private Pacchetto pacchetto;

	public Timestamp getDataAcquisto() {
		return this.dataAcquisto;
	}

	public void setDataAcquisto(Timestamp dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public int getIdAcquista() {
		return idAcquista;
	}

	public void setIdAcquista(int idAcquista) {
		this.idAcquista = idAcquista;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User u) {
		this.user = u;
	}

	public Pacchetto getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(Pacchetto pacchetto) {
		this.pacchetto = pacchetto;
	}
	
	/**
	 * Crea una Lista di AcquistaDTO data una lista di Acquista
	 * @param List
	 */
	public static List<AcquistaDTO> copiaToAcquistaDTO(List<Acquista> lista) {
			ArrayList<AcquistaDTO> copia = new ArrayList<AcquistaDTO>();
			for(int i=0; i<lista.size(); i++){
				AcquistaDTO daAggiungere=new AcquistaDTO();
				daAggiungere.setIdAcquista(lista.get(i).getIdAcquista());
				daAggiungere.setDataAcquisto(lista.get(i).getDataAcquisto());
				daAggiungere.setPacchetto(lista.get(i).getPacchetto().convertiInDTO());
				// Devo settare anche l'utente.
				copia.add(daAggiungere);	
			}
			return copia;
		
	}

}