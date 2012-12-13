package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00160_Config_Thrift_Pool extends AbstractGranule {

	@Override
	protected void callback() {

		Config thriftServerPoolSize = new Config();	
		thriftServerPoolSize.setKey("thrift_server_pool_size");
		thriftServerPoolSize.setValue("24");
		
		Config thriftEndpointPoolSize = new Config();	
		thriftEndpointPoolSize.setKey("thrift_endpoint_pool_size");
		thriftEndpointPoolSize.setValue("3");

		getHibernateTemplate().saveOrUpdate(thriftServerPoolSize);	
		getHibernateTemplate().saveOrUpdate(thriftEndpointPoolSize);

	}

}
