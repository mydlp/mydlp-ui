package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.EndpointStatus;

@Secured(AuthSecurityRole.ROLE_ADMIN)
public interface EndpointStatusService {
	
	public List<EndpointStatus> getEndpointStatuses(Integer offset, Integer limit);
	
	public Long getEndpointStatusCount();
	
}