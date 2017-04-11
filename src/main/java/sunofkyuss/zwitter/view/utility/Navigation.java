package sunofkyuss.zwitter.view.utility;

import java.io.IOException;

import javax.faces.context.FacesContext;

public final class Navigation {

	private Navigation(){}
	
	public static void redirectTo(MyUrl url) {

		String surl = url.addContext(FacesContext.getCurrentInstance().getExternalContext().getContextName()).asString();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(surl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static MyUrl getRedicrect(MyUrl url) {
		return addRedirect(url)
				.addContext(FacesContext.getCurrentInstance().getExternalContext().getContextName());
	}

	public static MyUrl login() {
		return new MyUrl("/login.xhtml");
	}

	public static MyUrl register() {
		return new MyUrl("/register.xhtml");
	}

	public static MyUrl index() {
		return new MyUrl("/app/index.xhtml");
	}

	public static MyUrl addZwitID(MyUrl url, long id) {
		url.addParameter("zid", String.valueOf(id));
		return url;
	}

	public static MyUrl addUserID(MyUrl url, long id) {
		url.addParameter("uid", String.valueOf(id));
		return url;
	}

	public static MyUrl addRedirect(MyUrl url) {
		url.addParameter("faces-redirect", String.valueOf(true));
		return url;
	}
}
