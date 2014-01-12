package it.polimi.traveldream.web.beans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.json.stream.JsonParser.Event;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

@ManagedBean(name="fieldsetBean")
@RequestScoped
public class FieldsetBean {

    public void handleToggle(ToggleEvent event) {
    	String messaggio;
    	event.getVisibility();
		if(event.getVisibility().equals(Visibility.VISIBLE)){
    		messaggio = new String("Visibile");
    	}else {
			messaggio = new String("Nascosto");
		}
    	
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Avviso", "Pacchetto " + messaggio);

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
                    
