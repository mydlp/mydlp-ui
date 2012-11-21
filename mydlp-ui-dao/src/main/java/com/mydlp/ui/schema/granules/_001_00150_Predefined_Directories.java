package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.Domain;
import com.mydlp.ui.domain.FileSystemDirectory;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00150_Predefined_Directories extends AbstractGranule {

	protected void generateDirectories(InventoryCategory inventoryCategory, String appName, String nameKey)
	{
		FileSystemDirectory a = new FileSystemDirectory();
		a.setDestinationString(appName);
		InventoryItem ii = new InventoryItem();
		ii.setNameKey(nameKey);
		ii.setItem(a);
		ii.setCategory(inventoryCategory);
		
		getHibernateTemplate().saveOrUpdate(a);
		getHibernateTemplate().saveOrUpdate(ii);
	}
	
	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.destinations"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory destinations = DAOUtil.getSingleResult(list);
		
		InventoryCategory directories = new InventoryCategory();
		directories.setNameKey("inventory.destinations.directories");
		directories.setEditable(false);
		directories.setCategory(destinations);
		
		generateDirectories(directories, "C:/Users", "inventory.destinations.directories.users");
		generateDirectories(directories, "C:/Documents and Settings", "inventory.destinations.directories.documentsAndSettings");
		
		
		getHibernateTemplate().saveOrUpdate(directories);
		getHibernateTemplate().saveOrUpdate(destinations);
	}

}
