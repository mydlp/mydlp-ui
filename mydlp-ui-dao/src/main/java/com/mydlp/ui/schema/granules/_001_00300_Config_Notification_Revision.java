package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00300_Config_Notification_Revision extends AbstractGranule {

	@Override
	protected void callback() {
		
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Config.class)
					.add(Restrictions.eq("key", "email_notification_message"));
		@SuppressWarnings("unchecked")
		List<Config> list = getHibernateTemplate().findByCriteria(criteria);
		Config config = DAOUtil.getSingleResult(list);
			
		String oldValue = config.getValue();
		config.setValue(oldValue + "\n" + "%%DETAILS%%");
		

		getHibernateTemplate().saveOrUpdate(config);	

	}

}
	