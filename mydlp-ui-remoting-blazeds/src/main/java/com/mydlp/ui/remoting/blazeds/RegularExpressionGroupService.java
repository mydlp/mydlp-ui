package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.RegularExpressionGroup;

@Secured(AuthSecurityRole.ROLE_USER)
public interface RegularExpressionGroupService {

	public List<RegularExpressionGroup> getRegularExpressionGroups();
	
	public RegularExpressionGroup save(RegularExpressionGroup d);

}