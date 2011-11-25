package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.domain.Network;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00002_NetworkAll extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.networks"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory networks = DAOUtil.getSingleResult(list);
		
		Network n1 = new Network();
		n1.setIpBase(new Long(0));
		n1.setIpMask(new Long(0));
		
		InventoryItem n1item = new InventoryItem();
		n1item.setNameKey("inventory.networks.all");
		n1item.setItem(n1);
		
		n1item.setCategory(networks);
		
		getHibernateTemplate().saveOrUpdate(n1);
		getHibernateTemplate().saveOrUpdate(networks);
		getHibernateTemplate().saveOrUpdate(n1item);
		
	}

}
