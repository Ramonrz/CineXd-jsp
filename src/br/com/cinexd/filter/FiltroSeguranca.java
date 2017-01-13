package br.com.cinexd.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/FiltroSeguranca")
public class FiltroSeguranca implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url = httpServletRequest.getRequestURI();
		HttpSession sessao = httpServletRequest.getSession();

		if (sessao.getAttribute("userAutenticado") != null || url.lastIndexOf("login.jsp") > -1
				|| url.lastIndexOf("LoginController") > -1 || url.matches(".*(png|css|ttf)")) {
			chain.doFilter(request, response);

		} else {
			((HttpServletResponse) response).sendRedirect("/CineXd/login.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */

	public void init(FilterConfig fConfig) throws ServletException {

	}

}