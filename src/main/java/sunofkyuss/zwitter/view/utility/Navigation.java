package sunofkyuss.zwitter.view.utility;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Navigation {

	public void redirectTo(MyUrl url) {

		String surl = url.addContext(FacesContext.getCurrentInstance().getExternalContext().getContextName()).asString();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(surl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MyUrl getRedicrect(MyUrl url) {
		return this.addRedirect(url)
				.addContext(FacesContext.getCurrentInstance().getExternalContext().getContextName());
	}

	public MyUrl login() {
		return new MyUrl("/login.xhtml");
	}

	public MyUrl register() {
		return new MyUrl("/register.xhtml");
	}

	public MyUrl index() {
		return new MyUrl("/app/index.xhtml");
	}

	public MyUrl addZwitID(MyUrl url, long id) {
		url.addParameter("zid", String.valueOf(id));
		return url;
	}

	public MyUrl addUserID(MyUrl url, long id) {
		url.addParameter("uid", String.valueOf(id));
		return url;
	}

	public MyUrl addRedirect(MyUrl url) {
		url.addParameter("faces-redirect", String.valueOf(true));
		return url;
	}
}
