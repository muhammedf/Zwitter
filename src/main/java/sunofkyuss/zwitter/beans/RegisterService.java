package sunofkyuss.zwitter.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import sunofkyuss.zwitter.beans.utility.Crypto;
import sunofkyuss.zwitter.dao.PersonDao;
import sunofkyuss.zwitter.model.Person;

@RequestScoped
public class RegisterService {

	@Inject
	private PersonDao pd;

	public boolean register(String username, String password) {

		if (pd.getPersonByUserName(username) != null) {
			return false;
		}

		password = Crypto.md5(password);

		Person person = new Person(username, password);
		pd.create(person);
		return true;

	}
}