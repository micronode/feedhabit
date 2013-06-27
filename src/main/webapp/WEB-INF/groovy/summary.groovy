import javax.jcr.Session
import javax.jcr.SimpleCredentials
import javax.naming.InitialContext

def context = new InitialContext()
def repository = context.lookup('feedhabit')

Session session = repository.login(new SimpleCredentials('readonly', ''.toCharArray()))

if (request.getParameter('p')) {
  def path = request.getParameter('p')
  def item = session.getNodeByIdentifier(path)
  json.data {
    title "${item['mn:title'].string.take(50)}..."
    content item['mn:description'].string
  }
}
