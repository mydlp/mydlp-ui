package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.AbstractNamedEntity;

@Repository("genericDAO")
@Transactional
public class GenericDAOImpl extends AbstractPolicyDAO implements GenericDAO {

	@Override
	@Transactional(readOnly=false)
	public AbstractEntity save(AbstractEntity i) {
		getHibernateTemplate().saveOrUpdate(i);
		return i;
	}

	@Override
	@Transactional(readOnly=false)
	public void remove(AbstractEntity i) {
		getHibernateTemplate().delete(i);
	}

	@Override
	public AbstractEntity merge(AbstractEntity entity) {
		getHibernateTemplate().merge(entity);
		return entity;
	}

	@Override
	@Transactional(readOnly=false)
	public void remove(String entityName, Integer id) {
		AbstractEntity i = (AbstractEntity) getHibernateTemplate().load(entityName, id);
		getHibernateTemplate().delete(i);
	}

	@Override
	public Boolean isObjectWithNameExists(AbstractNamedEntity namedEntity) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(namedEntity.getClass())
					.setProjection(Projections.rowCount())
					.add(Restrictions.ne("id", namedEntity.getId()))
					.add(Restrictions.eq("name", namedEntity.getName()));
		
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		Long count = DAOUtil.getSingleResult(returnList);
		
		if (count == 0 ) 
			return false;
		else 
			return true;
	}
}
