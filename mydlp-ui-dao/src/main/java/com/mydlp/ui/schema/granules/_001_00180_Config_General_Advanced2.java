package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00180_Config_General_Advanced2 extends AbstractGranule {

	@Override
	protected void callback() {

		Config webArchive = new Config();
		webArchive.setKey("web_archive");
		webArchive.setValue("false");

		Config icapIgnoreBig = new Config();
		icapIgnoreBig.setKey("icap_ignore_big_requests");
		icapIgnoreBig.setValue("true");

		getHibernateTemplate().saveOrUpdate(webArchive);
		getHibernateTemplate().saveOrUpdate(icapIgnoreBig);

	}

}
