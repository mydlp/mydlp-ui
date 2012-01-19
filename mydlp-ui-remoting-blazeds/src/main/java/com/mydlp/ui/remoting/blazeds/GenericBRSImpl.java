package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static Logger logger = LoggerFactory.getLogger(GenericBRSImpl.class);
	
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

	@Override
	public void removeAll(List<AbstractEntity> items) {
		for (AbstractEntity abstractEntity : items) {
			if (abstractEntity.getId() != null)
				remove(abstractEntity);
			else
				logger.info("Recieved transient object ( Type: " +
						abstractEntity.getClass().getName()
						+ " ). Ignoring.");
		}
	}
	
	
}