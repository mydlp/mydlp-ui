package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.RegularExpressionGroup;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN})
public interface RegularExpressionGroupService {

	public List<RegularExpressionGroup> getRegularExpressions();
	
	public RegularExpressionGroup save(RegularExpressionGroup r);
	
	public void remove(RegularExpressionGroup r);

}