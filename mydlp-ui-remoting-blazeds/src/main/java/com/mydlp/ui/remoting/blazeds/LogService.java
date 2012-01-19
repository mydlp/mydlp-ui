package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.IncidentLog;

@Secured(AuthSecurityRole.ROLE_USER)
public interface LogService {

	public List<IncidentLog> getLogs();
	
	public List<IncidentLog> getLogs(List<List<Object>> criteriaList, Integer offset, Integer count);
	
	public Long getLogCount(List<List<Object>> criteriaList);
	
}