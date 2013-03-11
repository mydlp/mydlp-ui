package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.Domain;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00210_RoleNone extends AbstractGranule {
	
	
	@Override
	protected void callback() {
		AuthSecurityRole roleNone = new AuthSecurityRole();
		roleNone.setRoleName(AuthSecurityRole.ROLE_NONE);
		
		getHibernateTemplate().saveOrUpdate(roleNone);
	}
	
}
