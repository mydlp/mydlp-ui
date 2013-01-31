package com.mydlp.ui.dao;

import java.util.List;
import java.util.Map;

import com.mydlp.ui.domain.Rule;
import com.mydlp.ui.domain.RuleItem;

public interface RuleDAO {

	public List<Rule> getRules();
	
	public Rule save(Rule r);
	
	public void remove(Rule r);
	
	public void removeRuleItem(RuleItem ri);
	
	public void removeRuleItems(List<RuleItem> ris);
	
	public void ruleUp(Rule r);
	
	public void ruleDown(Rule r);
	
	public void ruleMove(Rule r, Long minPriority, Long maxPriority);
	
	public void balanceRulePriority();
	
	public Map<String, String> getRuleLabelsAndIds();
	
}
