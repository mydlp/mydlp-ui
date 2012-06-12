package com.mydlp.ui.remoting.blazeds;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.LicenseInformation;

@Secured(AuthSecurityRole.ROLE_ADMIN)
public interface LicenseInformationService {

	@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_AUDITOR, AuthSecurityRole.ROLE_CLASSIFIER})
	public LicenseInformation getLicense();
	
	public Boolean saveLicenseKey(String s);
	
}
