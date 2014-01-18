package it.polimi.traveldream.ejb.management.mgrbean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.polimi.traveldream.ejb.exception.CoerenzaException;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ControlloCoerenzaMGRBean.
 * In questa classe è implementata tutta la logica per il controllo della
 * coerenza dei pacchetti.
 */
@Stateless
@LocalBean
public class ControlloCoerenzaMGRBean {

    /**
     * Default constructor. 
     */
    public ControlloCoerenzaMGRBean() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Questo metodo controlla la coerenza di un qualsiasi pacchettoDTO. 
     * @param p Pacchetto da controllare
     * @throws CoerenzaException. Quando il pacchetto non è coerente.
     */
    
    public void controlloPacchetto(PacchettoDTO p) throws CoerenzaException {
    	/*
    	 * variabili di supporto per il controllo della coerenza del pacchetto
    	 */
    	List <VoloDTO> voli = new ArrayList<VoloDTO>();
    	
    	if(p == null)
    		throw new NullPointerException();
    	voli = p.getVoli();
    	
    	Collections.sort(voli, VoloDTO.ordinaPerDataPartenza);
    	controllaVoli(voli);
    	
    	
    	return;
    }
    
    
    private void checkHotels(List<VoloDTO> voli, List<HotelDTO> hotels) {
    	//controllo che il numero di voli e di hotel sia giusto
    	VoloDTO voloArrivo;
    	VoloDTO voloPartenza;
    	boolean trovatoArrivo;
    	boolean trovatoPartenza;
    	
    	
    	if (voli.size() != hotels.size() + 1)
    		//TODO: solleva eccezione "La cardinalità dei voli e degli hotel non è coerente."
    		;
    	
    	/*
    	 * per ogni hotel devo trovare un volo che arriva e uno che parte dalla città in cui si trova 
    	 * l'hotel e devo verificare che la prenotazione sia compresa tra la data di arrivo e di partenza  
    	 *
    	 */
    	for(int i = 0; i<hotels.size(); i++ ){
    		trovatoArrivo = false;
    		trovatoPartenza = false;
    		for (int j=0; j<voli.size() && (!trovatoArrivo || !trovatoPartenza); j++){
    			if(voli.get(j).getCittaArrivo().equals(hotels.get(i).getCitta())){
    					voloArrivo = voli.get(j);
    					trovatoArrivo = true;
    			}
    			if(voli.get(j).getCittaPartenza().equals(hotels.get(i).getCitta())){
    				trovatoPartenza = true;
    				voloPartenza = voli.get(j);
    			}	
    		}
    	//TODO controlla che il pernottamento sia dentro le date dei due voli	
    	if (!trovatoArrivo || !trovatoPartenza){
    		//TODO l'hotel non è raggiungibile
    		;
    	}
    	
    	}
    	return;
    }
    
    /*
	 * controllo che per ogni escursione ci sia un hotel nella stessa città e che la data dell'escursione 
	 * sia compresa nel periodo in cui ci si trattiene in quella città
	 */
    private void controllaEscursioni(List<EscursioneDTO> e,List<HotelDTO> h){
    	boolean trovato;
    	/*
    	 * itero sulle escursioni
    	 */
    		for(int i=0; i<e.size();i++){
    			trovato=false;
    			// itero sugli alberghi
    			for(int j=0; j < h.size() && !trovato; j++){
    				if(e.get(i).getCitta().equals(h.get(j).getCitta()))
    				//TODO: controllo che l'escursione sia dentro al pernottamento 
    					trovato = true;
    			}
    		if(!trovato)
    			//TODO solleva eccezione perchè l'escursione non è in una città raggiunta.
    			;
    		}
    		
    			
    	return;
    }
    
    private void controllaVoli(List<VoloDTO> voli) throws CoerenzaException{
    	if (voli.size()<2)
    		throw new CoerenzaException("Il pacchetto deve contenere almeno due voli");
    	
    	//Controllo che non ci siano voli sovrapposti
    	
    	for (int i=0; i < voli.size() - 1 ;i++){
    		if(voli.get(i).getDataArrivo().after(voli.get(i+1).getDataPartenza()))
    			throw new CoerenzaException("Ci sono due voli sovrapposti.");
    	}
    	
    	//controllo che ultimo volo arrivi nella città di partenza del primo
    	if(!voli.get(0).getCittaPartenza().equals(voli.get(voli.size()-1).getCittaArrivo()))
    		throw new CoerenzaException("Il primo e l'ultimo volo non sono consistenti");
    	
    	//controllo geografico dei voli
    	for(int i=0;i<voli.size() - 1; i++){
    		if(! voli.get(i).getCittaArrivo().equals(voli.get(i+1).getCittaPartenza()))
    			throw new CoerenzaException("Il volo "+ voli.get(i).getIdVolo() + " arriva a " + voli.get(i).getCittaArrivo()+
    					"ma il successivo parte da "+ voli.get(i+1).getCittaPartenza());	
    	}
    	return;
    }
}
