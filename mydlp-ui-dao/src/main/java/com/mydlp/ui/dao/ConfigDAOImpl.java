package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
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
}
