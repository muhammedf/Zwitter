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

@Named
@ViewScoped
public class FlowView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FlowService fs;

	private List<Zwit> zwits;

	@PostConstruct
	public void init() {
		zwits = fs.getEveryonesZwits(1, new Date(), Constants.FETCHSIZE);
	}

	public void daha() {

		Date date;

		if (!zwits.isEmpty())
			date = zwits.get(zwits.size() - 1).getCreateDate();
		else {
			date = new Date();
		}

		zwits.addAll(fs.getEveryonesZwits(1, date, Constants.FETCHSIZE));
	}

	public List<Zwit> getZwits() {
		return zwits;
	}

	public void setZwits(List<Zwit> zwits) {
		this.zwits = zwits;
	}

}