package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00034_InventoryCategories2 extends AbstractGranule {

	@Override
	protected void callback() {
		
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory predefined = DAOUtil.getSingleResult(list);
		
		InventoryCategory compliance = new InventoryCategory();
		compliance.setNameKey("inventory.compliance.predefined");
		compliance.setEditable(false);
		compliance.setCategory(predefined);
		getHibernateTemplate().saveOrUpdate(compliance);
		
		InventoryCategory complianceFinance = new InventoryCategory();
		complianceFinance.setNameKey("inventory.compliance.finance.predefined");
		complianceFinance.setEditable(false);
		complianceFinance.setCategory(compliance);
		getHibernateTemplate().saveOrUpdate(complianceFinance);
		
		InventoryCategory complianceFederalRegulations = new InventoryCategory();
		complianceFederalRegulations.setNameKey("inventory.compliance.federal_regulations.predefined");
		complianceFederalRegulations.setEditable(false);
		complianceFederalRegulations.setCategory(compliance);
		getHibernateTemplate().saveOrUpdate(complianceFederalRegulations);
		
		InventoryCategory complianceCompanyConfidentiality = new InventoryCategory();
		complianceCompanyConfidentiality.setNameKey("inventory.compliance.company_confidentiality.predefined");
		complianceCompanyConfidentiality.setEditable(false);
		complianceCompanyConfidentiality.setCategory(compliance);
		getHibernateTemplate().saveOrUpdate(complianceCompanyConfidentiality);
		
		InventoryCategory networkSecurity = new InventoryCategory();
		networkSecurity.setNameKey("inventory.compliance.network_security.predefined");
		networkSecurity.setEditable(false);
		networkSecurity.setCategory(compliance);
		getHibernateTemplate().saveOrUpdate(networkSecurity);
		
		InventoryCategory userDefined = new InventoryCategory();
		userDefined.setNameKey("inventory.userDefined");
		userDefined.setEditable(false);
		getHibernateTemplate().save(userDefined);
	}

}
