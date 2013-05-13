package com.mydlp.ui.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.CustomAction;
import com.mydlp.ui.domain.InventoryGroup;
import com.mydlp.ui.domain.Item;
import com.mydlp.ui.domain.Rule;
import com.mydlp.ui.domain.RuleItem;
import com.mydlp.ui.domain.RuleItemGroup;

@Repository("ruleDAO")
@Transactional
public class RuleDAOImpl extends AbstractPolicyDAO implements RuleDAO {

	private static Logger errorLogger = LoggerFactory.getLogger("IERROR");
	
	public static final Long DEFAULT_PRIORITY_DISTANCE = new Long(100);

	@SuppressWarnings("unchecked")
	public List<Rule> getRules() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Rule.class)
				.addOrder(Order.desc("priority"));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	@Transactional(readOnly=false)
	public Rule save(Rule r) {
		getHibernateTemplate().saveOrUpdate(r);
		return r;
	}

	@Override
	@Transactional(readOnly=false)
	public void remove(Rule r) {
		getHibernateTemplate().delete(r);
	}

	@Override
	@Transactional(readOnly=false)
	public void removeRuleItem(RuleItem ri) {
		if (ri == null || ri.getId() == null) return;
		
		getHibernateTemplate().delete(ri);
	}

	@Override
	@Transactional(readOnly=false)
	public void removeRuleItems(List<RuleItem> ris) {
		for (RuleItem ri : ris) {
			if (ri.getId() != null)
				removeRuleItem(ri);
			else
				logger.info("Recieved transient object ( Type: " +
						ri.getClass().getName()
						+ " ). Ignoring.");
		}
	}
	
	public void removeRuleItemGroup(RuleItemGroup ri) {
		if (ri == null || ri.getId() == null) return;
		
		getHibernateTemplate().delete(ri);
	}
	
	public void removeRuleItemGroups(List<RuleItemGroup> ris) {
		for (RuleItemGroup rig : ris) {
			if (rig.getId() != null)
				removeRuleItemGroup(rig);
			else
				logger.info("Recieved transient object ( Type: " +
						rig.getClass().getName()
						+ " ). Ignoring.");
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void ruleUp(Rule r) {
		List<Rule> rules = getRules();
		int itemIndex = findRuleIndex(rules, r);

		if (itemIndex == 0)
			return;

		Collections.swap(rules, itemIndex, itemIndex -1);
		balanceRulePriority(rules);
	}

	@Override
	@Transactional(readOnly=false)
	public void ruleDown(Rule r) {
		List<Rule> rules = getRules();
		int itemIndex = findRuleIndex(rules, r);

		if (itemIndex == (rules.size() - 1))
			return;

		Collections.swap(rules, itemIndex, itemIndex + 1);
		balanceRulePriority(rules);
	}

	protected int findRuleIndex(List<Rule> rules, Rule r)
	{
		for (int i = 0; i < rules.size(); i++) {
			Rule rt = rules.get(i);
			if (rt.getId().equals(r.getId()))
			{
				return i;
			}
		}

		throw new RuntimeException("Rule not found in database");
	}

	@Override
	@Transactional(readOnly=false)
	public void balanceRulePriority() {
		List<Rule> rules = getRules();
		balanceRulePriority(rules);
	}

	@Transactional(readOnly=false)
	protected void balanceRulePriority(List<Rule> rules) {
		Long p = ( rules.size() + 1 ) * DEFAULT_PRIORITY_DISTANCE;
		for (Rule rule : rules) {
			rule.setPriority(p);
			p -= DEFAULT_PRIORITY_DISTANCE;
			save(rule);
		}
	}

	@Override
	public void ruleMove(Rule rule, Long minPriority, Long maxPriority) {
		Long newPriority = Math.round((minPriority + maxPriority)/2.0);
		rule.setPriority(newPriority);
		save(rule);

		balanceRulePriority();
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, String> getRuleLabelsAndIds() {
		Map<String, String> returnMap = new HashMap<String, String>();

		Query query = getSession().createQuery(
				"select r.id, r.name from Rule r ");
		
		for (@SuppressWarnings("unchecked")
		Iterator<Object[]> iterator = query.list().iterator(); iterator.hasNext();) {
			Object[] row = (Object[]) iterator.next();
			returnMap.put(row[0].toString(), (String)row[1]);
		}
		return returnMap;
	}

	@Override
	public Rule changeRuleAction(Integer ruleId, String ruleAction,
			CustomAction ruleCustomAction) {
		Rule rule = getHibernateTemplate().get(Rule.class, ruleId);
		rule.setAction(ruleAction);
		rule.setCustomAction(ruleCustomAction);
		return save(rule);
	}

	@Override
	public Rule removeDuplicateItems(Rule rule,
			List<Map<String, Object>> addList,
			List<Map<String, Object>> initialDeleteList) {
		
		// initialize sets
		Set<RuleItem> deleteSetRI = new HashSet<RuleItem>();
		Set<RuleItemGroup> deleteSetRIG = new HashSet<RuleItemGroup>();
		
		Set<RuleItem> addSetRI = new HashSet<RuleItem>();
		Set<RuleItemGroup> addSetRIG = new HashSet<RuleItemGroup>();
		
		// load delete items
		if (initialDeleteList != null && initialDeleteList.size() > 0)
		{
			for (Map<String,Object> idi: initialDeleteList) {
				if (idi.get("type").equals("RuleItem"))
				{
					RuleItem ri = getHibernateTemplate().get(RuleItem.class, (Integer) idi.get("id"));
					if (ri != null)
					{
						deleteSetRI.add(ri);
					}
				}
				else if (idi.get("type").equals("RuleItemGroup"))
				{
					RuleItemGroup rig = getHibernateTemplate().get(RuleItemGroup.class, (Integer) idi.get("id"));
					if (rig != null)
					{
						deleteSetRIG.add(rig);
					}
				}
				else
				{
					errorLogger.error("Ignoring unexpected object: " + idi.toString());
				}
			}
		}
		
		//load add items
		if (addList != null && addList.size() > 0)
		{
			for (Map<String,Object> iai: addList) {
				if (iai.get("type").equals("RuleItem"))
				{
					RuleItem ri = null;
					if (iai.get("id") != null)
					{
						ri = getHibernateTemplate().get(RuleItem.class, (Integer) iai.get("id"));
					}
					// if id is null or object does not exists, create
					if (ri == null)
					{
						ri = new RuleItem();
						Item i = getHibernateTemplate().get(Item.class, (Integer) iai.get("item_id"));
						if (i == null)
						{
							errorLogger.error("Ignoring transient object with item_id: " + iai.get("item_id"));
							continue;
						}
						ri.setItem(i);
						ri.setRuleColumn((String)iai.get("ruleColumn"));
						ri.setRule(rule);
					}
					addSetRI.add(ri);
				}
				else if (iai.get("type").equals("RuleItemGroup"))
				{
					RuleItemGroup rig = null;
					if (iai.get("id") != null)
					{
						rig = getHibernateTemplate().get(RuleItemGroup.class, (Integer) iai.get("id"));
					}
					// if id is null or object does not exists, create
					if (rig == null)
					{
						rig = new RuleItemGroup();
						InventoryGroup g = getHibernateTemplate().get(InventoryGroup.class, (Integer) iai.get("group_id"));
						if (g == null)
						{
							errorLogger.error("Ignoring transient object with group_id: " + iai.get("group_id"));
							continue;
						}
						rig.setGroup(g);
						rig.setRule(rule);
					}
					addSetRIG.add(rig);
				}
				else
				{
					errorLogger.error("Ignoring unexpected object: " + iai.toString());
				}
			}
		}
		else
		{
			// we must have add list. So we should generate
				
			if (rule.getRuleItems() != null)
			{
				addSetRI.addAll(rule.getRuleItems());
			}
			addSetRI.removeAll(deleteSetRI);
			
			if (rule.getRuleItemGroups() != null)
			{
				addSetRIG.addAll(rule.getRuleItemGroups());
			}
			addSetRIG.removeAll(deleteSetRIG);
		}
		
		// collection magic
		if (rule.getRuleItems() != null)
		{
			deleteSetRI.addAll(rule.getRuleItems());
		}
		deleteSetRI.removeAll(addSetRI);
		// remove duplicates
		for (RuleItem ruleItem: addSetRI) {
			for (RuleItem ruleItemIter: addSetRI) {
				if (!deleteSetRI.contains(ruleItem) &&
					ruleItem != ruleItemIter && // should not be that same item
					// to be consider equal
					ruleItem.getItem().getId().equals(ruleItemIter.getItem().getId()) && // need to have save item_id
					( (ruleItem.getRuleColumn() == null && ruleItemIter.getRuleColumn() == null ) ||
							ruleItem.getRuleColumn().equals(ruleItemIter.getRuleColumn()) )  //need to have same ruleColumn value
				)
				{
					deleteSetRI.add(ruleItemIter);
				}
			}
		}
		// secondary collection magic
		addSetRI.removeAll(deleteSetRI);
		rule.setRuleItems(new ArrayList<RuleItem>(addSetRI));
		
		// this time for itemgroups
		// collection magic
		if (rule.getRuleItemGroups() != null)
		{
			deleteSetRIG.addAll(rule.getRuleItemGroups());
		}
		deleteSetRIG.removeAll(addSetRIG);
		// remove duplicates
		for (RuleItemGroup ruleItemGroup: addSetRIG) {
			for (RuleItemGroup ruleItemGroupIter: addSetRIG) {
				if (!deleteSetRIG.contains(ruleItemGroup) &&
						ruleItemGroup != ruleItemGroupIter && // should not be that same item
					// to be consider equal
					ruleItemGroup.getGroup().getId().equals(ruleItemGroupIter.getGroup().getId())// need to have save item_id
				)
				{
					deleteSetRIG.add(ruleItemGroupIter);
				}
			}
		}
		// secondary collection magics
		addSetRIG.removeAll(deleteSetRIG);
		rule.setRuleItemGroups(new ArrayList<RuleItemGroup>(addSetRIG));
		
		// remove redundant enties
		removeRuleItems(deleteSetRI);
		removeRuleItemGroups(deleteSetRIG);
		// save da rule
		return save(rule);
	}
	

	protected void removeRuleItems(Set<RuleItem> ruleItems) {
		List<RuleItem> list = new ArrayList<RuleItem>();
		list.addAll(ruleItems);
		removeRuleItems(list);
	}
	

	protected void removeRuleItemGroups(Set<RuleItemGroup> ruleItemGroups) {
		List<RuleItemGroup> list = new ArrayList<RuleItemGroup>();
		list.addAll(ruleItemGroups);
		removeRuleItemGroups(list);
	}

	@Override
	public Rule getRuleById(Integer ruleId) {
		return getHibernateTemplate().get(Rule.class, ruleId);
	}

}
