package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.InventoryItem;

@Repository("inventoryDAO")
@Transactional
public class InventoryDAOImpl extends AbstractPolicyDAO implements InventoryDAO {

	@SuppressWarnings("unchecked")
	public List<InventoryBase> getInventory() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryBase.class)
					.add(Restrictions.isNull("category"));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	@Transactional(readOnly=false)
	public InventoryBase save(InventoryBase i) {
		getHibernateTemplate().saveOrUpdate(i);
		return i;
	}

	@Override
	@Transactional(readOnly=false)
	public void remove(InventoryBase i) {
		getHibernateTemplate().delete(i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InventoryItem> getInventoryItems() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryItem.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
}
