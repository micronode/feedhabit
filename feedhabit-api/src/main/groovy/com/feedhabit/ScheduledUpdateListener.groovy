package com.feedhabit

import groovy.util.logging.Slf4j
import org.mnode.juicer.query.QueryBuilder
import org.mnode.newsagent.FeedReader
import org.mnode.newsagent.FeedReaderImpl
import org.mnode.newsagent.jcr.JcrFeedCallback
import org.mnode.newsagent.util.FeedFetcherCacheImpl

import javax.jcr.SimpleCredentials
import javax.naming.InitialContext
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Slf4j
class ScheduledUpdateListener implements ServletContextListener {

	javax.jcr.Session session
	
	ExecutorService updateThread
	
	public void contextDestroyed(ServletContextEvent e) {
		updateThread.shutdown()
//		session.logout()
	}

	public void contextInitialized(ServletContextEvent e) {
        def context = new InitialContext()
        def repository = context.lookup('feedhabit')
        session = repository.login(new SimpleCredentials('update', ''.toCharArray()))
		
		JcrFeedCallback callback = [node:session.save {rootNode << 'mn:subscriptions'}, downloadEnclosures:false]
		session.save {rootNode << 'mn:tags'}
		
		final FeedReader reader = new FeedReaderImpl(new FeedFetcherCacheImpl('org.mnode.newsagent.reader.feedCache'))
		def subscriptionQuery = new QueryBuilder(session.workspace.queryManager).with {
			query(
				source: selector(nodeType: 'nt:unstructured', name: 'subscriptions'),
				constraint: and(
					constraint1: descendantNode(selectorName: 'subscriptions', path: '/mn:subscriptions'),
					constraint2: propertyExistence(selectorName: 'subscriptions', propertyName: 'mn:status'))
			)
		}
		
		updateThread = Executors.newSingleThreadScheduledExecutor()
		updateThread.scheduleAtFixedRate({
//			GParsPool.withPool {
				def asyncUpdate = { feedNode ->
					try {
						log.info "Updating feed: ${feedNode['mn:title']?.string}"
						reader.read(new URL(feedNode['mn:source'].string), callback)
						log.info "Update complete: ${feedNode['mn:title']?.string}"
					} catch (Exception ex) {
						log.error ex
					}
				} //.async()
				subscriptionQuery.execute().nodes.toList().each {
					asyncUpdate it
				}
//			}
		}, 0, 60, TimeUnit.MINUTES)
	}
}
