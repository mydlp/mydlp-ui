package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.RuleDAO;
import com.mydlp.ui.domain.Rule;
import com.mydlp.ui.domain.RuleItem;

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

	@Override
	public void removeRuleItem(RuleItem ruleItem) {
		ruleDAO.removeRuleItem(ruleItem);
	}

	@Override
	public void ruleUp(Rule r) {
		ruleDAO.ruleUp(r);
	}

	@Override
	public void ruleDown(Rule r) {
		ruleDAO.ruleDown(r);
	}

	@Override
	public void balanceRulePriority() {
		ruleDAO.balanceRulePriority();
	}

}