package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.InventoryItem;

public interface InventoryDAO {

	public List<InventoryBase> getInventory();
	
	public List<InventoryItem> getInventoryItems();
	
	public InventoryBase save(InventoryBase i);
	
	public void remove(InventoryBase i);
}
