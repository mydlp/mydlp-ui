package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.RemoteStorageDAO;
import com.mydlp.ui.domain.RemoteStorage;

@Service("remoteStorageBRS")
@RemotingDestination
public class RemoteStorageBRSImpl implements RemoteStorageService
{
	
	@Autowired
	protected RemoteStorageDAO remoteStorageDAO;

	@Override
	public List<RemoteStorage> getRemoteStorages() {
		return remoteStorageDAO.getRemoteStorages();
	}
	

}