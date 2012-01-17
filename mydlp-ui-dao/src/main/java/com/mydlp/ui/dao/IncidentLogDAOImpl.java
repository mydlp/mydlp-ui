package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
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
					.addOrder(Order.desc("date"));
		return getHibernateTemplate().findByCriteria(criteria);
	}
		
}
