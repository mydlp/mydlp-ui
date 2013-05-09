package com.mydlp.ui.remoting.blazeds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	protected Rule removeDuplicateRuleItems(Rule rule, List<RuleItem> initialDeleteList) {
		Set<RuleItem> deleteSet = new HashSet<RuleItem>();
		if (initialDeleteList != null && initialDeleteList.size() > 0)
			deleteSet.addAll(initialDeleteList);
		
		for (RuleItem ruleItem: rule.getRuleItems()) {
			for (RuleItem ruleItemIter: rule.getRuleItems()) {
				if (!deleteSet.contains(ruleItem) &&
					ruleItem != ruleItemIter && // should not be that same item
					// to be consider equal
					ruleItem.getItem().getId().equals(ruleItemIter.getItem().getId()) && // need to have save item_id
					( (ruleItem.getRuleColumn() == null && ruleItemIter.getRuleColumn() == null ) ||
							ruleItem.getRuleColumn().equals(ruleItemIter.getRuleColumn()) )  //need to have same ruleColumn value
				)
				{
					deleteSet.add(ruleItemIter);
				}
			}
		}
		rule.getRuleItems().removeAll(deleteSet);
		removeRuleItems(deleteSet);
		return rule;
	}

	@Override
	public Rule save(Rule rule) {
		return save(rule, null);
	}
	
	protected Rule save(Rule rule, List<RuleItem> deleteList) {
		rule = removeDuplicateRuleItems(rule, deleteList);
		rule = ruleDAO.save(rule);
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
	
	protected void removeRuleItems(Set<RuleItem> ruleItems) {
		List<RuleItem> list = new ArrayList<RuleItem>();
		list.addAll(ruleItems);
		removeRuleItems(list);
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
	public void saveChanges(Rule rule, List<RuleItem> ruleItems) {
		save(rule, ruleItems);
	}

	@Override
	public void changeRuleAction(Integer ruleId, String ruleAction,
			CustomAction ruleCustomAction) {
		ruleDAO.changeRuleAction(ruleId, ruleAction,ruleCustomAction);
	}

}