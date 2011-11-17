package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.InventoryDAO;
import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.domain.Network;

@Service("inventoryBRS")
@RemotingDestination
public class InventoryBRSImpl implements InventoryBRS
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
		if (item != null && item instanceof InventoryItem) {
			System.out.println("viola 1");
			InventoryItem ii = (InventoryItem) item;
			if (ii.getItem() != null && ii.getItem() instanceof Network) {
				System.out.println("viola 2");
				Network n = (Network) ii.getItem();
				System.out.println("viola 3: " + n.getId());
				System.out.println("viola 3: " + n.getIpBase());
				System.out.println("viola 3: " + n.getIpMask());
			}
		}
		return getInventoryDAO().save(item);
	}
}