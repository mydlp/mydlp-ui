package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.RDBMSConnection;
import com.mydlp.ui.domain.RDBMSEnumeratedValue;
import com.mydlp.ui.domain.RDBMSInformationTarget;

@Repository("rdbmsConnectionDAO")
@Transactional
public class RDBMSConnectionDAOImpl extends AbstractPolicyDAO implements RDBMSConnectionDAO {


	@Override
	public AbstractEntity save(AbstractEntity entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public void remove(AbstractEntity entity) {
		if (entity instanceof RDBMSInformationTarget)
			deleteValues((RDBMSInformationTarget) entity);
		getHibernateTemplate().delete(entity);
	}

	@Override
	public Boolean hasValue(RDBMSInformationTarget rdbmsInformationTarget,
			int stringHashCode) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(RDBMSEnumeratedValue.class)
					.add(Restrictions.eq("informationTarget", rdbmsInformationTarget))
					.add(Restrictions.eq("hashCode", stringHashCode))
					.setProjection(Projections.property("id"));
		
		@SuppressWarnings("unchecked")
		List<Integer> l = getHibernateTemplate().findByCriteria(criteria);
		if (l.size() == 1)
			return true;
		else if (l.size() == 0)
			return false;
		throw new RuntimeException("Illegal number of objects.");
	}

	@Override
	public void deleteValues(RDBMSInformationTarget rdbmsInformationTarget) {
		getHibernateTemplate().bulkUpdate("delete from RDBMSEnumeratedValue v " +
				"where v.informationTarget=?", rdbmsInformationTarget);
	}

	@Override
	public RDBMSEnumeratedValue getValue(
			RDBMSInformationTarget rdbmsInformationTarget, String origIdValue) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(RDBMSEnumeratedValue.class)
					.add(Restrictions.eq("informationTarget", rdbmsInformationTarget))
					.add(Restrictions.eq("originalId", origIdValue));
		@SuppressWarnings("unchecked")
		List<RDBMSEnumeratedValue> l = getHibernateTemplate().findByCriteria(criteria);
		if (l.size() > 0)
			return l.get(0);
		return null;
	}

	@Override
	public Boolean hasOtherValue(RDBMSInformationTarget rdbmsInformationTarget,
			int stringHashCode, String originalId) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(RDBMSEnumeratedValue.class)
					.add(Restrictions.eq("informationTarget", rdbmsInformationTarget))
					.add(Restrictions.eq("hashCode", stringHashCode))
					.add(Restrictions.ne("originalId", originalId))
					.setProjection(Projections.property("id"));
		
		@SuppressWarnings("unchecked")
		List<Integer> l = getHibernateTemplate().findByCriteria(criteria);
		if (l.size() == 1)
			return true;
		else if (l.size() == 0)
			return false;
		throw new RuntimeException("Illegal number of objects.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RDBMSConnection> getRDBMSConnections() {
		DetachedCriteria criteria = DetachedCriteria.forClass(RDBMSConnection.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void finalizeProcess(Integer rdbmsInformationTargetId) {
		getHibernateTemplate().bulkUpdate(
				"update from RDBMSInformationTarget r set r.currentlyEnumerating=false where r.id=?", 
				rdbmsInformationTargetId);
	}

	@Override
	public void startProcess(Integer rdbmsInformationTargetId) {
		getHibernateTemplate().bulkUpdate(
				"update from RDBMSInformationTarget r set r.currentlyEnumerating=true where r.id=?", 
				rdbmsInformationTargetId);
	}

	@Override
	public RDBMSInformationTarget getInformationTargetById(Integer id) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(RDBMSInformationTarget.class)
				.add(Restrictions.eq("id", id));
		@SuppressWarnings("unchecked")
		List<RDBMSInformationTarget> l = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(l);
	}

		
}
