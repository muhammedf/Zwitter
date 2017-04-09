package sunofkyuss.zwitter.view.utility;

public class MyUrl {

	private String url;
	private boolean firstParameter;

	public MyUrl(String url) {
		this.url = url;
		firstParameter = true;
	}

	public MyUrl addParameter(String name, String value) {

		if (firstParameter) {
			url += "?";
			firstParameter = false;
		} else {
			url += "&";
		}

		url += name + "=" + value;

		return this;
	}

	public MyUrl addContext(String contextName) {
		url = "/" + contextName + url;
		return this;
	}

	public String asString() {
		return url;
	}
}
