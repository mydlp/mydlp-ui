package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.InventoryDAO;
import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.InventoryCategory;

@Service("inventoryBRS")
@RemotingDestination
public class InventoryBRSImpl implements InventoryService
{
	@Autowired
	protected InventoryDAO inventoryDAO;
	
	public InventoryDAO getInventoryDAO() {
		return inventoryDAO;
	}

	public void setInventoryDAO(InventoryDAO inventoryDAO) {
		this.inventoryDAO = inventoryDAO;
	}

	@Override
	public List<InventoryCategory> getInventory() {
		return getInventoryDAO().getInventory();
	}

	@Override
	public InventoryBase save(InventoryBase item) {
		return getInventoryDAO().save(item);
	}

	@Override
	public void remove(InventoryBase item) {
		inventoryDAO.remove(item);
	}

	@Override
	public void saveAll(List<InventoryBase> items) {
		for (InventoryBase item: items) {
			 getInventoryDAO().save(item);
		}
	}
}