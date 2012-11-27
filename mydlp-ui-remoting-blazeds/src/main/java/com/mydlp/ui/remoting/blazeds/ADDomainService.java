package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.domain.ADDomainItem;
import com.mydlp.ui.domain.AuthSecurityRole;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN})
public interface ADDomainService {

	public void schedule(Integer domainId);
	
	public ADDomain save(ADDomain domain);
	
	public List<ADDomain> getADDomains();
	
	public List<ADDomainItem> getFilteredADDomains(String searchString);
	
	public String testConnection(ADDomain adDomain);
	
	public void remove(ADDomain adDomain);
	
}