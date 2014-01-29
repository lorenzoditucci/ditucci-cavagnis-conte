package it.polimi.traveldream.ejb.management.mgrbean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.polimi.traveldream.ejb.exception.CoerenzaException;
import it.polimi.traveldream.ejb.management.ControlloCoerenzaMGR;
import it.polimi.traveldream.ejb.management.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.management.dto.GiftListDTO;
import it.polimi.traveldream.ejb.management.dto.HotelDTO;
import it.polimi.traveldream.ejb.management.dto.PacchettoDTO;
import it.polimi.traveldream.ejb.management.dto.PernottamentoDTO;
import it.polimi.traveldream.ejb.management.dto.VoloDTO;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import sun.org.mozilla.javascript.internal.ast.ThrowStatement;
import sun.tools.jar.resources.jar;

/**
 * Session Bean implementation class ControlloCoerenzaMGRBean.
 * In questa classe è implementata tutta la logica per il controllo della
 * coerenza dei pacchetti.
 */
@Stateless
@LocalBean
public class ControlloCoerenzaMGRBean implements ControlloCoerenzaMGR{

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
    
    @Override
    public void controlloPacchetto(PacchettoDTO p) throws CoerenzaException {
    	/*
    	 * variabili di supporto per il controllo della coerenza del pacchetto
    	 */
    	List <VoloDTO> voli = new ArrayList<VoloDTO>();
    	List<PernottamentoDTO> pernottamenti = new ArrayList<PernottamentoDTO>();
    	
    	if(p == null)
    		throw new NullPointerException();
    	
    	voli = p.getVoli();
    	pernottamenti = p.getPernotti();
    	
    	Collections.sort(voli, VoloDTO.ordinaPerDataPartenza);
    	Collections.sort(pernottamenti, PernottamentoDTO.ordinaPerDataInizio);
    	
    	if(voli.isEmpty() || pernottamenti.isEmpty()){
    		throw new CoerenzaException("Liste voli/pernottamenti vuote");
    	}
    	
    	// controllo che la data partenza sia lo stesso giorno della data inizio
    	
    	if(!(stessoGiornoMeseAnno(toCalendar(voli.get(0).getDataPartenza()), toCalendar(p.getDataInizio()))) &&
    			stessoGiornoMeseAnno(toCalendar(voli.get(voli.size()-1).getDataArrivo()), toCalendar(p.getDataFine())))
    	{
    		throw new CoerenzaException("La partenza del primo volo non coincide con l'inizio del pacchetto e/o l'arrivo dell'ultimo"
    				+ "volo non coincide con la fine del pacchetto");
    	}
    	
    	controllaVoli(voli);
    	controllaPernottamenti(voli, pernottamenti);
    	controllaEscursioni(p.getEscursioni(), pernottamenti);
    	
    	
    	return;
    }
    
    @Override
    public void controllaGiftList(GiftListDTO g) throws CoerenzaException{
    	
    	List<PacchettoDTO> pacchetti = new ArrayList<PacchettoDTO>();
    	
    	if(g == null)
    		throw new NullPointerException();
    	pacchetti = g.getPacchettiContenuti();
    	
    	Collections.sort(pacchetti, PacchettoDTO.ordinaPerDataInizio);
    	

    	if(pacchetti.size() > 1){
    		for (int i = 0; i < pacchetti.size() -1;i++){
    			if(pacchetti.get(i).getDataFine().after(pacchetti.get(i+1).getDataInizio())){
    				throw new CoerenzaException("Due pacchetti si sovrappongono");
    			}
    		}
    	} 	
    	return;
    }
    
    
    private void controllaPernottamenti(List<VoloDTO> voli, List<PernottamentoDTO> p) throws CoerenzaException{
    	
    	if(voli.size() != p.size() + 1 )
    		throw new CoerenzaException("Il numero di voli e il numero dei pernottamenti non sono coerenti");
    	//controllo geografico e temporale
    	for(int i = 0; i < voli.size() - 1; i++){
    			//geografico
    			if(!voli.get(i).getCittaArrivo().equals(p.get(i).getHotel().getCitta()))
    				throw new CoerenzaException("Nella città "+ voli.get(i).getCittaArrivo()+ " manca il pernottamento");
    			//temporale
    			if(! periodoContenutoEsattamente(voli.get(i).getDataArrivo(),voli.get(i+1).getDataPartenza(),p.get(i).getDataInizio(),p.get(i).getDataFine()))
    				throw new CoerenzaException("il pernottamento "+p.get(i).getIdPernottametto() + " non coincide con l'arrivo e la partenza di due voli.");	
    	}
    	
    	
    	return;
    }
    
    /*
	 * controllo che per ogni escursione ci sia un hotel nella stessa città e che la data dell'escursione 
	 * sia compresa nel periodo in cui ci si trattiene in quella città
	 */
    
    private void controllaEscursioni(List<EscursioneDTO> e,List<PernottamentoDTO> p) throws CoerenzaException{
    	boolean controllata = false;
    	
    	for(int i=0; i < e.size(); i++){
    		controllata = false;
    		for(int j = 0; j < p.size() && !controllata; j++){
    			if(e.get(i).getCitta().equals(p.get(j).getHotel().getCitta()) && 
    					periodoContenuto(p.get(j).getDataInizio(), p.get(j).getDataFine(), e.get(i).getDataInizio(), e.get(i).getDataFine()))
    				controllata = true;
    		}
    		if(!controllata)
    			throw new CoerenzaException("L'escursione "+ e.get(i).getNome() + " non è coerente con il pacchetto");
    	}
    	return;
    }
    
    private void controllaVoli(List<VoloDTO> voli) throws CoerenzaException{
    	if (voli.size()<2){
    		throw new CoerenzaException("Il pacchetto deve contenere almeno due voli");
    	}
    	
    	
    	
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
    
    private boolean periodoContenutoEsattamente(java.util.Date inizioP, java.util.Date fineP, Timestamp inizio, Timestamp fine){
    	Calendar inizioPCalendar = toCalendar(inizioP);
    	Calendar finePCalendar = toCalendar(fineP);
    	Calendar inizioCalendar = toCalendar(inizio);
    	Calendar fineCalendar = toCalendar(fine);
    	
    	if(stessoGiornoMeseAnno(inizioPCalendar,inizioCalendar) && stessoGiornoMeseAnno(finePCalendar,fineCalendar))
    		return true;
    	return false;
    }
    
    private boolean periodoContenuto(java.util.Date inizioP, java.util.Date fineP, java.util.Date inizio, java.util.Date fine){
    	
    	if(inizioP.before(inizio) && fineP.after(fine))
    		return true;
    	return false;
    }
    
   /*
    * converte un oggetto formato Date in uno formato Calendar
    */
    
    private Calendar toCalendar(java.util.Date data){
    	Calendar newDataCalendar = Calendar.getInstance();
    	newDataCalendar.setTime(data);
    	return newDataCalendar;
    }
    
    /*
     * controlla che due date Calendar siano uguali con granularitù pari al giorno
     */
    private boolean stessoGiornoMeseAnno(Calendar d1,Calendar d2){
    	if(d1.get(Calendar.DAY_OF_MONTH) == d2.get(Calendar.DAY_OF_MONTH) && d1.get(Calendar.MONTH) == d2.get(Calendar.MONTH) && d1.get(Calendar.YEAR) == d2.get(Calendar.YEAR) )
    		return true;
    	return false;
    	
    }
}
