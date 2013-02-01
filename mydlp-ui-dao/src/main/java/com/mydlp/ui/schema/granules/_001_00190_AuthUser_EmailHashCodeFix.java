package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mydlp.ui.domain.AuthUser;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00190_AuthUser_EmailHashCodeFix extends AbstractGranule {

	@Override
	protected void callback() {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(AuthUser.class);
		@SuppressWarnings("unchecked")
		List<AuthUser> list = getHibernateTemplate().findByCriteria(criteria);

		for (AuthUser authUser : list) {
			authUser.setEmail(authUser.getEmail());
			getHibernateTemplate().saveOrUpdate(authUser);
		}

	}

}
