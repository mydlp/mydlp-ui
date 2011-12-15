package com.mydlp.ui.remoting.blazeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.GenericDAO;
import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.Rule;

@Service("genericBRS")
@RemotingDestination
public class GenericBRSImpl implements GenericService
{
	@Autowired
	protected GenericDAO genericDAO;
	
	@Autowired
	protected InventoryService inventoryService;
	
	@Autowired
	protected RuleService ruleService;

	@Override
	public AbstractEntity save(AbstractEntity item) {
		// delegating to related services to inherit authorization meta.
		if (item instanceof InventoryBase)
			return inventoryService.save((InventoryBase) item);
		else if (item instanceof Rule)
			return ruleService.save((Rule) item);
		else
			return genericDAO.save(item);
	}

	@Override
	public void remove(AbstractEntity item) {
		// delegating to related services to inherit authorization meta.
		if (item instanceof InventoryBase)
			inventoryService.remove((InventoryBase) item);
		else if (item instanceof Rule)
			ruleService.remove((Rule) item);
		else
			genericDAO.remove(item);
	}
	
	
}