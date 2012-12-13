package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.domain.SourceDomainName;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00170_SourceDomainAll extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.networks"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory sources = DAOUtil.getSingleResult(list);
		
		SourceDomainName d1 = new SourceDomainName();
		d1.setSourceDomain("all");
		
		InventoryItem n1item = new InventoryItem();
		n1item.setNameKey("inventory.sourceDomain.all");
		n1item.setItem(d1);
		
		n1item.setCategory(sources);
		
		getHibernateTemplate().saveOrUpdate(d1);
		getHibernateTemplate().saveOrUpdate(sources);
		getHibernateTemplate().saveOrUpdate(n1item);
		
	}

}
