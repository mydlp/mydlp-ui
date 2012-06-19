package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.Document;
import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.DocumentDatabaseFileEntry;
import com.mydlp.ui.domain.DocumentFingerprint;

public interface DocumentDatabaseDAO {
	
	public List<DocumentDatabase> getDocumentDatabases();
	
	public DocumentDatabase save(DocumentDatabase r);
	
	public DocumentDatabaseFileEntry saveFileEntry(DocumentDatabaseFileEntry f);
	
	public List<DocumentDatabase> getDocumentDatabasesWithRDBMS();
	
	public DocumentFingerprint saveFingerprint(DocumentFingerprint f);
	
	public DocumentDatabase getDocumentDatabaseById(Integer id);
	
	public void remove(DocumentDatabase r);
	
	public void removeDocument(Document d);
	
}
