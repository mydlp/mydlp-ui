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
import com.mydlp.ui.dao.RDBMSConnectionDAO;
import com.mydlp.ui.dao.RegularExpressionGroupDAO;
import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.AuthUser;
import com.mydlp.ui.domain.DocumentDatabase;

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
	
	@Autowired
	protected RDBMSConnectionDAO rdbmsConnectionDAO;
	
	@Autowired
	protected UserService userService;

	public List<AbstractEntity> getObjects() {
		
		List<AbstractEntity> objects = new ArrayList<AbstractEntity>(); 
		
		AuthUser authUser = userService.getCurrentUser();
		
		if(authUser.hasRole(AuthSecurityRole.ROLE_ADMIN))
		{
			objects.addAll(dataFormatDAO.getDataFormats());
			objects.addAll(regexDAO.getRegularExpressionGroups());
			objects.addAll(adDomainDAO.getADDomains());
			objects.addAll(rdbmsConnectionDAO.getRDBMSConnections());
		}
		if(authUser.hasRole(AuthSecurityRole.ROLE_ADMIN) || authUser.hasRole(AuthSecurityRole.ROLE_CLASSIFIER))
			objects.addAll(documentDatabaseDAO.getDocumentDatabases());
		
		return objects;
	}
	
	public List<DocumentDatabase> getDocumentDatabases()
	{
		List<DocumentDatabase> documentDatabases = new ArrayList<DocumentDatabase>();
		AuthUser authUser = userService.getCurrentUser();
		if(authUser.hasRole(AuthSecurityRole.ROLE_ADMIN) || authUser.hasRole(AuthSecurityRole.ROLE_CLASSIFIER))
			documentDatabases.addAll(documentDatabaseDAO.getDocumentDatabases());
		return documentDatabases;
	}

}