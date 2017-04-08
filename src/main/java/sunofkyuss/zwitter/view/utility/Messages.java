package sunofkyuss.zwitter.view.utility;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {

	public static void info(String summary, String detail){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}
	
	public static void error(String summary, String detail){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
	}
}
