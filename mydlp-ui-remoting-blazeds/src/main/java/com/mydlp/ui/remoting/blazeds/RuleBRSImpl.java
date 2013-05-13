package com.mydlp.ui.remoting.blazeds;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.DiscoveryReportDAO;
import com.mydlp.ui.dao.RuleDAO;
import com.mydlp.ui.domain.CustomAction;
import com.mydlp.ui.domain.DiscoveryReport;
import com.mydlp.ui.domain.Rule;
import com.mydlp.ui.domain.RuleItem;

@Service("ruleBRS")
@RemotingDestination
public class RuleBRSImpl implements RuleService
{
	@Autowired
	protected RuleDAO ruleDAO;
	
	@Autowired
	protected DiscoveryReportDAO discoveryReportDAO;

	@Override
	public List<Rule> getRules() {
		return ruleDAO.getRules();
	}
	

	@Override
	public Rule save(Rule rule) {
		return save(rule, null, null);
	}
	
	protected Rule save(Rule rule, List<Map<String,Object>> addList, List<Map<String,Object>> deleteList) {
		rule = ruleDAO.removeDuplicateItems(rule, addList, deleteList);
		ruleDAO.balanceRulePriority();
		return rule;
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
	public void removeRuleItems(List<RuleItem> ruleItems) {
		ruleDAO.removeRuleItems(ruleItems);
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

	@Override
	public void ruleMove(Rule rule, Long minPriority, Long maxPriority) {
		ruleDAO.ruleMove(rule, minPriority, maxPriority);
	}

	@Override
	public Map<String, String> getRuleLabelsAndIds() {
		return ruleDAO.getRuleLabelsAndIds();
	}

	@Override
	public DiscoveryReport getDiscoveryStatus(Long ruleId) {
		return discoveryReportDAO.getDiscoveryStatus(ruleId);
	}

	@Override
	public Rule changeRuleAction(Integer ruleId, String ruleAction,
			CustomAction ruleCustomAction) {
		return ruleDAO.changeRuleAction(ruleId, ruleAction,ruleCustomAction);
	}

	@Override
	public Rule saveRuleItemChanges(Integer ruleId,
			List<Map<String, Object>> itemsToAdd,
			List<Map<String, Object>> itemsToDelete) {
		Rule rule = ruleDAO.getRuleById(ruleId);
		return save(rule, itemsToAdd, itemsToDelete);
	}

}