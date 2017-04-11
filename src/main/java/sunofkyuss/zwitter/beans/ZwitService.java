package sunofkyuss.zwitter.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sunofkyuss.zwitter.Visibility;
import sunofkyuss.zwitter.dao.PersonDao;
import sunofkyuss.zwitter.dao.ZwitDao;
import sunofkyuss.zwitter.model.Person;
import sunofkyuss.zwitter.model.Zwit;

@Stateless
public class ZwitService {

	@Inject
	private ZwitDao zd;

	@Inject
	private PersonDao pd;

	public Zwit createZwit(long userID, String zmessage, Visibility zvisibility) {

		Zwit zwit = null;

		try {
			Person owner = pd.findById(userID);
			zwit = new Zwit(owner, zmessage, zvisibility);
			zd.create(zwit);
		} catch (Exception e) {
			return null;
		}

		return zwit;
	}
	
}
