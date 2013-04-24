package com.mydlp.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.DocumentDatabaseDAO;
import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("remoteFingerprintingService")
public class DocumentDatabaseRemoteStorageFingerprintingServiceImpl implements DocumentDatabaseRemoteFingerPrintingService {

	
	@Autowired
	protected DocumentDatabaseDAO documentDatabaseDAO;
	
	@Autowired
	protected MyDLPUIThriftService thriftService;
	
	@Override
	public void startRemoteFingerPrinting(int id) {
		thriftService.startFingerprinting(id);
	}

	@Scheduled(cron="0 0 4 * * ?")
	public void dailyFingerprinting()
	{
		for (DocumentDatabase dd: documentDatabaseDAO.getDocumentDatabases())
			if (dd.getId() != null)
				startRemoteFingerPrinting(dd.getId());
	}
	
}
