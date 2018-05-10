package com.guoke.filter;

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

/**
 * Servlet Filter implementation class HomeFilter
 */
@WebFilter("/HomeFilter")
public class HomeFilter implements Filter {

	private String homeUrl;
	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}
	
    /**
     * Default constructor. 
     */
    public HomeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String url = httpServletRequest.getRequestURI().toString();
		
		String root = httpServletRequest.getContextPath();
		if (url.length() > 0) {
			if(url.indexOf(";")>0) {
				url= url.substring(0,url.indexOf(";"));
			}
			url = url.substring(0, url.length() - 1);
		}
		if (url.equals(root)) {
			httpServletResponse.sendRedirect(root + getHomeUrl());
		} 
		else {
			chain.doFilter(request, response);
		}
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub		
		String homeUrl = fConfig.getInitParameter("home");
		this.setHomeUrl(homeUrl);
	}

	

}
