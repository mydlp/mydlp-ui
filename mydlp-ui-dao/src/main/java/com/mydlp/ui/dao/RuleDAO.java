package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.Rule;

public interface RuleDAO {

	public List<Rule> getRules();
	
	public Rule save(Rule r);
	
	public void remove(Rule r);
}
