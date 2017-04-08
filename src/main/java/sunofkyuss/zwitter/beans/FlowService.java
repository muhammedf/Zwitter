package sunofkyuss.zwitter.beans;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import sunofkyuss.zwitter.dao.PersonDao;
import sunofkyuss.zwitter.dao.ZwitDao;
import sunofkyuss.zwitter.model.Person;
import sunofkyuss.zwitter.model.Zwit;

@RequestScoped
public class FlowService {

	@Inject
	private ZwitDao zd;

	@Inject
	private PersonDao pd;

	public List<Zwit> getEveryonesZwits(long userID, Date date, int count) {
		System.out.println("flowservice");
		return zd.listEveryones(date, count);
	}

	public List<Zwit> getFriendsZwits(long userID, Date date, int count) {
		Person user = pd.findById(userID);
		return zd.listFriends(user, date, count);
	}

	public List<Zwit> getMineZwits(long userID, Date date, int count) {
		return zd.listMines(userID, date, count);
	}

}