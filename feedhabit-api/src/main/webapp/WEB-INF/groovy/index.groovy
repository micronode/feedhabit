import org.mnode.juicer.query.QueryBuilder

import javax.jcr.Session
import javax.jcr.query.qom.QueryObjectModelConstants

Session session = request.jcrSession
/*
JcrFeedCallback callback = [node:session.save {rootNode << 'mn:subscriptions'}, downloadEnclosures:false]
session.save {rootNode << 'mn:tags'}

FeedReader reader = new FeedReaderImpl(new FeedFetcherCacheImpl('org.mnode.newsagent.reader.feedCache'))
reader.read(new SiteResolver().getFeedUrls("slashdot.org")[0], callback)
reader.read(new SiteResolver().getFeedUrls("theregister.co.uk")[0], callback)
reader.read(new SiteResolver().getFeedUrls("ausdroid.net")[0], callback)
reader.read(new SiteResolver().getFeedUrls("reddit.com")[0], callback)
reader.read(new SiteResolver().getFeedUrls("infoworld.com")[0], callback)
*/
//request.jcr = session
def subscriptionQuery = new QueryBuilder(session.workspace.queryManager).with {
	query(
		source: selector(nodeType: 'nt:unstructured', name: 'subscriptions'),
		constraint: and(
			constraint1: descendantNode(selectorName: 'subscriptions', path: '/mn:subscriptions'),
			constraint2: and(
				constraint1: propertyExistence(selectorName: 'subscriptions', propertyName: 'mn:date'),
				constraint2: comparison(
						operand1: propertyValue(selectorName: 'subscriptions', propertyName: 'mn:date'),
						operator: QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO,
						operand2: literal(session.valueFactory.createValue((new Date() - 1).toCalendar())),
					))),
		orderings: [
			descending(operand: propertyValue(selectorName: 'subscriptions', propertyName: 'mn:date'))]
	)
}
/*
request.feedStream = []
subscriptionQuery.execute().nodes.toList().each {
	request.feedStream.addAll it.nodes.toList()
}
*/
request.feedStream = subscriptionQuery.execute().rows
//request.feedStream.sort(true) { a, b ->
//	b['mn:date'].date.time <=> a['mn:date'].date.time
//}

forward 'index.html'
