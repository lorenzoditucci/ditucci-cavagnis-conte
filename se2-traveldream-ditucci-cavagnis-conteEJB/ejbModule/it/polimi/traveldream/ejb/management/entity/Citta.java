package it.polimi.traveldream.ejb.management.entity;

import it.polimi.traveldream.ejb.management.dto.CittaDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the Citta database table.
 * 
 */
@Entity
@Table(name="Citta")

@NamedQueries({
	@NamedQuery(name="Citta.findAll", query="SELECT c FROM Citta c"),
	@NamedQuery(name="Citta.cercaCittaNome", query="SELECT c FROM Citta c where c.nome = :nomeCitta")	
})
public class Citta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue	
	@Column(unique=true, nullable=false)
	private int idCitta;

	@Column(nullable=false, length=45)
	private String nome;

	public Citta() {
	}

	public int getIdCitta() {
		return this.idCitta;
	}

	public void setIdCitta(int idCitta) {
		this.idCitta = idCitta;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Citta(CittaDTO citta){
		this.nome = citta.getNome();
		//this.pacchetti = Pacchetto.copiaToPacchetto(citta.getPacchetti());
	}
	/**
	 * associazione con Pacchetto - destinazione
	 */
	
		@ManyToMany(mappedBy="cittaDestinazione")
		private List<Pacchetto> pacchetti;
		
		public static List<Citta> copiaToCitta(List<CittaDTO> lista){
			List<Citta> copia = new ArrayList<Citta>();
			for(int i=0; i<lista.size(); i++){
				Citta daAggiungere = new Citta(lista.get(i));
				copia.add(daAggiungere);	
			}
			return copia;
		}
	

}