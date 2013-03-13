package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.DiscoveryReport;

@Repository("discoveryReportDAO")
@Transactional
public class DiscoveryReportDAOImpl extends AbstractReportDAO implements DiscoveryReportDAO {
	
	protected static final String MAPKEY_LABEL = "labelKey";
	protected static final String MAPKEY_VALUE = "value";
	
	protected DetachedCriteria applyCriteriaList(DetachedCriteria detachedCriteria, List<List<Object>> criteriaList)
	{
		DetachedCriteria criteria = detachedCriteria;
		for (List<Object> list : criteriaList) {
			String field = (String) list.get(0);
			String operation = (String) list.get(1);
			
			
			if(field.equals("context") && operation.equals("eq"))
			{
				String context = (String) list.get(2);
				criteria.add(Restrictions.eq(field, context));
			}
			else if (field.equals("showAll") && operation.equals("eq"))
			{
				Boolean showAll = (Boolean) list.get(2); 
				if (!showAll)
				{
					criteria.add(Restrictions.eq("visible", true));
				}
			}
		}
		return criteria;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<DiscoveryReport> getDiscoveryReports(
			List<List<Object>> criteriaList, Integer offset, Integer limit) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(DiscoveryReport.class)
					.addOrder(Order.desc("startDate"));
		criteria = applyCriteriaList(criteria, criteriaList);
		return criteria.getExecutableCriteria(getSession())
			.setFirstResult(offset)
			.setMaxResults(limit)
			.list();
	}


	@Override
	public Long getDiscoveryReportCount(List<List<Object>> criteriaList) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(DiscoveryReport.class)
					.setProjection(Projections.rowCount());
		criteria = applyCriteriaList(criteria, criteriaList);
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}
	
	@Override
	@Transactional(readOnly=false)
	public DiscoveryReport save(DiscoveryReport d) {
		getHibernateTemplate().saveOrUpdate(d);
		return d;
	}

	@Override
	@Transactional(readOnly=false)
	public void remove(DiscoveryReport d) {
		getHibernateTemplate().delete(d);
	}
}
