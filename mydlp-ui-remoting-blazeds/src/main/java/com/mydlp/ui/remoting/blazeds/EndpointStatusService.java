package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.Endpoint;
import com.mydlp.ui.domain.EndpointStatus;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN})
public interface EndpointStatusService {
	
	public List<EndpointStatus> getEndpointStatuses(String searchString, Integer offset, Integer limit);
	
	public Long getEndpointStatusCount(String searchString);
	
	public Long getEndpointOnlineCount(String searchString);
	
	public Long getEndpointOfflineCount(String searchString);
	
	public Long getEndpointNotUpToDateCount(String searchString);
	
	public Boolean truncateEndpointStatus();
	
	public List<Endpoint> getFilteredEndpoints(String searchString);
	
}