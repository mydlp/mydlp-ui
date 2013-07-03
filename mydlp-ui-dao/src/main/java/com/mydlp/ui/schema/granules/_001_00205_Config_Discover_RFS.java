package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00205_Config_Discover_RFS extends AbstractGranule {

	@Override
	protected void callback() {

		Config rfsInterval = new Config();
		rfsInterval.setKey("discover_rfs_interval");
		rfsInterval.setValue("7200000");

		Config rfsStartup = new Config();
		rfsStartup.setKey("discover_rfs_on_startup");
		rfsStartup.setValue("false");
		
		Config webInterval = new Config();
		webInterval.setKey("discover_web_interval");
		webInterval.setValue("7200000");

		Config webStartup = new Config();
		webStartup.setKey("discover_web_on_startup");
		webStartup.setValue("false");

		getHibernateTemplate().saveOrUpdate(rfsInterval);
		getHibernateTemplate().saveOrUpdate(rfsStartup);
		getHibernateTemplate().saveOrUpdate(webInterval);
		getHibernateTemplate().saveOrUpdate(webStartup);

	}

}
