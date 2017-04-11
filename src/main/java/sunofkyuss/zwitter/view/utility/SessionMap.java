package sunofkyuss.zwitter.view.utility;

import javax.faces.context.FacesContext;

import sunofkyuss.zwitter.model.Person;

public final class SessionMap {

	private SessionMap(){}
	
	public static Person getUser() {
		return (Person) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	}

}
