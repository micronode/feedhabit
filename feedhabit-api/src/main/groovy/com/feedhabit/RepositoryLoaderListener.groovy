package com.feedhabit

import groovy.util.logging.Slf4j
import org.apache.jackrabbit.core.jndi.RegistryHelper

import javax.jcr.NamespaceException
import javax.jcr.SimpleCredentials
import javax.naming.InitialContext
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

@Slf4j
class RepositoryLoaderListener implements ServletContextListener {

    def context = new InitialContext()
    
    void contextInitialized(ServletContextEvent e) {
        File homeDir = [System.getProperty('feedhabit.home', System.getProperty('user.home') + '/.feedhabit')]
        homeDir.mkdirs()
        log.info "feedhabit home: [$homeDir.absolutePath]"
        def configFile = new File(homeDir, "config.xml")
        configFile.text = RepositoryLoaderListener.getResourceAsStream("/config-mysql.xml").text
        
        File repositoryLocation = [homeDir, "data"]
        
        RegistryHelper.registerRepository(context, 'feedhabit', configFile.absolutePath,
             repositoryLocation.absolutePath, false)
        
        def context = new InitialContext()
        def repository = context.lookup('feedhabit')
        def session = repository.login(new SimpleCredentials('readonly', ''.toCharArray()))
        e.servletContext.setAttribute('jcr', session)
        
        try {
            session.workspace.namespaceRegistry.registerNamespace('mn', 'http://mnode.org/namespace/1.0')
        }
        catch (NamespaceException ne) {
            log.warn ne.message
        }
    }
    
    void contextDestroyed(ServletContextEvent e) {
        RegistryHelper.unregisterRepository(context, 'feedhabit')
    }
}
