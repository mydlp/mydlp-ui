package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.InformationDescription;
import com.mydlp.ui.domain.InformationFeature;
import com.mydlp.ui.domain.InformationType;
import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.domain.Matcher;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00057_Network_Mac_Addresses extends AbstractGranule {

	@Override
	protected void callback() {
			
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.network_security.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory network = DAOUtil.getSingleResult(list);
		
		DetachedCriteria criteria2 = 
				DetachedCriteria.forClass(DataFormat.class)
					.add(Restrictions.eq("nameKey", "dataFormat.all.label"));
		@SuppressWarnings("unchecked")
		List<DataFormat> list2 = getHibernateTemplate().findByCriteria(criteria2);
		DataFormat df = DAOUtil.getSingleResult(list2);
		
		/*InventoryCategory patternsAndIP = new InventoryCategory();
		patternsAndIP.setNameKey("inventory.compliance.network_security.mac.predefined");
		patternsAndIP.setEditable(false);
		patternsAndIP.setCategory(network);*/
	
		Matcher matcher = new Matcher();
		matcher.setFunctionName("mac");
		
		InformationFeature informationFeature = new InformationFeature();
		informationFeature.setThreshold(new Long(4));
		informationFeature.setMatcher(matcher);
		matcher.setCoupledInformationFeature(informationFeature);
		
		InformationDescription informationDescription = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		informationDescription.setDistanceEnabled(true);
		informationDescription.setDistance(150);
		ifts.add(informationFeature);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey("informationType.compliance.network_security.mac");
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);

		inventoryItem.setCategory(network);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItem);
		network.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(network);
		//getHibernateTemplate().saveOrUpdate(patternsAndIP);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
	}
}
