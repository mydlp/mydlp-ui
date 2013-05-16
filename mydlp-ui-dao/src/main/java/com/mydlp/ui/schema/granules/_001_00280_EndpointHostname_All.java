package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.EndpointHostname;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00280_EndpointHostname_All extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory inventory = DAOUtil.getSingleResult(list);
		
		InventoryCategory hostnames = new InventoryCategory();
		hostnames.setNameKey("inventory.endpointHostnames");
		hostnames.setEditable(false);
		hostnames.setCategory(inventory);
		
		EndpointHostname e = new EndpointHostname();
		e.setHostname("all");
		
		InventoryItem n1item = new InventoryItem();
		n1item.setNameKey("inventory.endpointHostname.all");
		n1item.setItem(e);
		
		n1item.setCategory(hostnames);
		
		getHibernateTemplate().saveOrUpdate(e);
		getHibernateTemplate().saveOrUpdate(hostnames);
		getHibernateTemplate().saveOrUpdate(n1item);
		
	}

}
