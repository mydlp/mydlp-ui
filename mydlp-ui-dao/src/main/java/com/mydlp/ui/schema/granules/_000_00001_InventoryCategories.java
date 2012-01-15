package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00001_InventoryCategories extends AbstractGranule {

	@Override
	protected void callback() {
		InventoryCategory networks = new InventoryCategory();
		networks.setNameKey("inventory.networks");
		getHibernateTemplate().save(networks);
		
		InventoryCategory informationTypes = new InventoryCategory();
		informationTypes.setNameKey("inventory.informationTypes");
		InventoryCategory predefinedInformationTypes = new InventoryCategory();
		predefinedInformationTypes.setNameKey("inventory.informationTypes.predefined");
		predefinedInformationTypes.setCategory(informationTypes);
		
		getHibernateTemplate().saveOrUpdate(informationTypes);
		getHibernateTemplate().saveOrUpdate(predefinedInformationTypes);
		
		
		InventoryCategory users = new InventoryCategory();
		users.setNameKey("inventory.users");
		getHibernateTemplate().save(users);
	}

}
