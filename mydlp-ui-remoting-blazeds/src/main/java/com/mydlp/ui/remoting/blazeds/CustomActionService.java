package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.CustomAction;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN})
public interface CustomActionService {

	public List<CustomAction> getCustomActions(String searchStr);
	
	public List<CustomAction> getCustomActions();
	
}