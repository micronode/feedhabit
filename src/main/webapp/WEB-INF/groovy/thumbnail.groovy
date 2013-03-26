if (request.getParam('p')) {
  def path = request.getParam('p')
  def favIconProperty = session.getNode(path).parent['mn:icon']
  if (favIconProperty) {
    sout << favIconProperty.binary.stream
  }
}
