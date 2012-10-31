package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00070_InventoryCategories3 extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory predefined = DAOUtil.getSingleResult(list);
		
		InventoryCategory destinations = new InventoryCategory();
		destinations.setNameKey("inventory.destinations");
		destinations.setEditable(false);
		destinations.setCategory(predefined);
		
		getHibernateTemplate().save(destinations);
		getHibernateTemplate().save(predefined);
	}

}
