package com.mydlp.ui.remoting.blazeds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.DocumentDatabaseDAO;
import com.mydlp.ui.dao.RemoteStorageDAO;
import com.mydlp.ui.domain.RemoteStorage;
import com.mydlp.ui.domain.RemoteStorageCIFS;
import com.mydlp.ui.domain.RemoteStorageDFS;
import com.mydlp.ui.domain.RemoteStorageFTPFS;
import com.mydlp.ui.domain.RemoteStorageNFS;
import com.mydlp.ui.domain.RemoteStorageSSHFS;
import com.mydlp.ui.domain.WebServer;
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

	@Override
	public String testConnection(RemoteStorage remoteStorage) {
		Map<String, String> rs = new HashMap<String, String>();
		if(remoteStorage instanceof RemoteStorageSSHFS)
		{
			RemoteStorageSSHFS temp = (RemoteStorageSSHFS)remoteStorage;
			rs.put("type", "sshfs");
			rs.put("address", temp.getAddress());
			rs.put("port", Integer.toString(temp.getPort()));
			rs.put("path", temp.getPath());
			rs.put("username", temp.getUsername());
			rs.put("password", temp.getPassword());
		}
		else if(remoteStorage instanceof RemoteStorageFTPFS)
		{
			RemoteStorageFTPFS temp = (RemoteStorageFTPFS)remoteStorage;
			rs.put("type", "ftpfs");
			rs.put("address", temp.getAddress());
			rs.put("path", temp.getPath());
			rs.put("username", temp.getUsername());
			rs.put("password", temp.getPassword());
		}
		else if(remoteStorage instanceof RemoteStorageCIFS)
		{
			RemoteStorageCIFS temp = (RemoteStorageCIFS)remoteStorage;
			rs.put("type", "cifs");
			rs.put("address", temp.getWindowsShare());
			rs.put("path", temp.getPath());
			rs.put("username", temp.getUsername());
			rs.put("password", temp.getPassword());
		}
		else if(remoteStorage instanceof RemoteStorageDFS)
		{
			RemoteStorageDFS temp = (RemoteStorageDFS)remoteStorage;
			rs.put("type", "dfs");
			rs.put("address", temp.getWindowsShare());
			rs.put("path", temp.getPath());
			rs.put("username", temp.getUsername());
			rs.put("password", temp.getPassword());
		}
		else if(remoteStorage instanceof RemoteStorageNFS)
		{
			RemoteStorageNFS temp = (RemoteStorageNFS)remoteStorage;
			rs.put("type", "nfs");
			rs.put("address", temp.getAddress());
			rs.put("path", temp.getPath());
		}
		return thriftService.testConnection(rs);
	}

	@Override
	public String testWebServer(WebServer webServer) {
		String url = webServer.getProto() + "://" + webServer.getAddress() + ":" + Integer.toString(webServer.getPort()) + "/" + webServer.getStartPath();
		return thriftService.testWebServer(url);	
	}
	
}