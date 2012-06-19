package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.DocumentDatabase;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_CLASSIFIER})
public interface DocumentDatabaseService {

	public List<DocumentDatabase> getDocumentDatabases();
	
	public DocumentDatabase save(DocumentDatabase d);
	
	public void generateFingerprints(Integer documentId, String filename, byte[] data);

}