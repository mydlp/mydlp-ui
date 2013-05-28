package com.mydlp.ui.schema.granules;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;

import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00300_GenerateEmptyADDomainBaseDN extends AbstractGranule {

	@SuppressWarnings("unchecked")
	@Override
	protected void callback() {
		DetachedCriteria criteria = DetachedCriteria.forClass(ADDomain.class);
		List<ADDomain> list = getHibernateTemplate().findByCriteria(criteria);
		for (ADDomain domain : list) {
			if (domain.getBaseDistinguishedName() == null ||
					domain.getBaseDistinguishedName().length() == 0)
			{
				domain.setBaseDistinguishedName(generateBaseDN(domain.getDomainName()));
				getHibernateTemplate().saveOrUpdate(domain);
			}
		}
	}
	
	protected String generateBaseDN(String domainName) {
		String[] tokens = domainName.split(",");
		for (int i = 0; i < tokens.length; i++) {
			String string = tokens[i];
			tokens[i] = "dc=" + string;
		}
		return StringUtils.join(tokens);
	}
}
