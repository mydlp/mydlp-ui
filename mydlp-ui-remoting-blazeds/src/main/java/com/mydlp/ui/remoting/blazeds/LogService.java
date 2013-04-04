package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.IncidentLog;
import com.mydlp.ui.domain.IncidentLogFileContent;
import com.mydlp.ui.domain.OperationLog;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN,AuthSecurityRole.ROLE_AUDITOR})
public interface LogService {

	public List<IncidentLog> getLogs(List<List<Object>> criteriaList, Integer offset, Integer count);
	
	public Long getLogCount(List<List<Object>> criteriaList);
	
	public List<OperationLog> getDiscoveryOperationLogs(List<List<Object>> criteriaList, Integer offset, Integer count);
	
	public Long getDiscoveryOperationLogCount(List<List<Object>> criteriaList);
	
	public List<IncidentLogFileContent> getLogContents(String searchStr, Integer offset, Integer count);
	
	public Long getLogContentCount(String searchStr);
	
	public List<String> getFilenamesForContent(Integer id);
	
	public void requeueIncident(IncidentLog log);
	
	public String exportExcel(List<List<Object>> criteriaList);
	
}