package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.DiscoveryReport;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN,AuthSecurityRole.ROLE_AUDITOR})
public interface DiscoveryReportService {

	public List<DiscoveryReport> getDiscoveryReports(List<List<Object>> criteriaList, Integer offset, Integer count);
	
	public Long getDiscoveryReportCount(List<List<Object>> criteriaList);
	
	public DiscoveryReport save(DiscoveryReport discoveryReport);
	
	public void remove(DiscoveryReport discoveryReport);
	
}