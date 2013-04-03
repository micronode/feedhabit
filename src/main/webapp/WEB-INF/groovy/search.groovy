import javax.jcr.query.Query

if (request.getParameter('q')) {
	def searchQuery = request.jcrSession.workspace.queryManager.createQuery("//*[jcr:contains(., '${request.getParameter('q')}')]/(@mn:text|rep:excerpt(.))", Query.XPATH)
	 request.setAttribute('query', searchQuery)
 
	 forward 'search.html'
}
else {
	redirect 'index.groovy'
}
