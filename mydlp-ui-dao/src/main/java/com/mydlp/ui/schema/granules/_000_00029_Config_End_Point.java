package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00029_Config_End_Point extends AbstractGranule {

	@Override
	protected void callback() {

		Config usbSerialAccessControl = new Config();
		usbSerialAccessControl.setKey("usb_serial_access_control");
		usbSerialAccessControl.setValue("false");

		Config printMonitor = new Config();
		printMonitor.setKey("print_monitor");
		printMonitor.setValue("false");

		Config logLevel = new Config();
		logLevel.setKey("log_level");
		logLevel.setValue("0");

		Config logLimit = new Config();
		logLimit.setKey("log_limit");
		logLimit.setValue("10485760");

		Config syncInterval = new Config();
		syncInterval.setKey("sync_interval");
		syncInterval.setValue("300000");
		
		Config discoverFSInterval = new Config();
		discoverFSInterval.setKey("discover_fs_interval");
		discoverFSInterval.setValue("7200000");
		
		Config discoverFSOnStartup = new Config();
		discoverFSOnStartup.setKey("discover_fs_on_startup");
		discoverFSOnStartup.setValue("false");
		
		Config ignoreDiscoverMaxSizeExceeded = new Config();
		ignoreDiscoverMaxSizeExceeded.setKey("ignore_discover_max_size_exceeded");
		ignoreDiscoverMaxSizeExceeded.setValue("true");

		getHibernateTemplate().saveOrUpdate(usbSerialAccessControl);
		getHibernateTemplate().saveOrUpdate(printMonitor);	
		getHibernateTemplate().saveOrUpdate(logLevel);
		getHibernateTemplate().saveOrUpdate(logLimit);	
		getHibernateTemplate().saveOrUpdate(syncInterval);
		getHibernateTemplate().saveOrUpdate(discoverFSInterval);
		getHibernateTemplate().saveOrUpdate(discoverFSOnStartup);
		getHibernateTemplate().saveOrUpdate(ignoreDiscoverMaxSizeExceeded);
	}

}
