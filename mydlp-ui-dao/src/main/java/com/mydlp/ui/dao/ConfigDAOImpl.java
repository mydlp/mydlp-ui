package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.Config;

@Repository("configDAO")
@Transactional
public class ConfigDAOImpl extends AbstractPolicyDAO implements ConfigDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Config> getConfigs() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Config.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void saveAll(List<Config> configs) {
		for (Config config : configs)
			getHibernateTemplate().saveOrUpdate(config);
	}

	protected Config getConfig(String key) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Config.class)
				.add(Restrictions.eq("key", key));
		@SuppressWarnings("unchecked")
		List<Config> l = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(l);
	}
	
	@Override
	public String getValue(String key) {
		Config c = getConfig(key);
		if ( c == null )
			return null;
		else 
			return c.getValue();
	}

	@Override
	public void setValue(String key, String value) {
		Config c = getConfig(key);
		if ( c == null )
		{
			c = new Config();
			c.setKey(key);
		}
		c.setValue(value);
		getHibernateTemplate().saveOrUpdate(c);
	}
}
