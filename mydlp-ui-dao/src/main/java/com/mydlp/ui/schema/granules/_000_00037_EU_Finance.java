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

public class _000_00036_EU_Finance extends AbstractGranule {

	@Override
	protected void callback() {
			
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.finance.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory complianceFinance = DAOUtil.getSingleResult(list);
		
		DetachedCriteria criteria2 = 
				DetachedCriteria.forClass(DataFormat.class)
					.add(Restrictions.eq("nameKey", "dataFormat.all.label"));
		@SuppressWarnings("unchecked")
		List<DataFormat> list2 = getHibernateTemplate().findByCriteria(criteria2);
		DataFormat df = DAOUtil.getSingleResult(list2);
		

		InventoryCategory euFinance = new InventoryCategory();
		euFinance.setNameKey("inventory.compliance.finance.eu_finance.predefined");
		euFinance.setEditable(false);
		euFinance.setCategory(complianceFinance);
		
		InventoryCategory ccn_ni = new InventoryCategory();
		ccn_ni.setNameKey("inventory.compliance.finance.eu_finance.ccn_ni.predefined");
		ccn_ni.setEditable(false);
		ccn_ni.setCategory(euFinance);
		
		Matcher m = new Matcher();
		m.setFunctionName("cc");
	
		Matcher m1 = new Matcher();
		m1.setFunctionName("uk_nino");
		
		Matcher m2 = new Matcher();
		m2.setFunctionName("france_insee");
		
		Matcher m3 = new Matcher();
		m3.setFunctionName("cc");
		
		Matcher m4 = new Matcher();
		m4.setFunctionName("cc");
		
		Matcher m5 = new Matcher();
		m5.setFunctionName("spain_dni");
		
		Matcher m6 = new Matcher();
		m6.setFunctionName("cc");
		
		Matcher m7 = new Matcher();
		m7.setFunctionName("italy_fc");
		
		InformationFeature ift = new InformationFeature();
		ift.setThreshold(new Long(1));
		ift.setMatcher(m);
		m.setCoupledInformationFeature(ift);
		
		InformationFeature ift1 = new InformationFeature();
		ift1.setThreshold(new Long(1));
		ift1.setMatcher(m1);
		m1.setCoupledInformationFeature(ift1);
		
		InformationFeature ift2 = new InformationFeature();
		ift2.setThreshold(new Long(1));
		ift2.setMatcher(m2);
		m2.setCoupledInformationFeature(ift2);
		
		InformationFeature ift3 = new InformationFeature();
		ift3.setThreshold(new Long(1));
		ift3.setMatcher(m3);
		m3.setCoupledInformationFeature(ift3);
		
		InformationFeature ift4 = new InformationFeature();
		ift4.setThreshold(new Long(1));
		ift4.setMatcher(m4);
		m4.setCoupledInformationFeature(ift4);
		
		InformationFeature ift5 = new InformationFeature();
		ift5.setThreshold(new Long(1));
		ift5.setMatcher(m5);
		m5.setCoupledInformationFeature(ift5);
		
		InformationFeature ift6 = new InformationFeature();
		ift6.setThreshold(new Long(1));
		ift6.setMatcher(m6);
		m6.setCoupledInformationFeature(ift6);
		
		InformationFeature ift7 = new InformationFeature();
		ift7.setThreshold(new Long(1));
		ift7.setMatcher(m7);
		m7.setCoupledInformationFeature(ift7);
		
		InformationDescription id = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		id.setDistanceEnabled(true);
		id.setDistance(100);
		ifts.add(ift);
		ifts.add(ift1);
		id.setFeatures(ifts);
		
		InformationType it = new InformationType();
		it.setInformationDescription(id);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		it.setDataFormats(dfs);
		
		InventoryItem ii = new InventoryItem();
		ii.setNameKey("informationType.compliance.finance.eu_finance.uk");
		ii.setItem(it);
		it.setCoupledInventoryItem(ii);
		
		InformationDescription id1 = new InformationDescription();
		List<InformationFeature> ifts1 = new ArrayList<InformationFeature>();
		id1.setDistanceEnabled(true);
		id1.setDistance(100);
		ifts1.add(ift3);
		ifts1.add(ift2);
		id1.setFeatures(ifts1);
		
		InformationType it1 = new InformationType();
		it1.setInformationDescription(id1);
		it1.setDataFormats(dfs);
		
		InventoryItem ii1 = new InventoryItem();
		ii1.setNameKey("informationType.compliance.finance.eu_finance.france");
		ii1.setItem(it1);
		it1.setCoupledInventoryItem(ii1);
		
		InformationDescription id2 = new InformationDescription();
		List<InformationFeature> ifts2 = new ArrayList<InformationFeature>();
		id2.setDistanceEnabled(true);
		id2.setDistance(100);
		ifts2.add(ift4);
		ifts2.add(ift5);
		id2.setFeatures(ifts2);
		
		InformationType it2 = new InformationType();
		it2.setInformationDescription(id2);
		it2.setDataFormats(dfs);
		
		InventoryItem ii2 = new InventoryItem();
		ii2.setNameKey("informationType.compliance.finance.eu_finance.spain");
		ii2.setItem(it2);
		it2.setCoupledInventoryItem(ii2);
		
		InformationDescription id3 = new InformationDescription();
		List<InformationFeature> ifts3 = new ArrayList<InformationFeature>();
		id3.setDistanceEnabled(true);
		id3.setDistance(100);
		ifts3.add(ift6);
		ifts3.add(ift7);
		id3.setFeatures(ifts3);
		
		InformationType it3 = new InformationType();
		it3.setInformationDescription(id3);
		it3.setDataFormats(dfs);
		
		InventoryItem ii3 = new InventoryItem();
		ii3.setNameKey("informationType.compliance.finance.eu_finance.italy");
		ii3.setItem(it3);
		it3.setCoupledInventoryItem(ii3);
		
		ii.setCategory(ccn_ni);
		ii1.setCategory(ccn_ni);
		ii2.setCategory(ccn_ni);
		ii3.setCategory(ccn_ni);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(ii);
		dtc.add(ii1);
		dtc.add(ii2);
		dtc.add(ii3);
		ccn_ni.setChildren(dtc);
	
		getHibernateTemplate().saveOrUpdate(complianceFinance);
		getHibernateTemplate().saveOrUpdate(euFinance);
		getHibernateTemplate().saveOrUpdate(ccn_ni);
		getHibernateTemplate().saveOrUpdate(ii);
		getHibernateTemplate().saveOrUpdate(ii1);
		getHibernateTemplate().saveOrUpdate(ii2);
		getHibernateTemplate().saveOrUpdate(ii3);
	}
}
