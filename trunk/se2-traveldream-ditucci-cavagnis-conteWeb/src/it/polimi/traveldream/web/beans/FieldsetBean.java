package it.polimi.traveldream.web.beans;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ToggleEvent;

public class FieldsetBean {

    public void handleToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fieldset Toggled", "Visibility:" + event.getVisibility());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
                    
