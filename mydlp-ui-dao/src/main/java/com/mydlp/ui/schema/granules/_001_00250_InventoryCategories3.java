package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00250_InventoryCategories3 extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory compliance = DAOUtil.getSingleResult(list);
		
		InventoryCategory compliancePersonalInformation = new InventoryCategory();
		compliancePersonalInformation.setNameKey("inventory.compliance.personal_information.predefined");
		compliancePersonalInformation.setEditable(false);
		compliancePersonalInformation.setCategory(compliance);
		getHibernateTemplate().saveOrUpdate(compliancePersonalInformation);
	
		InventoryCategory compliancePersonalInformationChina = new InventoryCategory();
		compliancePersonalInformationChina.setNameKey("inventory.compliance.personal_information.china.predefined");
		compliancePersonalInformationChina.setEditable(false);
		compliancePersonalInformationChina.setCategory(compliancePersonalInformation);
		getHibernateTemplate().saveOrUpdate(compliancePersonalInformationChina);
		
		InventoryCategory compliancePersonalInformationTaiwan = new InventoryCategory();
		compliancePersonalInformationTaiwan.setNameKey("inventory.compliance.personal_information.taiwan.predefined");
		compliancePersonalInformationTaiwan.setEditable(false);
		compliancePersonalInformationTaiwan.setCategory(compliancePersonalInformation);
		getHibernateTemplate().saveOrUpdate(compliancePersonalInformationTaiwan);
		
	}

}
