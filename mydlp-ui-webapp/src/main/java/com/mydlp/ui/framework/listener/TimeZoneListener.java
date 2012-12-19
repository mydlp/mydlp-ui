package com.mydlp.ui.framework.listener;

import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TimeZoneListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
	}


}
