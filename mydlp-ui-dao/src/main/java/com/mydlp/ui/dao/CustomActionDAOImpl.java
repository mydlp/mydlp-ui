package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.CustomAction;

@Repository("customActionDAO")
@Transactional
public class CustomActionDAOImpl extends AbstractPolicyDAO implements CustomActionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomAction> getCustomActions(String searchStr) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CustomAction.class);
		criteria = applySearchCriteria(criteria, searchStr);
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	protected DetachedCriteria applySearchCriteria(DetachedCriteria detachedCriteria, String searchStr)
	{
		DetachedCriteria criteria = detachedCriteria;
		
		if (searchStr == null || searchStr.length() == 0)
			return criteria;
		
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.sqlRestriction("(1=0)")); //  defaults to false
		
		disjunction.add(Restrictions.ilike("type", "%" + searchStr + "%"));
		disjunction.add(Restrictions.ilike("name", "%" + searchStr + "%"));
		
		criteria = criteria.add(disjunction);
		
		return criteria;
	}

	@Override
	public List<CustomAction> getCustomActions() {
		return getCustomActions(null);
	}

}
