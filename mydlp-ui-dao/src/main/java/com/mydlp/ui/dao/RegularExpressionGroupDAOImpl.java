package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.RegularExpressionGroup;


@Repository("regularExpressionGroupDAO")
@Transactional
public class RegularExpressionGroupDAOImpl extends AbstractPolicyDAO implements RegularExpressionGroupDAO {

	@Autowired
	protected RDBMSConnectionDAO rdbmsConnectionDAO;
	
	@SuppressWarnings("unchecked")
	public List<RegularExpressionGroup> getRegularExpressionGroups() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(RegularExpressionGroup.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	@Transactional(readOnly=false)
	public RegularExpressionGroup save(RegularExpressionGroup r) {
		getHibernateTemplate().saveOrUpdate(r);
		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegularExpressionGroup> getRegularExpressionGroupsWithRDBMS() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(RegularExpressionGroup.class)
				.add(Restrictions.isNotNull("rdbmsInformationTarget"));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public RegularExpressionGroup getRegularExpressionGroupById(Integer id) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(RegularExpressionGroup.class)
				.add(Restrictions.eq("id", id));
		@SuppressWarnings("unchecked")
		List<RegularExpressionGroup> l = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(l);
	}

	@Override
	public void remove(RegularExpressionGroup r) {
		r = getRegularExpressionGroupById(r.getId());
		if (r.getRdbmsInformationTarget() != null) {
			rdbmsConnectionDAO.deleteValues(r.getRdbmsInformationTarget());
		}
		getHibernateTemplate().delete(r);
	}

}
