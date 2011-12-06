package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.InventoryBase;

public interface InventoryDAO {

	public List<InventoryBase> getInventory();
	
	public InventoryBase save(InventoryBase i);
	
	public void remove(InventoryBase i);
}
