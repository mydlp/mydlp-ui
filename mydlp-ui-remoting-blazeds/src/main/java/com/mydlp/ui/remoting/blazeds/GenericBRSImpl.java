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
import com.mydlp.ui.domain.AbstractNamedEntity;
import com.mydlp.ui.domain.DashboardItem;
import com.mydlp.ui.domain.DiscoveryRule;
import com.mydlp.ui.domain.Document;
import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.RDBMSConnection;
import com.mydlp.ui.domain.RDBMSInformationTarget;
import com.mydlp.ui.domain.RegularExpressionGroup;
import com.mydlp.ui.domain.RemoteStorageRule;
import com.mydlp.ui.domain.Rule;
import com.mydlp.ui.service.VersionService;
import com.mydlp.ui.thrift.MyDLPUIThriftService;

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
	
	@Autowired
	protected DocumentDatabaseService documentDatabaseService;
	
	@Autowired
	protected RegularExpressionGroupService regularExpressionGroupService;
	
	@Autowired
	protected VersionService versionService;
	
	@Autowired
	protected MyDLPUIThriftService thriftService;

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
		else if (item instanceof RDBMSInformationTarget)
			return rdbmsConnectionService.save(item);
		else if (item instanceof DocumentDatabase)
			return documentDatabaseService.save((DocumentDatabase) item);
		else
			return genericDAO.save(item);
	}

	@Override
	public void remove(AbstractEntity item) {
		// delegating to related services to inherit authorization meta.
		if (item instanceof InventoryBase)
			inventoryService.remove((InventoryBase) item);
		else if (item instanceof Rule)
		{
			if(item instanceof DiscoveryRule || item instanceof RemoteStorageRule)
				thriftService.stopReportBeforeRemoveRule(item.getId());
			ruleService.remove((Rule) item);
		}
		else if (item instanceof DashboardItem)
			dashboardService.remove((DashboardItem) item);
		else if (item instanceof ADDomain)
			adDomainService.remove((ADDomain) item);
		else if (item instanceof RDBMSConnection)
			rdbmsConnectionService.remove(item);
		else if (item instanceof RDBMSInformationTarget)
			rdbmsConnectionService.remove(item);
		else if (item instanceof DocumentDatabase)
			documentDatabaseService.remove((DocumentDatabase) item);
		else if (item instanceof Document)
			documentDatabaseService.removeDocument((Document) item);
		else if (item instanceof RegularExpressionGroup)
			regularExpressionGroupService.remove((RegularExpressionGroup) item);
		else
			genericDAO.remove(item);
	}

	@Override
	public void removeAll(List<AbstractEntity> items) {
		if (items == null)
			return;
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

	@Override
	public void remove(String entityName, Integer id) {
		entityName = entityName.replace("::", ".");
		genericDAO.remove(entityName, id);
	}

	@Override
	public String persistChange(AbstractEntity itemToSave,
			List<AbstractEntity> itemsToRemove) {
		
		if (itemToSave instanceof AbstractNamedEntity) {
			AbstractNamedEntity namedEntity = (AbstractNamedEntity) itemToSave;
			String enteredName = namedEntity.getName();
			if (enteredName == null)
				return "error.nameIsNull";
			String trimmedName = enteredName.trim();
			if (trimmedName.length() == 0)
				return "error.nameIsEmpty";
			if (trimmedName.length() < 3)
				return "error.nameIsTooShort";
			namedEntity.setName(trimmedName);
		}
		
		if (itemToSave instanceof AbstractNamedEntity) {
			AbstractNamedEntity namedEntity = (AbstractNamedEntity) itemToSave;
			Boolean isObjectWithNameExists = genericDAO.isObjectWithNameExists(namedEntity);
			if (isObjectWithNameExists)
				return "error.anObjectWithSameNameIsPresent"; 
		}
		
		save(itemToSave);
		removeAll(itemsToRemove);
		
		return "ok";
	}

	@Override
	public String getVersion() {
		return versionService.getVersion();
	}

	@Override
	public String getWindowsAgentVersion() {
		return versionService.getWindowsAgentVersion();
	}
	
	
	
}