import javax.jcr.Session

Session session = request.jcrSession

if (request.getParameter('p')) {
  def path = request.getParameter('p')
  session.save {
	getNodeByIdentifier(path).remove()
  }
}
redirect '/'
