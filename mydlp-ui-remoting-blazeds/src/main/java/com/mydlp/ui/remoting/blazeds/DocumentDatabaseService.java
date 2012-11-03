package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.Document;
import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.DocumentDatabaseFileEntry;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN, AuthSecurityRole.ROLE_CLASSIFIER})
public interface DocumentDatabaseService {

	public List<DocumentDatabase> getDocumentDatabases();
	
	public DocumentDatabase save(DocumentDatabase d);
	
	public DocumentDatabaseFileEntry generateDocumentDatabaseFileEntry(Integer databaseId, String filename, String md5sum, byte[] data);
	
	public void remove(DocumentDatabase r);
	
	public void removeDocument(Document d);

}