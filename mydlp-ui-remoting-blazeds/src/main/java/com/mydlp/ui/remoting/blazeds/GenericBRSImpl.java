package com.mydlp.ui.remoting.blazeds;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.GenericDAO;
import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.DashboardItem;
import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.RDBMSConnection;
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
	
	@Autowired
	protected ADDomainService adDomainService;
	
	@Autowired
	protected DashboardService dashboardService;
	
	@Autowired
	protected RDBMSConnectionService rdbmsConnectionService;

	@Override
	public AbstractEntity save(AbstractEntity item) {
		// delegating to related services to inherit authorization meta.
		if (item instanceof InventoryBase)
			return inventoryService.save((InventoryBase) item);
		else if (item instanceof Rule)
			return ruleService.save((Rule) item);
		else if (item instanceof ADDomain)
			return adDomainService.save((ADDomain) item);
		else if (item instanceof DashboardItem)
			return dashboardService.saveDashboardItem((DashboardItem) item);
		else if (item instanceof RDBMSConnection)
			return rdbmsConnectionService.save(item);
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
		else if (item instanceof DashboardItem)
			dashboardService.remove((DashboardItem) item);
		else if (item instanceof RDBMSConnection)
			rdbmsConnectionService.remove(item);
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

	@Override
	public void sync() {}

	@Override
	public Date getSystemTime() {
		return new Date();
	}
	
	
	
}