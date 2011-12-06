package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.Rule;

@Secured(AuthSecurityRole.ROLE_USER)
public interface RuleService {

	public List<Rule> getRules();
	
	public Rule save(Rule rule);

	public void remove(Rule rule);
}