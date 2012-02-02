package com.mydlp.ui.remoting.blazeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.RemoteFileUploadDAO;


@Service("remoteFileUploadBRS")
@RemotingDestination
public class RemoteFileUploadBRSImpl implements RemoteFileUploadService{

	@Autowired
	protected RemoteFileUploadDAO remoteFileUploadDao;
	
	@Override
	public String doRemoteUpload(byte[] bytes, String fileName) {
		try {
			return remoteFileUploadDao.doUpload(bytes, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

}
