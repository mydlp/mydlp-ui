package com.mydlp.ui.service;

import java.util.Map;

public interface EndpointSyncService {

	public void asyncRegisterEndpointMeta(Map<String,String> endpointMeta, String endpointId, String ipAddress, String usernameHash);
	
}
