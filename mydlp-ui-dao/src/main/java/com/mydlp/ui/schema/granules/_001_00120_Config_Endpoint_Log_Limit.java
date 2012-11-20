package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00120_Config_Endpoint_Log_Limit extends AbstractGranule {

	@Override
	protected void callback() {

		Config endpointSpoolSoftLimit = new Config();	
		endpointSpoolSoftLimit.setKey("endpoint_spool_soft_limit");
		endpointSpoolSoftLimit.setValue("52428800");
		
		Config endpointSpoolHardLimit = new Config();	
		endpointSpoolHardLimit.setKey("endpoint_spool_hard_limit");
		endpointSpoolHardLimit.setValue("78643200");

		getHibernateTemplate().saveOrUpdate(endpointSpoolSoftLimit);	
		getHibernateTemplate().saveOrUpdate(endpointSpoolHardLimit);

	}

}
