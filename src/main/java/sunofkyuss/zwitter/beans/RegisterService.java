package sunofkyuss.zwitter.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sunofkyuss.zwitter.beans.utility.Crypto;
import sunofkyuss.zwitter.beans.utility.MD5;
import sunofkyuss.zwitter.dao.PersonDao;
import sunofkyuss.zwitter.model.Person;

@Stateless
public class RegisterService {

	@Inject
	private PersonDao pd;
	
	@Inject
	@MD5
	private Crypto cr;

	public boolean register(String username, String password) {

		if (pd.getPersonByUserName(username) != null) {
			return false;
		}

		password = cr.encrypt(password);

		Person person = new Person(username, password);
		pd.create(person);
		return true;

	}
}