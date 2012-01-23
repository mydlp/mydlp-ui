package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00025_Config_Smtp extends AbstractGranule {

	@Override
	protected void callback() {
		
		Config smtpHeloName = new Config();
		smtpHeloName.setKey("smtp_helo_name");
		smtpHeloName.setValue("mydlp.com");
		
		Config smtpNextHopHost = new Config();	
		smtpNextHopHost.setKey("smtp_next_hop_host");
		smtpNextHopHost.setValue("localhost");
		
		Config smtpNextHopPort = new Config();
		smtpNextHopPort.setKey("smtp_next_hop_port");
		smtpNextHopPort.setValue("10027");
		
		Config smtpBypassOnFail = new Config();
		smtpBypassOnFail.setKey("smtp_bypass_on_fail");
		smtpBypassOnFail.setValue("true");
		
		Config smtpEnableForAll	= new Config(); 
		smtpEnableForAll.setKey("smtp_enable_for_all");
		smtpEnableForAll.setValue("true");
		
		getHibernateTemplate().saveOrUpdate(smtpHeloName);
		getHibernateTemplate().saveOrUpdate(smtpNextHopHost);
		getHibernateTemplate().saveOrUpdate(smtpNextHopPort);
		getHibernateTemplate().saveOrUpdate(smtpBypassOnFail);
		getHibernateTemplate().saveOrUpdate(smtpEnableForAll);
			
	}

}
