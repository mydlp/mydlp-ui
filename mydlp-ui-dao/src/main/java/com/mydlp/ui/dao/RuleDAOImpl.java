package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.Rule;

@Repository("ruleDAO")
@Transactional
public class RuleDAOImpl extends AbstractDAO implements RuleDAO {

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
	
}
