package com.belhopat.backoffice.util.servlet;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BelhopatServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed( final ServletContextEvent event ) {
		// Do nothing
	}

	@Override
	public void contextInitialized( ServletContextEvent event ) {
		BelhopatServletContextInfo.setContextPath( event.getServletContext().getContextPath() );
		BelhopatServletContextInfo.setRealPath( event.getServletContext().getRealPath( File.separator ));
		BelhopatServletContextInfo.setContext( event.getServletContext() );
	}

}
