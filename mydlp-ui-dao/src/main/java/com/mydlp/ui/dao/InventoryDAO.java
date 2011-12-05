package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.InventoryCategory;

public interface InventoryDAO {

	public List<InventoryCategory> getInventory();
	
	public InventoryBase save(InventoryBase i);
	
	public void remove(InventoryBase i);
}
