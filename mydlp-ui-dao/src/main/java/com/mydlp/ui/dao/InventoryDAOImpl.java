package com.mydlp.ui.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.InventoryCategory;

@Repository("inventoryDAO")
@Transactional
public class InventoryDAOImpl extends AbstractDAO implements InventoryDAO {

	@SuppressWarnings("unchecked")
	public List<InventoryCategory> getInventory() {
		return getHibernateTemplate().find(
			"from InventoryCategory i where i.category is null"
		);
	}
	
	@Transactional(readOnly=false)
	public void save(InventoryBase i ) {
		getHibernateTemplate().save(i);
	}
}
