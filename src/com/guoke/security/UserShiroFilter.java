package com.guoke.security;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;


public class UserShiroFilter extends AccessControlFilter {
	/**
	 * 验证是否具有权限
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object object)
			throws Exception {
		// TODO Auto-generated method stub
		Subject subject = getSubject(request, response);

		// subject.getSession().
		Object principal = subject.getPrincipal();
		
		HttpServletRequest httpRequest=(HttpServletRequest)request;  
		String url =httpRequest.getServletPath();

        if(principal.toString().toLowerCase().equals("administrator")) return true;
        String username = principal.toString();
        
        

		if (isPermittedAll(username,url)) {
			return true;
		}
		return false;// 跳到onAccessDenied处理
	}
	
	
	
	
	
	private boolean isPermittedAll(String username, String url) {
		
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest arg0, ServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	
}
