package com.mydlp.ui.dao;

import java.util.List;
import java.util.Map;

import com.mydlp.ui.domain.CustomAction;
import com.mydlp.ui.domain.Rule;
import com.mydlp.ui.domain.RuleItem;

public interface RuleDAO {

	public List<Rule> getRules();
	
	public Rule save(Rule r);
	
	public Rule getRuleById(Integer ruleId);
	
	public void remove(Rule r);
	
	public void removeRuleItem(RuleItem ri);
	
	public void removeRuleItems(List<RuleItem> ris);
	
	public void ruleUp(Rule r);
	
	public void ruleDown(Rule r);
	
	public void ruleMove(Rule r, Long minPriority, Long maxPriority);
	
	public void balanceRulePriority();
	
	public Map<String, String> getRuleLabelsAndIds();
	
	public Rule changeRuleAction(Integer ruleId, String ruleAction, CustomAction ruleCustomAction);
	
	public Rule removeDuplicateItems(Rule rule, 
			List<Map<String,Object>> addList, 
			List<Map<String,Object>> initialDeleteList);
}
