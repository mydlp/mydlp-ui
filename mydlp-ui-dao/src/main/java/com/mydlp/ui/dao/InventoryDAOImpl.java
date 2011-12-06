package com.mydlp.ui.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.InventoryBase;

@Repository("inventoryDAO")
@Transactional
public class InventoryDAOImpl extends AbstractDAO implements InventoryDAO {

	@SuppressWarnings("unchecked")
	public List<InventoryBase> getInventory() {
		return getHibernateTemplate().find(
			"from InventoryBase i where i.category is null"
		);
	}

	@Override
	public InventoryBase save(InventoryBase i) {
		getHibernateTemplate().saveOrUpdate(i);
		return i;
	}

	@Override
	public void remove(InventoryBase i) {
		getHibernateTemplate().delete(i);
	}
	
}
