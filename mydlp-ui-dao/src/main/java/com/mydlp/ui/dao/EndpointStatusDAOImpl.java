package com.mydlp.ui.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.EndpointStatus;

@Repository("endpointStatusDAO")
@Transactional
public class EndpointStatusDAOImpl extends AbstractLogDAO implements EndpointStatusDAO {

	@Override
	public void upToDateEndpoint(String ipAddress) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(EndpointStatus.class)
					.add(Restrictions.eq("ipAddress", ipAddress));
		@SuppressWarnings("unchecked")
		List<EndpointStatus> list = getHibernateTemplate().findByCriteria(criteria);
		EndpointStatus endpointStatus = DAOUtil.getSingleResult(list);
		if (endpointStatus == null)
		{
			endpointStatus = new EndpointStatus();
			endpointStatus.setIpAddress(ipAddress);
			endpointStatus.setFirstAppeared(new Date());
		}
		endpointStatus.setIsUpToDate(true);
		endpointStatus.setLastUpdate(new Date());
		
		getHibernateTemplate().saveOrUpdate(endpointStatus);
	}

	@Override
	public void outOfDateAllEndpoints() {
		getHibernateTemplate().bulkUpdate("update EndpointStatus es set es.isUpToDate=false");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndpointStatus> getEndpointStatuses(Integer offset,
			Integer limit) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(EndpointStatus.class)
					.addOrder(Order.desc("firstAppeared"));
		return criteria.getExecutableCriteria(getSession())
			.setFirstResult(offset)
			.setMaxResults(limit)
			.list();
	}

	@Override
	public Long getEndpointStatusCount() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(EndpointStatus.class)
					.setProjection(Projections.rowCount());
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}
			
}
