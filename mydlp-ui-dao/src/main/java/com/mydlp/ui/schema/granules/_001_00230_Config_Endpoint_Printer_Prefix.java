package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00230_Config_Endpoint_Printer_Prefix extends AbstractGranule {

	@Override
	protected void callback() {

		Config printerPrefix = new Config();	
		printerPrefix.setKey("printer_prefix");
		printerPrefix.setValue("MyDLP");
		
		getHibernateTemplate().saveOrUpdate(printerPrefix);	
	}

}
