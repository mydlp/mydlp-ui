package com.mydlp.ui.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.CustomAction;
import com.mydlp.ui.domain.Rule;
import com.mydlp.ui.domain.RuleItem;

@Repository("ruleDAO")
@Transactional
public class RuleDAOImpl extends AbstractPolicyDAO implements RuleDAO {

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
	public void changeRuleAction(Integer ruleId, String ruleAction,
			CustomAction ruleCustomAction) {
		Rule rule = getHibernateTemplate().get(Rule.class, ruleId);
		rule.setAction(ruleAction);
		rule.setCustomAction(ruleCustomAction);
		save(rule);
	}

}
