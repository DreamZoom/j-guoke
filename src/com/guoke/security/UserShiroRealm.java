package com.guoke.security;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;


public class UserShiroRealm extends CasRealm  {
	
	private String centerUrl;
	private String siteKey;
	/**
	 * 璁剧疆瑙掕壊鍜屾潈闄愪俊鎭�
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String username = (String)principals.getPrimaryPrincipal(); 
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(); 
		return authorizationInfo; 
	}

	/**
	 * 1銆丆AS璁よ瘉 ,楠岃瘉鐢ㄦ埛韬唤 2銆佸皢鐢ㄦ埛鍩烘湰淇℃伅璁剧疆鍒颁細璇濅腑
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		
		try {
			AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
	        String account = (String) authc.getPrincipals().getPrimaryPrincipal();
	        if(account!=null && !account.equals("")) {
	        	SecurityUtils.getSubject().getSession().setAttribute("account", account);
	        }      
	        return authc;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
        return null;

	}

	public String getCenterUrl() {
		return centerUrl;
	}

	public void setCenterUrl(String centerUrl) {
		this.centerUrl = centerUrl;
	}

	public String getSiteKey() {
		return siteKey;
	}

	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
	}
}
