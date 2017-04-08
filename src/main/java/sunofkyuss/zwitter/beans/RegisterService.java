package sunofkyuss.zwitter.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import sunofkyuss.zwitter.dao.PersonDao;
import sunofkyuss.zwitter.model.Person;

@RequestScoped
public class RegisterService {

	@Inject
	private PersonDao pd;

	public boolean register(String username, String password) {

		try {
			pd.getPersonByUserName(username);
		} catch (Exception e) {
			return false;
		}

		Person person = new Person(username, password);
		pd.create(person);

		return true;
	}
}