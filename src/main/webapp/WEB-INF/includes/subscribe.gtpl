<div class="subscribe" title="Modify subscriptions">
    <form action="subscribe.groovy" method="GET">
        <input type="text" name="s"/>
        <input type="submit" value="Subscribe"/>
    </form>
    <%
	def subscriptionQuery = new org.mnode.juicer.query.QueryBuilder(request.jcrSession.workspace.queryManager).with {
		query(
			source: selector(nodeType: 'nt:unstructured', name: 'subscriptions'),
			constraint: and(
				constraint1: descendantNode(selectorName: 'subscriptions', path: '/mn:subscriptions'),
				constraint2: propertyExistence(selectorName: 'subscriptions', propertyName: 'mn:status'))
			)
		}

	subscriptionQuery.execute().nodes.each { subscription ->
		html.div(class: 'site') {
			img(src: "/favicon.groovy?p=${subscription.identifier}")
			a(href: subscription['mn:link'].string, subscription['mn:title'].string)
		}
	}
	%>

</div>
