import org.mnode.newsagent.FeedReader
import org.mnode.newsagent.FeedReaderImpl
import org.mnode.newsagent.jcr.JcrFeedCallback
import org.mnode.newsagent.util.FeedFetcherCacheImpl
import org.mnode.newsagent.util.SiteResolver

import javax.jcr.Session

if (request.getParameter('s')) {
	Session session = request.jcrSession
	JcrFeedCallback callback = [node:session.save {rootNode << 'mn:subscriptions'}, downloadEnclosures:false]
	session.save {rootNode << 'mn:tags'}
	
	FeedReader reader = new FeedReaderImpl(new FeedFetcherCacheImpl('org.mnode.newsagent.reader.feedCache'))
	try {
		reader.read(new SiteResolver().getFeedUrls(request.getParameter('s'))[0], callback)
	} catch (IllegalArgumentException iae) {
		try {
			reader.read(new URL(request.getParameter('s')), callback)
		} catch (MalformedURLException mue) {
			// log invalid feed..
		}
	}
}

redirect '/'
