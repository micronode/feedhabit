package com.feedhabit

import javax.jcr.Session
import javax.jcr.SimpleCredentials
import javax.naming.InitialContext
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class JcrSessionFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		def context = new InitialContext()
		def repository = context.lookup('feedhabit')
		
		Session session = repository.login(new SimpleCredentials('readonly', ''.toCharArray()))
		request.setAttribute('jcrSession', session)
		
		chain.doFilter(request, response)
		
		session.logout()
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
