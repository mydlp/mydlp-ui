package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00100_Config_Notification extends AbstractGranule {

	@Override
	protected void callback() {
		
		Config emailNotificationSubject = new Config();
		emailNotificationSubject.setKey("email_notification_message_subject");
		emailNotificationSubject.setValue("Notifications from MyDLP");

		Config emailNotificationBody = new Config();	
		emailNotificationBody.setKey("email_notification_message");
		emailNotificationBody.setValue("Hello,\nThis is an auto-generated message. This message aims to inform you about some incidents that have been recently occurred and logged in your MyDLP system.\nYou are recieving this message because you have subscribed to be notified for incidents related to a rule in MyDLP.\nFor details, please log on to MyDLP Management Console and go to Logs screen.\nIf you do not want to recieve these emails, please contact to your system administrator.\nPlease do not reply this email.");

		getHibernateTemplate().saveOrUpdate(emailNotificationSubject);
		getHibernateTemplate().saveOrUpdate(emailNotificationBody);	

	}

}
	