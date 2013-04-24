package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.DocumentDatabaseDAO;
import com.mydlp.ui.dao.RemoteStorageDAO;
import com.mydlp.ui.domain.RemoteStorage;
import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("remoteStorageBRS")
@RemotingDestination
public class RemoteStorageBRSImpl implements RemoteStorageService
{
	
	@Autowired
	protected RemoteStorageDAO remoteStorageDAO;
	
	@Autowired
	protected DocumentDatabaseDAO documentDatabaseDAO;
	
	@Autowired
	protected MyDLPUIThriftService thriftService;

	@Override
	public List<RemoteStorage> getRemoteStorages() {
		return remoteStorageDAO.getRemoteStorages();
	}

	@Override
	public List<String> getRemoteStorageDir(int id) {
		return thriftService.getRemoteStorageDir(id);
	}

	@Override
	public void startRemoteFingerprint(int id) {
		thriftService.startFingerprinting(id);
	}
	
}