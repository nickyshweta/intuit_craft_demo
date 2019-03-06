package com.intuit.craft.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
/**
 * @author nicky
 * Channel processing filter used to filter HTTP requests. APIs other than login/swagger are verified by the 
 * authorization token
 */
public class CustomChannelProcessingFilter extends ChannelProcessingFilter {
	
	private final AuthTokenManager authTokenManager;
	
	public CustomChannelProcessingFilter(AuthTokenManager authTokenManager) {
		// TODO Auto-generated constructor stub
		this.authTokenManager = authTokenManager;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		if(request.getServletPath().equals(WebSecurityConfig.AUTH_WHITELIST)){
			chain.doFilter(req, res);
			return;
		}
		else{
			String authToken = request.getHeader("Authorization");
			if(authTokenManager.verifyToken(authToken)){
				chain.doFilter(req, res);
			}
		}
	}
	
}
