package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.IncidentLog;
import com.mydlp.ui.domain.IncidentLogFileContent;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_AUDITOR})
public interface LogService {

	public List<IncidentLog> getLogs();
	
	public List<IncidentLog> getLogs(List<List<Object>> criteriaList, Integer offset, Integer count);
	
	public Long getLogCount(List<List<Object>> criteriaList);
	
	public List<IncidentLogFileContent> getLogContents(String searchStr, Integer offset, Integer count);
	
	public Long getLogContentCount(String searchStr);
	
	public List<String> getFilenamesForContent(Integer id);
	
}