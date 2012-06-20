package com.mydlp.ui.remoting.blazeds;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.DocumentDatabaseDAO;
import com.mydlp.ui.domain.Document;
import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.DocumentDatabaseFileEntry;
import com.mydlp.ui.thrift.MyDLPUIThriftService;


@Service("documentDatabaseBRS")
@RemotingDestination
public class DocumentDatabaseBRSImpl implements DocumentDatabaseService {

	@Autowired
	protected DocumentDatabaseDAO documentDatabaseDAO;
	
	@Autowired
	protected MyDLPUIThriftService thriftService;
	
	@Override
	public List<DocumentDatabase> getDocumentDatabases() {
		return documentDatabaseDAO.getDocumentDatabases();
	}

	@Override
	public DocumentDatabase save(DocumentDatabase d) {
		return documentDatabaseDAO.save(d);
	}

	@Override
	public DocumentDatabaseFileEntry generateDocumentDatabaseFileEntry(Integer databaseId, String filename,  String md5sum, byte[] data) {
		DocumentDatabase documentDatabase = documentDatabaseDAO.getDocumentDatabaseById(databaseId);
		
		DocumentDatabaseFileEntry documentDatabaseFileEntry = new DocumentDatabaseFileEntry();
		documentDatabaseFileEntry.setCreatedDate(new Date());
		documentDatabaseFileEntry.setFilename(filename);
		documentDatabaseFileEntry.setMd5Hash(md5sum);
		
		if (documentDatabase.getFileEntries() == null)
			documentDatabase.setFileEntries(new ArrayList<DocumentDatabaseFileEntry>());
		
		documentDatabaseFileEntry = documentDatabaseDAO.saveFileEntry(documentDatabaseFileEntry);
		documentDatabase.getFileEntries().add(documentDatabaseFileEntry);
		documentDatabaseDAO.save(documentDatabase);
		
		thriftService.generateFingerprints(documentDatabaseFileEntry.getId().longValue(), filename,ByteBuffer.wrap(data));
		
		return documentDatabaseFileEntry;
	}

	@Override
	public void remove(DocumentDatabase r) {
		documentDatabaseDAO.remove(r);
	}

	@Override
	public void removeDocument(Document d) {
		documentDatabaseDAO.removeDocument(d);
	}
	
}
