package com.feedhabit

import groovy.util.logging.Slf4j
import org.mnode.juicer.query.QueryBuilder

import javax.jcr.SimpleCredentials
import javax.naming.InitialContext
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Slf4j
class ScheduledCleanupListener implements ServletContextListener {

	javax.jcr.Session session
	
	ExecutorService cleanupThread
	
	public void contextDestroyed(ServletContextEvent e) {
		cleanupThread.shutdown()
//		session.logout()
	}

	public void contextInitialized(ServletContextEvent e) {
        def context = new InitialContext()
        def repository = context.lookup('feedhabit')
        session = repository.login(new SimpleCredentials('update', ''.toCharArray()))

		def subscriptionQuery = new QueryBuilder(session.workspace.queryManager).with {
			query(
				source: selector(nodeType: 'nt:unstructured', name: 'subscriptions'),
				constraint: and(
					constraint1: descendantNode(selectorName: 'subscriptions', path: '/mn:subscriptions'),
					constraint2: propertyExistence(selectorName: 'subscriptions', propertyName: 'mn:status'))
			)
		}
		
		cleanupThread = Executors.newSingleThreadScheduledExecutor()
		cleanupThread.scheduleAtFixedRate({
//			GParsPool.withPool {
				def asyncCleanup = { feedNode ->
					try {
						log.info "Cleaning feed: ${feedNode['mn:title']?.string}"
						session.save {
							try {
							feedNode.nodes.each { article ->
								if (article['mn:date'].date.time < (new Date() - 14)) {
									article.remove()
								}
							}
							} catch (Exception ex) {
							log.error ex
							}
						}
						log.info "Cleaning complete: ${feedNode['mn:title']?.string}"
					} catch (Exception ex) {
						log.error ex
					}
				} //.async()
				subscriptionQuery.execute().nodes.toList().each {
					asyncCleanup it
				}
//			}
		}, 0, 4, TimeUnit.HOURS)
	}
}
