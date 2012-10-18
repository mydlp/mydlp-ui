package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.Destination;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00080_DestinationAll extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.destinations"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory destinations = DAOUtil.getSingleResult(list);
		
		Destination d1 = new Destination();
		d1.setDestinationString("all");
		
		InventoryItem n1item = new InventoryItem();
		n1item.setNameKey("inventory.destination.all");
		n1item.setItem(d1);
		
		n1item.setCategory(destinations);
		
		getHibernateTemplate().saveOrUpdate(d1);
		getHibernateTemplate().saveOrUpdate(destinations);
		getHibernateTemplate().saveOrUpdate(n1item);
		
	}

}
