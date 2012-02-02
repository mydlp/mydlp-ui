package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.InventoryDAO;
import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.service.SolrService;

@Service("inventoryBRS")
@RemotingDestination
public class InventoryBRSImpl implements InventoryService
{
	@Autowired
	protected InventoryDAO inventoryDAO;
	
	@Autowired
	protected SolrService solrService;

	@Override
	public List<InventoryBase> getInventory() {
		return inventoryDAO.getInventory();
	}

	@Override
	public InventoryBase save(InventoryBase item) {
		return inventoryDAO.save(item);
	}

	@Override
	public void remove(InventoryBase item) {
		inventoryDAO.remove(item);
	}

	@Override
	public void saveAll(List<InventoryBase> items) {
		for (InventoryBase item: items) {
			 inventoryDAO.save(item);
		}
	}
}