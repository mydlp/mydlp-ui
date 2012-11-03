package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.DocumentDatabase;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN})
public interface ObjectsService {

	@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN, AuthSecurityRole.ROLE_CLASSIFIER})
	public List<AbstractEntity> getObjects();
	
	@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN, AuthSecurityRole.ROLE_CLASSIFIER})
	public List<DocumentDatabase> getDocumentDatabases();
	
}