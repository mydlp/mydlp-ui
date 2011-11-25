package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00001_InventoryCategories extends AbstractGranule {

	@Override
	protected void callback() {
		InventoryCategory networks = new InventoryCategory();
		networks.setNameKey("inventory.networks");
		getHibernateTemplate().save(networks);
		
		InventoryCategory documentTypes = new InventoryCategory();
		documentTypes.setNameKey("inventory.documentTypes");
		InventoryCategory predefinedDocumentTypes = new InventoryCategory();
		predefinedDocumentTypes.setNameKey("inventory.documentTypes.predefined");
		predefinedDocumentTypes.setCategory(documentTypes);
		
		getHibernateTemplate().saveOrUpdate(documentTypes);
		getHibernateTemplate().saveOrUpdate(predefinedDocumentTypes);
		
		
		InventoryCategory users = new InventoryCategory();
		users.setNameKey("inventory.users");
		getHibernateTemplate().save(users);
	}

}
