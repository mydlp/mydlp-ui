package com.mydlp.ui.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.RegularExpressionGroup;
import com.mydlp.ui.domain.RegularExpressionGroupEntry;


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
		if (r.getRdbmsInformationTarget() != null) {
			rdbmsConnectionDAO.remove(r);
			r.setRdbmsInformationTarget(null);
		}
		
		if (r.getEntries() != null) {
			for (RegularExpressionGroupEntry entry : r.getEntries()) 
				getHibernateTemplate().delete(entry);
			r.setEntries(new ArrayList<RegularExpressionGroupEntry>());
		}
		
		getHibernateTemplate().saveOrUpdate(r);
		
		getHibernateTemplate().delete(r);
	}

}
