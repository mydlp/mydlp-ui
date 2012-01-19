package com.mydlp.ui.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.IncidentLog;

@Repository("incidentLogDAO")
@Transactional
public class IncidentLogDAOImpl extends AbstractLogDAO implements IncidentLogDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<IncidentLog> getIncidents() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(IncidentLog.class)
					.addOrder(Order.desc("id"));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public Long getIncidentCount(List<List<Object>> criteriaList) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(IncidentLog.class)
					.setProjection(Projections.rowCount());
		criteria = applyUserCriteria(criteria, criteriaList);
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}
	
	protected DetachedCriteria applyUserCriteria(DetachedCriteria detachedCriteria, List<List<Object>> criteriaList)
	{
		DetachedCriteria criteria = detachedCriteria;
		for (List<Object> list : criteriaList) {
			String field = (String) list.get(0);
			String operation = (String) list.get(1);
			if (field.equals("date") && operation.equals("bw"))
			{
				Date startDate = (Date) list.get(2);
				Date endDate = (Date) list.get(3);
				criteria = criteria.add(Restrictions.between(field, startDate, endDate));
			}
			
		}
		return criteria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IncidentLog> getIncidents(List<List<Object>> criteriaList, Integer offset, Integer limit) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(IncidentLog.class)
					.addOrder(Order.desc("id"));
		criteria = applyUserCriteria(criteria, criteriaList);
		return criteria.getExecutableCriteria(getSession())
			.setFirstResult(offset)
			.setMaxResults(limit)
			.list();
	}
		
}
