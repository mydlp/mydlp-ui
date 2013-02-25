package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.Domain;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00200_DestinationBCC extends AbstractGranule {
	
	protected void generateDomains(InventoryCategory inventoryCategory, String appName, String nameKey)
	{
		Domain a = new Domain();
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
		
		InventoryCategory domains = new InventoryCategory();
		domains.setNameKey("inventory.destinations.mailspecific");
		domains.setEditable(false);
		domains.setCategory(destinations);
		
		generateDomains(domains, "hasBCC", "inventory.mail.hasBcc");
		
		
		getHibernateTemplate().saveOrUpdate(domains);
		getHibernateTemplate().saveOrUpdate(destinations);
	}
	
}
