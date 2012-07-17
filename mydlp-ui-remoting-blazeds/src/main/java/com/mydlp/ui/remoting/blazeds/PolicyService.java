package com.mydlp.ui.remoting.blazeds;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_CLASSIFIER})
public interface PolicyService {

	public void compilePolicy(Integer policyId);

}