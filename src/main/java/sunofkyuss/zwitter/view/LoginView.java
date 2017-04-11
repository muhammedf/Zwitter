package sunofkyuss.zwitter.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import sunofkyuss.zwitter.beans.LoginService;
import sunofkyuss.zwitter.model.Person;
import sunofkyuss.zwitter.view.utility.Messages;
import sunofkyuss.zwitter.view.utility.Navigation;

@Named
@RequestScoped
public class LoginView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Navigation nav;

	@Inject
	private LoginService ls;

	private String username, password;

	public void login() {

		Person per = ls.login(username, password);

		if (per == null) {
			Messages.error("Error!", "Login Failed.");
			return;
		}

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", per);

		nav.redirectTo(nav.index());
	}

	public void logut() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		nav.redirectTo(nav.login());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
