package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.DocumentDatabase;

public interface DocumentDatabaseDAO {
	
	public List<DocumentDatabase> getDocumentDatabases();
	
	public DocumentDatabase save(DocumentDatabase r);
	
}
