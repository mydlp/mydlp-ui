package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.AuthUser;

@Repository("userDAO")
@Transactional
public class UserDAOImpl extends AbstractPolicyDAO implements UserDAO {
	@Override
	public AuthUser findByName(String username) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(AuthUser.class)
					.add(Restrictions.eq("username", username));
		@SuppressWarnings("unchecked")
		List<AuthUser> list = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(list);
	}
	
	@SuppressWarnings("unchecked")
	public List<AuthUser> getUsers() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(AuthUser.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	@Override
	@Transactional(readOnly=false)
	public AuthUser save(AuthUser r) {
		getHibernateTemplate().saveOrUpdate(r);
		return r;
	}
	
	@Override
	@Transactional(readOnly=false)
	public void remove(AuthUser i) {
		getHibernateTemplate().delete(i);
	}

	@SuppressWarnings("unchecked")
	public List<AuthSecurityRole> getRoles() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(AuthSecurityRole.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
}
