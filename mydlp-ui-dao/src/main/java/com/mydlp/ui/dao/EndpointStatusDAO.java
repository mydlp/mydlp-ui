package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.EndpointStatus;

public interface EndpointStatusDAO {

	public List<EndpointStatus> getEndpointStatuses(String searchString, Integer offset, Integer limit);
	
	public Long getEndpointStatusCount(String searchString);
	
	public void upToDateEndpoint(String ipAddress);
	
	public void upToDateEndpoint(String ipAddress, String username);
	
	public void outOfDateAllEndpoints();
	
	public Long getEndpointOnlineCount(String searchString);
	
	public Long getEndpointOfflineCount(String searchString);
	
	public Long getEndpointNotUpToDateCount(String searchString);
	
	public Boolean truncateEndpointStatus();
	
}
