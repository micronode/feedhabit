import javax.jcr.Session
import javax.jcr.SimpleCredentials
import javax.naming.InitialContext

def context = new InitialContext()
def repository = context.lookup('feedhabit')

Session session = repository.login()

if (request.getParameter('p')) {
  def path = request.getParameter('p')
  session.save {
	getNodeByIdentifier(path).remove()
  }
}
redirect '/'
