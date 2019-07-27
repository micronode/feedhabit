import groovy.json.JsonParser

try {
    def categoriesUrl = currentNode.getProperty('uri').string.toURL().text
    def limit = request.requestPathInfo.suffix ? request.requestPathInfo.suffix.substring(1) as int : Integer.MAX_VALUE;

    def categories = JsonParser.parseText(categoriesUrl)
    categories.list().take(limit).each {
        out.write("<li><a href=\"$it.link\" title=\"${it.description.text().replaceAll('"', '&quot;')}\"  data-html=\"true\" rel=\"tooltip\">$it.title</a></li>")
    }
} catch (ex) {
    ex.printStackTrace()
}
