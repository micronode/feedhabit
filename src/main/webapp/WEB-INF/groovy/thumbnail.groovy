def path = request.getParam('p')
def favIconProperty = session.getNode(path).parent['mn:icon']
if (favIconProperty) {
  out.bytes = favIconProperty.binary.stream.bytes
}
