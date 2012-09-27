package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.BundledKeywordGroup;

@Repository("bundledKeywordGroupDAO")
@Transactional
public class BundledKeywordGroupDAOImpl extends AbstractPolicyDAO implements BundledKeywordGroupDAO{

	@SuppressWarnings("unchecked")
	public List<BundledKeywordGroup> getBundledKeywordGroups() {
		DetachedCriteria criteria =
				DetachedCriteria.forClass(BundledKeywordGroup.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public BundledKeywordGroup getBundledKeywordGroupById(Integer id) {
		DetachedCriteria criteria = 
					DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("id", id));
		List<BundledKeywordGroup> l = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(l);
	}

}
