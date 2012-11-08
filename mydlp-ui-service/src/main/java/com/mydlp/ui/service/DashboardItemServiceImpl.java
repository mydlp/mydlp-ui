package com.mydlp.ui.service;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.mydlp.ui.dao.IncidentLogDAO;
import com.mydlp.ui.dao.UserSettingsDAO;
import com.mydlp.ui.domain.DashboardItem;


@Service("dashboardItemService")
public class DashboardItemServiceImpl implements DashboardItemService {

	private static Logger logger = LoggerFactory.getLogger(DashboardItemServiceImpl.class);
	
	@Autowired
	protected UserSettingsDAO userSettingsDAO;
	
	protected Map<String, Object> itemCache = null;
	protected Map<String, Date> itemAge = null;
	
	protected BlockingQueue<String> generationRequests = null;
	
	@Autowired
	@Qualifier("logTransactionTemplate")
	protected TransactionTemplate transactionTemplate;
	
	@PostConstruct
	protected void init() {
		itemCache = new TreeMap<String, Object>();
		itemAge = new TreeMap<String, Date>();
		generationRequests = new LinkedBlockingQueue<String>();
		registerAll();
	}
	
	@Scheduled(cron="0 0/30 * * * ?")
	public void registerAllJob() {
		registerAll();
	}
	
	protected void registerAll() {
		for (String itemKey: userSettingsDAO.getDashboardItems()) 
			register(itemKey);
		consume();
	}
	
	@Async
	public void registerForGeneration(String itemKey) {
		register(itemKey);
		consume();
	}
	
	protected void register(String itemKey) {
		try {
			if (!generationRequests.contains(itemKey))
				generationRequests.put(itemKey);
		} catch (InterruptedException e) {
			logger.error("Error when pushing item to queue",e);
		}
	}
	
	@Scheduled(fixedDelay=60000)
	public void consumeJob() {
		consume();
	}
	
	protected Boolean isItemOld(String itemKey) {
		if (!itemAge.containsKey(itemKey)) return true;
		
		Date now = new Date();
		Date birth = itemAge.get(itemKey);
		
		return now.getTime() - birth.getTime() > 0.5 * 60L * 60L * 1000L; // half an hour
	}
	
	protected void consume() {
		if (generationRequests.size() == 0) return;
		
		String itemKey = null;
		while((itemKey = generationRequests.poll()) != null)
		{
			if (!itemCache.containsKey(itemKey) || isItemOld(itemKey)) {
				final String finalItemKey = itemKey;
				Object result = transactionTemplate.execute(new TransactionCallback<Object>() {
					@Override
					public Object doInTransaction(TransactionStatus arg0) {
						return generate(finalItemKey);
					}
				});
				if (result != null)
				{
					itemCache.put(itemKey, result);
					itemAge.put(itemKey, new Date());
				}
			}
		}
	}
	
	@Override
	public Object get(String itemKey) {
		Object ret = itemCache.get(itemKey);
		if (ret == null)
			registerForGeneration(itemKey);
		return ret;
	}
	
	@Autowired
	protected IncidentLogDAO incidentLogDAO;
	
	protected Object generate(String itemKey) {
		
		if (itemKey.equals(DashboardItem.INCIDENTS_BY_PROTOCOLS_1H)) {
			return incidentLogDAO.getProtocolIncidentCount(1);
		} else if (itemKey.equals(DashboardItem.INCIDENTS_BY_PROTOCOLS_24H)) {
			return incidentLogDAO.getProtocolIncidentCount(24);
		} else if (itemKey.equals(DashboardItem.INCIDENT_BY_ACTIONS_1H)) {
			return incidentLogDAO.getActionIncidentCount(1);
		} else if (itemKey.equals(DashboardItem.INCIDENT_BY_ACTIONS_24H)) {
			return incidentLogDAO.getActionIncidentCount(24);
		} else if (itemKey.equals(DashboardItem.TOP_5_ADDRESS_1H)) {
			return incidentLogDAO.topSourceAddress(1,5);
		} else if (itemKey.equals(DashboardItem.TOP_5_ADDRESS_24H)) {
			return incidentLogDAO.topSourceAddress(24,5);
		} else if (itemKey.equals(DashboardItem.TOP_5_USERS_1H)) {
			return incidentLogDAO.topSourceUser(1,5);
		} else if (itemKey.equals(DashboardItem.TOP_5_USERS_24H)) {
			return incidentLogDAO.topSourceUser(24,5);
		} else if (itemKey.equals(DashboardItem.TOP_5_RULES_1H)) {
			return incidentLogDAO.topRuleId(1,5);
		} else if (itemKey.equals(DashboardItem.TOP_5_RULES_24H)) {
			return incidentLogDAO.topRuleId(24,5);
		} else if (itemKey.equals(DashboardItem.TOP_5_ITYPES_1H)) {
			return incidentLogDAO.topInformationTypeId(1,5);
		} else if (itemKey.equals(DashboardItem.TOP_5_ITYPES_24H)) {
			return incidentLogDAO.topInformationTypeId(24,5);
		} else if (itemKey.equals(DashboardItem.INCIDENTS_BY_PROTOCOL_1W)) {
			return incidentLogDAO.getProtocolIncidentCount(24*7);
		} else if (itemKey.equals(DashboardItem.INCIDENTS_BY_ACTIONS_1W)) {
			return incidentLogDAO.getActionIncidentCount(24*7);
		} else if (itemKey.equals(DashboardItem.TOP_5_ADDRESS_1W)) {
			return incidentLogDAO.topSourceAddress(24*7, 5);
		} else if (itemKey.equals(DashboardItem.TOP_5_RULES_1W)) {
			return incidentLogDAO.topRuleId(24*7, 5);
		} else if (itemKey.equals(DashboardItem.TOP_5_USERS_1W)) {
			return incidentLogDAO.topSourceUser(24*7, 5);
		} else if (itemKey.equals(DashboardItem.TOP_5_ITYPES_1W)) {
			return incidentLogDAO.topInformationTypeId(24*7, 5);
		}
		
		return null;
	}
	
}
