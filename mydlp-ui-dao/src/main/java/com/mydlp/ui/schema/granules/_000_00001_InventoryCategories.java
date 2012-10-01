package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00001_InventoryCategories extends AbstractGranule {

	@Override
	protected void callback() {
		InventoryCategory predefined = new InventoryCategory();
		predefined.setNameKey("inventory.predefined");
		predefined.setEditable(false);
		getHibernateTemplate().save(predefined);
		
		InventoryCategory networks = new InventoryCategory();
		networks.setNameKey("inventory.networks");
		networks.setEditable(false);
		networks.setCategory(predefined);
		getHibernateTemplate().save(networks);
		
		InventoryCategory informationTypes = new InventoryCategory();
		informationTypes.setNameKey("inventory.informationTypes.predefined");
		informationTypes.setEditable(false);
		informationTypes.setCategory(predefined);
		getHibernateTemplate().saveOrUpdate(informationTypes);
		
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
