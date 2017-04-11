package sunofkyuss.zwitter.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import sunofkyuss.zwitter.beans.FlowService;
import sunofkyuss.zwitter.model.Zwit;
import sunofkyuss.zwitter.view.utility.Constants;
import sunofkyuss.zwitter.view.utility.SessionMap;

@Named
@ViewScoped
public class MyZwitsView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FlowService fs;
	
	private List<Zwit> zwits;

	@PostConstruct
	public void init() {
		zwits = fs.getMineZwits(SessionMap.getUser().getId(), new Date(), Constants.FETCHSIZE);
	}

	public void more() {

		List<Zwit> more = fs.getMineZwits(SessionMap.getUser().getId(),
				zwits.isEmpty() ? new Date() : zwits.get(zwits.size() - 1).getCreateDate(), Constants.FETCHSIZE);

		zwits.addAll(more);
	}

	public List<Zwit> getZwits() {
		return zwits;
	}

	public void setZwits(List<Zwit> zwits) {
		this.zwits = zwits;
	}

}
