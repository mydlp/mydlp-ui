package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.EndpointStatus;

public interface EndpointStatusDAO {

	public List<EndpointStatus> getEndpointStatuses(Integer offset, Integer limit);
	
	public Long getEndpointStatusCount();
	
	public void upToDateEndpoint(String ipAddress);
	
	public void outOfDateAllEndpoints();
}
