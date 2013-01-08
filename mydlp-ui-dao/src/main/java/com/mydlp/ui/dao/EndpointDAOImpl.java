package com.mydlp.ui.dao;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.Endpoint;

@Repository("endpointDAO")
@Transactional
public class EndpointDAOImpl extends AbstractPolicyDAO implements
		EndpointDAO {
	
	
	@Autowired
	protected EndpointStatusDAO endpointStatusDAO;

	@Override
	public String registerNewEndpoint() throws RandomExhaustedException {
		Endpoint e = new Endpoint();
		e.setEndpointId(getRandomString());
		e.setEndpointSecret(getRandomString());
		e.setEndpointAlias(e.getEndpointId());
		getHibernateTemplate().saveOrUpdate(e);
		Integer realId = e.getId();
		e.setEndpointAlias("E" + String.format("%07d", realId));
		getHibernateTemplate().saveOrUpdate(e);
		endpointStatusDAO.upToDateEndpoint(e.getEndpointAlias(), null, null, null, null, null);
		return "EPKEY_" + e.getEndpointId() + "_" + e.getEndpointSecret();
	}
	
	protected String getRandomString() throws RandomExhaustedException {
		int counter = 0;
		while (counter < 5) {
			counter++;
			String randomStr = RandomStringUtils.randomAlphanumeric(32);
			
			if (doesEndpointIdExists(randomStr))
				continue;
			if (doesEndpointSecretExists(randomStr))
				continue;
			
			return randomStr;
		}
		throw new RandomExhaustedException();
	}
	
	protected Boolean doesEndpointIdExists(String endpointId) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Endpoint.class)
					.add(Restrictions.eq("endpointId", endpointId));
		@SuppressWarnings("unchecked")
		List<Endpoint> l = getHibernateTemplate().findByCriteria(criteria);
		if (l != null && l.size() > 0)
			return true;
		else
			return false;
	}
	
	protected Boolean doesEndpointSecretExists(String endpointSecret) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Endpoint.class)
					.add(Restrictions.eq("endpointSecret", endpointSecret));
		@SuppressWarnings("unchecked")
		List<Endpoint> l = getHibernateTemplate().findByCriteria(criteria);
		if (l != null && l.size() > 0)
			return true;
		else
			return false;
	}

	@Override
	public String getEndpointSecret(String endpointId) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Endpoint.class)
					.add(Restrictions.eq("endpointId", endpointId));
		@SuppressWarnings("unchecked")
		List<Endpoint> l = getHibernateTemplate().findByCriteria(criteria);
		Endpoint e = DAOUtil.getSingleResult(l);
		return e.getEndpointSecret();
	}

	@Override
	public String getEndpointAlias(String endpointId) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Endpoint.class)
					.add(Restrictions.eq("endpointId", endpointId));
		@SuppressWarnings("unchecked")
		List<Endpoint> l = getHibernateTemplate().findByCriteria(criteria);
		Endpoint e = DAOUtil.getSingleResult(l);
		return e.getEndpointAlias();
	}

	@Override
	public String getEndpointId(String endpointAlias) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Endpoint.class)
					.add(Restrictions.eq("endpointAlias", endpointAlias));
		@SuppressWarnings("unchecked")
		List<Endpoint> l = getHibernateTemplate().findByCriteria(criteria);
		Endpoint e = DAOUtil.getSingleResult(l);
		return e.getEndpointId();
	}

}
