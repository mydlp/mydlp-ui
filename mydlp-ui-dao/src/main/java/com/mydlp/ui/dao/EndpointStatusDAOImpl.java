package com.mydlp.ui.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.EndpointStatus;

@Repository("endpointStatusDAO")
@Transactional
public class EndpointStatusDAOImpl extends AbstractLogDAO implements
		EndpointStatusDAO {

	protected static final String SYNC_INTERVAL_KEY = "sync_interval";

	protected static final long OFFLINE_RATIO = 3;

	@Autowired
	protected ConfigDAO configDAO;

	@Override
	public void upToDateEndpoint(String endpointAlias, String ipAddress, String hostname, String username, String osName, String version, Boolean discoverInProg) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				EndpointStatus.class).add(
				Restrictions.eq("endpointAlias", endpointAlias));
		@SuppressWarnings("unchecked")
		List<EndpointStatus> list = getHibernateTemplate().findByCriteria(
				criteria);
		EndpointStatus endpointStatus = DAOUtil.getSingleResult(list);
		if (endpointStatus == null) {
			endpointStatus = new EndpointStatus();
			endpointStatus.setEndpointAlias(endpointAlias);
			endpointStatus.setFirstAppeared(new Date());
		}
		endpointStatus.setIsUpToDate(true);
		endpointStatus.setLastUpdate(new Date());
		if (endpointStatus.getIpAddress() == null)
			endpointStatus.setIpAddress(ipAddress);
		else {
			if (ipAddress != null)
				endpointStatus.setIpAddress(ipAddress);
			else
				logger.error("EndpointStatus#" + endpointStatus.getEndpointAlias()
						+ "has a not-null ip-address ("
						+ endpointStatus.getIpAddress()
						+ "). Ignoring ip-address update to null.");
		}
		if (endpointStatus.getHostname() == null)
			endpointStatus.setHostname(hostname);
		else {
			if (hostname != null)
				endpointStatus.setHostname(hostname);
			else
				logger.error("EndpointStatus#" + endpointStatus.getEndpointAlias()
						+ "has a not-null hostname ("
						+ endpointStatus.getHostname()
						+ "). Ignoring hostname update to null.");
		}
		if (endpointStatus.getUsername() == null)
			endpointStatus.setUsername(username);
		else {
			if (username != null)
				endpointStatus.setUsername(username);
			else
				logger.error("EndpointStatus#" + endpointStatus.getEndpointAlias()
						+ "has a not-null username ("
						+ endpointStatus.getUsername()
						+ "). Ignoring username update to null.");
		}
		if (endpointStatus.getVersion() == null)
			endpointStatus.setVersion(version);
		else {
			if (version != null)
				endpointStatus.setVersion(version);
			else
				logger.error("EndpointStatus#" + endpointStatus.getEndpointAlias()
						+ "has a not-null version  ("
						+ endpointStatus.getVersion()
						+ "). Ignoring version update to null.");
		}
		
		if (endpointStatus.getOsName() == null)
			endpointStatus.setOsName(osName);
		else {
			if (osName != null)
				endpointStatus.setOsName(osName);
			else
				logger.error("EndpointStatus#" + endpointStatus.getEndpointAlias()
						+ "has a not-null osName  ("
						+ endpointStatus.getOsName()
						+ "). Ignoring osName update to null.");
		}
		
		if (endpointStatus.getDiscoverInProg() == null)
			endpointStatus.setDiscoverInProg(discoverInProg);
		else {
			if (discoverInProg != null)
				endpointStatus.setDiscoverInProg(discoverInProg);
			else
				logger.error("EndpointStatus#" + endpointStatus.getEndpointAlias()
						+ "has a not-null discoverInProg  ("
						+ endpointStatus.getDiscoverInProg()
						+ "). Ignoring discoverInProg update to null.");
		}

		getHibernateTemplate().saveOrUpdate(endpointStatus);
	}

	@Override
	public void outOfDateAllEndpoints() {
		getHibernateTemplate().bulkUpdate(
				"update EndpointStatus es set es.isUpToDate=false");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndpointStatus> getEndpointStatuses(String searchString,
			Integer offset, Integer limit) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				EndpointStatus.class).addOrder(Order.desc("lastUpdate"));
		criteria = applySearchCriteria(criteria, searchString);
		return criteria.getExecutableCriteria(getSession())
				.setFirstResult(offset).setMaxResults(limit).list();
	}

	@Override
	public Long getEndpointStatusCount(String searchString) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				EndpointStatus.class).setProjection(Projections.rowCount());
		criteria = applySearchCriteria(criteria, searchString);
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}

	protected DetachedCriteria applySearchCriteria(
			DetachedCriteria detachedCriteria, String searchStr) {
		DetachedCriteria criteria = detachedCriteria;

		if (searchStr == null || searchStr.length() == 0)
			return criteria;

		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.sqlRestriction("(1=0)")); // defaults to
																// false

		disjunction.add(Restrictions.ilike("ipAddress", "%" + searchStr + "%"));
		disjunction.add(Restrictions.ilike("username", "%" + searchStr + "%"));
		disjunction.add(Restrictions.ilike("osName", "%" + searchStr + "%"));
		disjunction.add(Restrictions.ilike("version", "%" + searchStr + "%"));

		criteria = criteria.add(disjunction);

		return criteria;
	}

	protected Date getOfflineLimit() {
		long now = new Date().getTime();
		long sync_interval = Long.parseLong(configDAO
				.getValue(SYNC_INTERVAL_KEY));
		return new Date(now - (sync_interval * OFFLINE_RATIO));
	}

	@Override
	public Long getEndpointOnlineCount(String searchString) {
		Date offlineLimit = getOfflineLimit();
		DetachedCriteria criteria = DetachedCriteria.forClass(
				EndpointStatus.class).setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq("isUpToDate", true)).add(
				Restrictions.gt("lastUpdate", offlineLimit));
		criteria = applySearchCriteria(criteria, searchString);
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}

	@Override
	public Long getEndpointOfflineCount(String searchString) {
		Date offlineLimit = getOfflineLimit();
		DetachedCriteria criteria = DetachedCriteria.forClass(
				EndpointStatus.class).setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq("isUpToDate", true)).add(
				Restrictions.lt("lastUpdate", offlineLimit));
		criteria = applySearchCriteria(criteria, searchString);
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}

	@Override
	public Long getEndpointNotUpToDateCount(String searchString) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				EndpointStatus.class).setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq("isUpToDate", false));
		criteria = applySearchCriteria(criteria, searchString);
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}

	@Override
	public Boolean truncateEndpointStatus() {
		getHibernateTemplate().bulkUpdate("delete from EndpointStatus e");
		return true;
	}

}
