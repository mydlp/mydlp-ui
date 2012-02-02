package com.mydlp.ui.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.IncidentLog;
import com.mydlp.ui.domain.IncidentLogFile;
import com.mydlp.ui.domain.IncidentLogFileContent;

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
			if (field.equals("contentId") && operation.equals("eq"))
			{
				criteria.createAlias("files", "incidentFile", CriteriaSpecification.LEFT_JOIN);
				Integer contentId = (Integer) list.get(2);
				criteria.add(Restrictions.eq("incidentFile.content.id", contentId));
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

	@Override
	public IncidentLogFile geIncidentLogFile(Integer id) {
		return getHibernateTemplate().load(IncidentLogFile.class, id);
	}

	@Override
	public IncidentLogFileContent getIncidentContent(Integer id) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(IncidentLogFileContent.class)
					.add(Restrictions.eq("id", id));
		@SuppressWarnings("unchecked")
		List<IncidentLogFileContent> l = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(l);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getFilenamesForContent(Integer id) {
		return getHibernateTemplate().findByNamedParam(
				"select distinct f.filename from IncidentLogFile f " +
				"where f.content.id=:contentId", "contentId", id);
	}
		
}
