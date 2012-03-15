package com.mydlp.ui.remoting.blazeds;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.ADDomainDAO;
import com.mydlp.ui.dao.DataFormatDAO;
import com.mydlp.ui.dao.DocumentDatabaseDAO;
import com.mydlp.ui.dao.InventoryDAO;
import com.mydlp.ui.dao.RegularExpressionGroupDAO;
import com.mydlp.ui.domain.AbstractEntity;

@Service("objectsBRS")
@RemotingDestination
public class ObjectsBRSImpl implements ObjectsService
{
	@Autowired
	protected InventoryDAO inventoryDAO;
	
	@Autowired
	protected DataFormatDAO dataFormatDAO;
	
	@Autowired
	protected RegularExpressionGroupDAO regexDAO;
	
	@Autowired
	protected DocumentDatabaseDAO documentDatabaseDAO;
	
	@Autowired
	protected ADDomainDAO adDomainDAO;

	public List<AbstractEntity> getObjects() {
		
		List<AbstractEntity> objects = new ArrayList<AbstractEntity>(); 
		
		objects.addAll(dataFormatDAO.getDataFormats());
		objects.addAll(inventoryDAO.getInventoryItems());
		objects.addAll(regexDAO.getRegularExpressionGroups());
		objects.addAll(documentDatabaseDAO.getDocumentDatabases());
		objects.addAll(adDomainDAO.getADDomains());
		
		return objects;
	}

}