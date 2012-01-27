package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.DocumentDatabaseDAO;
import com.mydlp.ui.domain.DocumentDatabase;


@Service("documentDatabaseBRS")
@RemotingDestination
public class DocumentDatabaseBRSImpl implements DocumentDatabaseService {

	@Autowired
	protected DocumentDatabaseDAO documentDatabaseDAO;

	@Override
	public List<DocumentDatabase> getDocumentDatabases() {
		return documentDatabaseDAO.getDocumentDatabases();
	}

	@Override
	public DocumentDatabase save(DocumentDatabase d) {
		return documentDatabaseDAO.save(d);
	}
	
}
