package com.mydlp.ui.remoting.blazeds;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.domain.AuthSecurityRole;

@Secured(AuthSecurityRole.ROLE_ADMIN)
public interface ADDomainService {

	public void enumerate(ADDomain domain);
	
	public ADDomain save(ADDomain domain);
	
}