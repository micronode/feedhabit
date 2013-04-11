import javax.jcr.Session
import javax.jcr.SimpleCredentials
import javax.naming.InitialContext

import org.mnode.juicer.query.QueryBuilder
import org.mnode.newsagent.FeedReader
import org.mnode.newsagent.FeedReaderImpl
import org.mnode.newsagent.jcr.JcrFeedCallback
import org.mnode.newsagent.util.FeedFetcherCacheImpl
import org.mnode.newsagent.util.SiteResolver


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
			constraint2: propertyExistence(selectorName: 'subscriptions', propertyName: 'mn:status'))
	)
}
request.feedStream = []
subscriptionQuery.execute().nodes.toList().each {
	request.feedStream.addAll it.nodes.toList()
}
request.feedStream.sort(true) { a, b ->
	b['mn:date'].date.time <=> a['mn:date'].date.time
}

forward 'index.html'
