import javax.jcr.Session
import javax.jcr.SimpleCredentials
import javax.naming.InitialContext

def context = new InitialContext()
def repository = context.lookup('feedhabit')

Session session = repository.login(new SimpleCredentials('readonly', ''.toCharArray()))

if (request.getParam('p')) {
  def path = request.getParam('p')
  def favIconProperty = session.getNode(path).parent['mn:icon']
  if (favIconProperty) {
    sout << favIconProperty.binary.stream
  }
}
