package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00028_Config_Auto_Distribution extends AbstractGranule {

	@Override
	protected void callback() {

		Config autoDistribution = new Config();
		autoDistribution.setKey("auto_distribution");
		autoDistribution.setValue("false");

		Config autoDistributionNodes = new Config();
		autoDistributionNodes.setKey("auto_distribution_nodes");
		autoDistributionNodes.setValue("['localhost']");

		getHibernateTemplate().saveOrUpdate(autoDistribution);
		getHibernateTemplate().saveOrUpdate(autoDistributionNodes);	

	}

}
