package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.OperationLog;

@Repository("operationLogDAO")
@Transactional
public class OperationLogDAOImpl extends AbstractLogDAO implements OperationLogDAO {
	
	protected static final String MAPKEY_LABEL = "labelKey";
	protected static final String MAPKEY_VALUE = "value";

	@Override
	public Long getOperationLogCount(List<List<Object>> criteriaList) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(OperationLog.class)
					.setProjection(Projections.rowCount());
		criteria = applyCriteriaList(criteria, criteriaList);
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}
	
	
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
	public List<OperationLog> getOperationLogs(List<List<Object>> criteriaList, Integer offset, Integer limit) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(OperationLog.class)
					.addOrder(Order.desc("date"));
		criteria = applyCriteriaList(criteria, criteriaList);
		return criteria.getExecutableCriteria(getSession())
			.setFirstResult(offset)
			.setMaxResults(limit)
			.list();
	}

}
