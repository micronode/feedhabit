import javax.jcr.Session

Session session = request.getAttribute('jcrSession')

if (request.getParameter('p')) {
  def path = request.getParameter('p')
  def thumbnailProperty = session.getNodeByIdentifier(path)['mn:thumbnail']
  def favIconProperty = session.getNodeByIdentifier(path).parent['mn:icon']
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
