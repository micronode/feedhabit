import javax.jcr.Session
import javax.jcr.SimpleCredentials
import javax.naming.InitialContext

def context = new InitialContext()
def repository = context.lookup('feedhabit')

Session session = repository.login(new SimpleCredentials('readonly', ''.toCharArray()))

if (request.getParameter('p')) {
  def path = request.getParameter('p')
  def thumbnailProperty = session.getNode(path)['mn:thumbnail']
  def favIconProperty = session.getNode(path).parent['mn:icon']
  if (thumbnailProperty) {
  	redirect thumbnailProperty.string
  }
  else if (favIconProperty) {
    sout << favIconProperty.binary.stream
  }
  else {
	redirect '/images/fh48.png'
  }
}
