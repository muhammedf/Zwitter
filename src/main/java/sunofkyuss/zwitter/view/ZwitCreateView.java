package sunofkyuss.zwitter.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import sunofkyuss.zwitter.Visibility;
import sunofkyuss.zwitter.beans.ZwitService;
import sunofkyuss.zwitter.model.Zwit;
import sunofkyuss.zwitter.view.utility.Messages;
import sunofkyuss.zwitter.view.utility.SessionMap;

@Named
@RequestScoped
public class ZwitCreateView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ZwitService zs;
	
	@Inject
	private MyZwitsView mzv;
	
	@Inject
	private SessionMap sm;

	private String message;
	private Visibility visibility;

	@PostConstruct
	public void init() {
	}

	public void createZwit() {
		Zwit zwit = zs.createZwit(sm.getUser().getId(), message, visibility);

		if (zwit == null) {
			Messages.info("Error!", "Zwitting failed.");
			return;
		}
		Messages.info("Success!", "Zwitted.");
		
		mzv.getZwits().add(0, zwit);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

}
