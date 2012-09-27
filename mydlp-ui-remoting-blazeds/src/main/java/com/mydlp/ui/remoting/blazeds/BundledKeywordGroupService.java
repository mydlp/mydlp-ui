package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.BundledKeywordGroup;

@Secured(AuthSecurityRole.ROLE_ADMIN)
public interface BundledKeywordGroupService {

	public List<BundledKeywordGroup> getBundledKeywordGroups();
}