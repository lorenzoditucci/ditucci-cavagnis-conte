package it.polimi.traveldream.ejb.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * eccezione lanciata in caso di incoerenza di un pacchetto o di una giftlist.
 * Contiene al suo interno una stringa di messaggi da visualizzare all'utente.
 * @author giorgioconte
 *
 */

public class CoerenzaException extends RuntimeException{

	
	private List<String> messaggi;
	
	
	/**
	 * Costruttore senza parametri
	 */
	public CoerenzaException(){
		this.messaggi = new ArrayList<String>();
	}
	
	public CoerenzaException(String messaggio){
		this.messaggi = new ArrayList<String>();
		messaggi.add(messaggio);
	}
	
	public void aggungiMessaggio(String m){
		messaggi.add(m);
	}
}
