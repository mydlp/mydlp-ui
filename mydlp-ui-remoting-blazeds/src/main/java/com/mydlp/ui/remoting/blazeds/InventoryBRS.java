package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.InventoryCategory;

public interface InventoryBRS {

	public List<InventoryCategory> getInventory();
	
	public InventoryBase save(InventoryBase item);

}