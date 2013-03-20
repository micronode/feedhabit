import javax.jcr.Session
import javax.jcr.SimpleCredentials
import javax.naming.InitialContext

import org.mnode.newsagent.FeedReader
import org.mnode.newsagent.FeedReaderImpl
import org.mnode.newsagent.jcr.JcrFeedCallback
import org.mnode.newsagent.util.FeedFetcherCacheImpl
import org.mnode.newsagent.util.SiteResolver

def context = new InitialContext()
def repository = context.lookup('feedhabit')

Session session = repository.login(new SimpleCredentials('readonly', ''.toCharArray()))

JcrFeedCallback callback = [node:session.save {rootNode << 'mn:subscriptions'}, downloadEnclosures:false]
session.save {rootNode << 'mn:tags'}

FeedReader reader = new FeedReaderImpl(new FeedFetcherCacheImpl('org.mnode.newsagent.reader.feedCache'))
reader.read(new SiteResolver().getFeedUrls("slashdot.org")[0], callback)

request.jcr = session
forward 'index.html'
