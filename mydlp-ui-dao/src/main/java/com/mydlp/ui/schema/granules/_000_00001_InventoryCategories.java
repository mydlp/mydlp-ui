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
		
		InventoryCategory userDefined = new InventoryCategory();
		userDefined.setNameKey("inventory.userDefined");
		userDefined.setEditable(false);
		getHibernateTemplate().save(userDefined);
	}

}
