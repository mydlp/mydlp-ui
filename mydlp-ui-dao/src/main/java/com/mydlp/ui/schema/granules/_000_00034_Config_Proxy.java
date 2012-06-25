package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00034_Config_Proxy extends AbstractGranule {

	@Override
	protected void callback() {
		Config licenseProxyHost = new Config();
		licenseProxyHost.setKey("license_proxy_host");
		licenseProxyHost.setValue("");
		
		Config licenseProxyPort = new Config();
		licenseProxyPort.setKey("license_proxy_port");
		licenseProxyPort.setValue("");
		
		getHibernateTemplate().saveOrUpdate(licenseProxyHost);
		getHibernateTemplate().saveOrUpdate(licenseProxyPort);
	}

}

