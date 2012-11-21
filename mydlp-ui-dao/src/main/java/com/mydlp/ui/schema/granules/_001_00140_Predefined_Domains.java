package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.Domain;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00140_Predefined_Domains extends AbstractGranule {

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
		domains.setNameKey("inventory.destinations.domains");
		domains.setEditable(false);
		domains.setCategory(destinations);
		
		generateDomains(domains, "hotmail.com", "inventory.destinations.domains.hotmail");
		generateDomains(domains, "outlook.com", "inventory.destinations.domains.outlook");
		generateDomains(domains, "live.com", "inventory.destinations.domains.live");
		generateDomains(domains, "msn.com", "inventory.destinations.domains.msn");
		generateDomains(domains, "gmail.com", "inventory.destinations.domains.gmail");
		generateDomains(domains, "google.com", "inventory.destinations.domains.google");
		generateDomains(domains, "yahoo.com", "inventory.destinations.domains.yahoo");
		generateDomains(domains, "yandex.ru", "inventory.destinations.domains.yandex1");
		generateDomains(domains, "yandex.com", "inventory.destinations.domains.yandex2");
		generateDomains(domains, "facebook.com", "inventory.destinations.domains.facebook");
		generateDomains(domains, "microsoft.com", "inventory.destinations.domains.microsoft");
		
		
		getHibernateTemplate().saveOrUpdate(domains);
		getHibernateTemplate().saveOrUpdate(destinations);
	}

}
