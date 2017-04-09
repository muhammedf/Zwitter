package sunofkyuss.zwitter.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import sunofkyuss.zwitter.beans.utility.Crypto;
import sunofkyuss.zwitter.dao.PersonDao;
import sunofkyuss.zwitter.model.Person;

@RequestScoped
public class LoginService {

	@Inject
	private PersonDao pd;

	public Person login(String username, String password) {

		Person person = pd.getPersonByUserName(username);
		
		if(person==null){
			return null;
		}
		
		if (Crypto.md5Equals(password, person.getPassword())) {
			return person;
		}

		return null;
	}

}
