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

public class _000_00035_PCI extends AbstractGranule {

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
		

		InventoryCategory pci = new InventoryCategory();
		pci.setNameKey("inventory.compliance.finance.pci.predefined");
		pci.setEditable(false);
		pci.setCategory(complianceFinance);
		
		InventoryCategory pciTrack = new InventoryCategory();
		pciTrack.setNameKey("inventory.compliance.finance.pci_track.predefined");
		pciTrack.setEditable(false);
		pciTrack.setCategory(pci);
		
		Matcher m = new Matcher();
		m.setFunctionName("cc");
		
		/*Matcher m2 = new Matcher();
		m2.setFunctionName("cc_track2");
		
		Matcher m3 = new Matcher();
		m3.setFunctionName("cc_track3");*/
		
		InformationFeature ift = new InformationFeature();
		ift.setThreshold(new Long(1));
		ift.setMatcher(m);
		m.setCoupledInformationFeature(ift);
		
		InformationDescription id = new InformationDescription();
		//ift.setInformationDescription(id);
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		id.setDistanceEnabled(false);
		id.setDistance(32);
		ifts.add(ift);
		id.setFeatures(ifts);
		
		InformationType it = new InformationType();
		it.setInformationDescription(id);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		it.setDataFormats(dfs);
		//id.setInformationType(it);
		
		InventoryItem ii = new InventoryItem();
		ii.setNameKey("informationType.compliance.finance.pci.credit_card");
		ii.setItem(it);
		it.setCoupledInventoryItem(ii);
		
		ii.setCategory(pci);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(ii);
		pci.setChildren(dtc);
		
		Matcher m1 = new Matcher();
		m1.setFunctionName("cc_track1");
		
		InformationFeature ift1 = new InformationFeature();
		ift1.setThreshold(new Long(1));
		ift1.setMatcher(m1);
		m1.setCoupledInformationFeature(ift1);
		
		InformationDescription id1 = new InformationDescription();
		//ift.setInformationDescription(id);
		List<InformationFeature> ifts1 = new ArrayList<InformationFeature>();
		id1.setDistanceEnabled(false);
		id1.setDistance(32);
		ifts1.add(ift1);
		id1.setFeatures(ifts1);
		
		InformationType it1 = new InformationType();
		it1.setInformationDescription(id1);
		it1.setDataFormats(dfs);
		//id.setInformationType(it);
		
		InventoryItem ii1 = new InventoryItem();
		ii1.setNameKey("informationType.compliance.finance.pci.credit_card_track1");
		ii1.setItem(it1);
		it1.setCoupledInventoryItem(ii1);
		
		//ii1.setCategory(pciTrack);
		//List<InventoryBase> dtc1 = new ArrayList<InventoryBase>();
		//dtc1.add(ii1);
		//pci.setChildren(dtc1);
		
		Matcher m2 = new Matcher();
		m2.setFunctionName("cc_track2");
		
		InformationFeature ift2 = new InformationFeature();
		ift2.setThreshold(new Long(1));
		ift2.setMatcher(m2);
		m2.setCoupledInformationFeature(ift2);
		
		InformationDescription id2 = new InformationDescription();
		List<InformationFeature> ifts2 = new ArrayList<InformationFeature>();
		id2.setDistanceEnabled(false);
		id2.setDistance(32);
		ifts2.add(ift2);
		id2.setFeatures(ifts2);
		
		InformationType it2 = new InformationType();
		it2.setInformationDescription(id2);
		it2.setDataFormats(dfs);
		
		InventoryItem ii2 = new InventoryItem();
		ii2.setNameKey("informationType.compliance.finance.pci.credit_card_track2");
		ii2.setItem(it2);
		it2.setCoupledInventoryItem(ii2);
		
		//ii2.setCategory(pciTrack);
		//List<InventoryBase> dtc2 = new ArrayList<InventoryBase>();
		//dtc2.add(ii2);
		//pci.setChildren(dtc2);
		
		Matcher m3 = new Matcher();
		m3.setFunctionName("cc_track3");
		
		InformationFeature ift3 = new InformationFeature();
		ift3.setThreshold(new Long(1));
		ift3.setMatcher(m3);
		m3.setCoupledInformationFeature(ift3);
		
		InformationDescription id3 = new InformationDescription();
		List<InformationFeature> ifts3 = new ArrayList<InformationFeature>();
		id3.setDistanceEnabled(false);
		id3.setDistance(32);
		ifts3.add(ift3);
		id3.setFeatures(ifts3);
		
		InformationType it3 = new InformationType();
		it3.setInformationDescription(id3);
		it3.setDataFormats(dfs);
		
		InventoryItem ii3 = new InventoryItem();
		ii3.setNameKey("informationType.compliance.finance.pci.credit_card_track3");
		ii3.setItem(it3);
		it3.setCoupledInventoryItem(ii3);
		
		ii3.setCategory(pciTrack);
		ii2.setCategory(pciTrack);
		ii1.setCategory(pciTrack);
		List<InventoryBase> dtc3 = new ArrayList<InventoryBase>();
		dtc3.add(ii3);
		dtc3.add(ii2);
		dtc3.add(ii1);
		pci.setChildren(dtc3);
		
	
		getHibernateTemplate().saveOrUpdate(complianceFinance);
		getHibernateTemplate().saveOrUpdate(pci);
		getHibernateTemplate().saveOrUpdate(pciTrack);
		getHibernateTemplate().saveOrUpdate(ii);
		getHibernateTemplate().saveOrUpdate(ii1);
		getHibernateTemplate().saveOrUpdate(ii2);
		getHibernateTemplate().saveOrUpdate(ii3);
	}
}
