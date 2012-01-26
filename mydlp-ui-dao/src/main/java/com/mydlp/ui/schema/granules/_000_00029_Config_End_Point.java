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
		
		getHibernateTemplate().saveOrUpdate(usbSerialAccessControl);
		getHibernateTemplate().saveOrUpdate(printMonitor);	
		getHibernateTemplate().saveOrUpdate(logLevel);
		getHibernateTemplate().saveOrUpdate(logLimit);	

	}

}
