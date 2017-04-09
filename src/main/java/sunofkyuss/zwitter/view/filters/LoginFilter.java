package sunofkyuss.zwitter.view.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omnifaces.filter.HttpFilter;
import org.omnifaces.util.Servlets;

@WebFilter("/app/*")
public class LoginFilter extends HttpFilter{

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			FilterChain chain) throws ServletException, IOException {
		
		String loginURL = request.getContextPath() + "/login.xhtml";

		boolean loggedIn = (session != null) && (session.getAttribute("user") != null);
		boolean loginRequest = request.getRequestURI().equals(loginURL);
		boolean resourceRequest = Servlets.isFacesResourceRequest(request);

		if (loggedIn || loginRequest || resourceRequest) {
			if (!resourceRequest) { // Prevent browser from caching restricted
									// resources. See also
									// http://stackoverflow.com/q/4194207/157882
				Servlets.setNoCacheHeaders(response);
			}

			chain.doFilter(request, response); // So, just continue request.
		} else {
			Servlets.facesRedirect(request, response, loginURL);
		}
	}

}
