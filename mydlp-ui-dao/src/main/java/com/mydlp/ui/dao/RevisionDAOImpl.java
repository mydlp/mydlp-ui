package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.Revision;

@Repository("revisionDAO")
@Transactional
public class RevisionDAOImpl extends AbstractPolicyDAO implements RevisionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Revision> getRevisions(Integer offset, Integer limit) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Revision.class)
					.addOrder(Order.desc("id"));
		return criteria.getExecutableCriteria(getSession())
			.setFirstResult(offset)
			.setMaxResults(limit)
			.list();
	}

	@Override
	public Long getRevisionCount() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Revision.class)
					.setProjection(Projections.rowCount());
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Revision> getNamedRevisions(Integer offset, Integer limit) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Revision.class)
					.add(Restrictions.isNotNull("name"))
					.addOrder(Order.desc("id"));
		return criteria.getExecutableCriteria(getSession())
			.setFirstResult(offset)
			.setMaxResults(limit)
			.list();
	}

	@Override
	public Long getNamedRevisionCount() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Revision.class)
					.setProjection(Projections.rowCount())
					.add(Restrictions.isNotNull("name"));
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}

	@Override
	public void save(Revision revision) {
		getHibernateTemplate().saveOrUpdate(revision);
	}

	@Override
	public Long getRevisionIndex(Revision revision) {
		if (revision == null || revision.getId() == null) return 0L;
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Revision.class)
					.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		Revision firstRevision = DAOUtil.getSingleResult(criteria.getExecutableCriteria(getSession())
								.setMaxResults(1)
								.list());
		return new Long(firstRevision.getId() - revision.getId());
	}
		
}
