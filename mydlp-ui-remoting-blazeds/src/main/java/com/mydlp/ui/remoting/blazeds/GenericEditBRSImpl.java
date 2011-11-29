package com.mydlp.ui.remoting.blazeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.GenericDAO;
import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.InventoryBase;

@Service("genericBRS")
@RemotingDestination
public class GenericEditBRSImpl implements GenericEditService
{
	@Autowired
	protected GenericDAO genericDAO;
	
	@Autowired
	protected InventoryService inventoryService;

	@Override
	public AbstractEntity save(AbstractEntity item) {
		// delegating to related services to inherit authorization meta.
		if (item instanceof InventoryBase)
			return inventoryService.save((InventoryBase) item);

		return genericDAO.save(item);
	}
	
	
}