package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00100_Config_Notification extends AbstractGranule {

	@Override
	protected void callback() {

		Config emailNotificationPage = new Config();	
		emailNotificationPage.setKey("email_notification_message");
		emailNotificationPage.setValue("hede hodo");

		getHibernateTemplate().saveOrUpdate(emailNotificationPage);	

	}

}
