package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.AuthUser;
import com.mydlp.ui.domain.DashboardItem;
import com.mydlp.ui.domain.UserSettings;

@Repository("userSettingsDAO")
@Transactional
public class UserSettingsDAOImpl extends AbstractPolicyDAO implements UserSettingsDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDashboardItems() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(DashboardItem.class)
						.setProjection(Projections.distinct(Projections.property("dasboardItemKey")));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public UserSettings findByUser(AuthUser user) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(UserSettings.class)
					.add(Restrictions.eq("user", user));
		@SuppressWarnings("unchecked")
		List<UserSettings> returnList = getHibernateTemplate().findByCriteria(criteria);
		UserSettings settings = DAOUtil.getSingleResult(returnList);
		if (settings == null) {
			settings = new UserSettings();
			settings.setUser(user);
			getHibernateTemplate().saveOrUpdate(settings);
		}
		return settings;
	}
	
}
