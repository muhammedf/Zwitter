package sunofkyuss.zwitter.view.utility;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import sunofkyuss.zwitter.model.Person;

@Named
@ApplicationScoped
public class SessionMap {

	public Person getUser() {
		return (Person) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	}

}
