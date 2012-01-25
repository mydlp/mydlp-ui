package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00026_Config_Icap extends AbstractGranule {

	@Override
	protected void callback() {

		Config icapReqModPath = new Config();
		icapReqModPath.setKey("icap_reqmod_path");
		icapReqModPath.setValue("/dlp");

		Config icapRespModPath = new Config();
		icapRespModPath.setKey("icap_respmod_path");
		icapRespModPath.setValue("dlp-respmod");

		Config icapMaxConnections = new Config();
		icapMaxConnections.setKey("icap_max_connections");
		icapMaxConnections.setValue("0");

		Config icapOptionsTTL = new Config();
		icapOptionsTTL.setKey("icap_options_ttl");
		icapOptionsTTL.setValue("0");

		Config icapLogPass = new Config();
		icapLogPass.setKey("icap_log_pass");
		icapLogPass.setValue("false");

		Config icapLogPassLowerLimit = new Config();	
		icapLogPassLowerLimit.setKey("icap_log_pass_lower_limit");
		icapLogPassLowerLimit.setValue("10240");

		getHibernateTemplate().saveOrUpdate(icapReqModPath);
		getHibernateTemplate().saveOrUpdate(icapRespModPath);
		getHibernateTemplate().saveOrUpdate(icapMaxConnections);
		getHibernateTemplate().saveOrUpdate(icapOptionsTTL);
		getHibernateTemplate().saveOrUpdate(icapLogPass);
		getHibernateTemplate().saveOrUpdate(icapLogPassLowerLimit);

	}

}
