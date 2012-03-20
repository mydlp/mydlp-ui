package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.domain.ADDomainItem;
import com.mydlp.ui.domain.AbstractEntity;


@Repository("adDomainDAO")
@Transactional
public class ADDomainDAOImpl extends AbstractPolicyDAO implements ADDomainDAO {

	@Override
	@Transactional(readOnly=false)
	public ADDomain saveDomain(ADDomain domain) {
		getHibernateTemplate().saveOrUpdate(domain);
		return domain;
	}

	@Override
	@Transactional(readOnly=false)
	public ADDomainItem saveDomainItem(ADDomainItem domainItem) {
		getHibernateTemplate().saveOrUpdate(domainItem);
		return domainItem;
	}

	@Override
	public void remove(AbstractEntity domainObj) {
		getHibernateTemplate().delete(domainObj);
	}

	@Override
	public ADDomainItem findByDistinguishedName(String distinguishedName) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(ADDomainItem.class)
				.add(Restrictions.eq("distinguishedName", distinguishedName));
		@SuppressWarnings("unchecked")
		List<ADDomainItem> l = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(l);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ADDomain> getADDomains() {
		DetachedCriteria criteria = DetachedCriteria.forClass(ADDomain.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
