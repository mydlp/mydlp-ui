package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.DocumentFingerprint;

public interface DocumentDatabaseDAO {
	
	public List<DocumentDatabase> getDocumentDatabases();
	
	public DocumentDatabase save(DocumentDatabase r);
	
	public List<DocumentDatabase> getDocumentDatabasesWithRDBMS();
	
	public DocumentFingerprint saveFingerprint(DocumentFingerprint f);
	
}
