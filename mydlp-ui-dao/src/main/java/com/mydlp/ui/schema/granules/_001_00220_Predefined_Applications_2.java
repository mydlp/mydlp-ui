package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.ApplicationName;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00220_Predefined_Applications_2 extends AbstractGranule {

	protected void generateApplicationNames(InventoryCategory inventoryCategory, String appName, String nameKey)
	{
		ApplicationName a = new ApplicationName();
		a.setDestinationString(appName);
		InventoryItem ii = new InventoryItem();
		ii.setNameKey(nameKey);
		ii.setItem(a);
		ii.setCategory(inventoryCategory);
		
		getHibernateTemplate().saveOrUpdate(a);
		getHibernateTemplate().saveOrUpdate(ii);
	}
	
	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.destinations.appNames.pdfViewers"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory pdfReaders  = DAOUtil.getSingleResult(list);
	
		generateApplicationNames(pdfReaders, "qvp64.exe", "inventory.destinations.appName.pdfquickview64");
		generateApplicationNames(pdfReaders, "nitropdfreader.exe", "inventory.destinations.appName.nitropdf2");
		
		getHibernateTemplate().saveOrUpdate(pdfReaders);
		
	}

}
