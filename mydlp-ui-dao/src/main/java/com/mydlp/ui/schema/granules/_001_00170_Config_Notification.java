package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00170_Config_Notification extends AbstractGranule {

	@Override
	protected void callback() {
		
		Config emailNotificationFrom = new Config();
		emailNotificationFrom.setKey("email_notification_message_from");
		emailNotificationFrom.setValue("support@mydlp.com");

		getHibernateTemplate().saveOrUpdate(emailNotificationFrom);
	}

}
	