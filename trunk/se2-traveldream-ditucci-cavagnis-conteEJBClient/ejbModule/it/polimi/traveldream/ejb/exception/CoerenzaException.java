package it.polimi.traveldream.ejb.exception;

import java.util.ArrayList;
import java.util.List;

public class CoerenzaException {

	
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
