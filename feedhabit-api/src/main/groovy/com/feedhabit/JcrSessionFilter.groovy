package com.feedhabit

import javax.jcr.Session
import javax.jcr.SimpleCredentials
import javax.naming.InitialContext
import javax.servlet.*

class JcrSessionFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		def context = new InitialContext()
		def repository = context.lookup('feedhabit')
		
		Session session = repository.login(new SimpleCredentials('readonly', ''.toCharArray()))
		request.setAttribute('jcrSession', session)
		
		try {
			chain.doFilter(request, response)
		} finally {
			session.logout()
		}
	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

}
