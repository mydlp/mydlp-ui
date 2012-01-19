package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00024_Config_ErrorAction extends AbstractGranule {

	@Override
	protected void callback() {
		
		Config errorAction = new Config();
		errorAction.setKey("error_action");
		errorAction.setValue("pass");
		
		getHibernateTemplate().saveOrUpdate(errorAction);
		
	}

}
