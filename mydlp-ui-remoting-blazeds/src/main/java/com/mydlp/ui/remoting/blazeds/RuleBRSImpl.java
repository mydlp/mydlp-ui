package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.RuleDAO;
import com.mydlp.ui.domain.Rule;

@Service("ruleBRS")
@RemotingDestination
public class RuleBRSImpl implements RuleService
{
	@Autowired
	protected RuleDAO ruleDAO;

	@Override
	public List<Rule> getRules() {
		return ruleDAO.getRules();
	}

	@Override
	public Rule save(Rule rule) {
		return ruleDAO.save(rule);
	}

	@Override
	public void remove(Rule rule) {
		ruleDAO.remove(rule);
	}

}