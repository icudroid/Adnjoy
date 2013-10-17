package fr.k2i.adnjoy.stripes.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.k2i.adnjoy.business.user.User;

public class SecurityFilter implements Filter {
	private static Set<String> publicUrls = new HashSet<String>();

	static {
		publicUrls.add("/Home.htm");
		publicUrls.add("/CreateAcount.htm");
		publicUrls.add("/ValidateAccount.htm");
		publicUrls.add("/Android/LoginErr.htm");
		publicUrls.add("/Android/Connexion.htm");
		publicUrls.add("/Android/CreateAcount.htm");
		publicUrls.add("/Android/UpdateDBImage.htm");
		publicUrls.add("/Android/Chanels.htm");
		/*publicUrls.add("/");
		publicUrls.add("/");
		publicUrls.add("/");*/
//		adminUrls.add("/Admin");
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		User user = (User) request.getSession().getAttribute("user");
		User admin = (User) request.getSession().getAttribute("admin");
		//pas de connexion uniquement les pages public sont accésibles
		if (user != null) {
			if (isAdminResource(request) && admin == null) {
				response.sendRedirect(request.getContextPath()+ "/Home.htm");
			} else {
				filterChain.doFilter(request, response);
			}
		} else if (isPublicResource(request)) {
			filterChain.doFilter(request, response);
		} else {
			
			if (isAdminResource(request)) {
				request.setAttribute("login", true);
				filterChain.doFilter(request, response);
				//response.sendRedirect(request.getContextPath()+ "/Home.htm");
			}else if (isAndroidResource(request) && ! isPublicResource(request)) {
				response.sendRedirect(request.getContextPath()+ "/Android/LoginErr.htm");
			}else{
				request.setAttribute("login", true);
				filterChain.doFilter(request, response);
			}
		}
	}

	private boolean isPublicResource(HttpServletRequest request) {
        String resource = request.getServletPath();

        return publicUrls.contains(request.getServletPath())
                || (!resource.endsWith(".jsp") && !resource.endsWith(".htm"));
	}

	private boolean isAdminResource(HttpServletRequest request) {
        return request.getServletPath().startsWith("/Admin");
	}
	
	private boolean isAndroidResource(HttpServletRequest request) {
        return request.getServletPath().startsWith("/Android");
	}
 
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
