package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.BundledKeywordGroup;
import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.InformationDescription;
import com.mydlp.ui.domain.InformationFeature;
import com.mydlp.ui.domain.InformationType;
import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.domain.Matcher;
import com.mydlp.ui.domain.MatcherArgument;
import com.mydlp.ui.domain.NonCascadingArgument;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00051_CompanyConfidentiality_StrategicBusinessDocument extends AbstractGranule {

	@Override
	protected void callback() {
			
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.company_confidentiality.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory company = DAOUtil.getSingleResult(list);
		
		DetachedCriteria criteria2 = 
				DetachedCriteria.forClass(DataFormat.class)
					.add(Restrictions.eq("nameKey", "dataFormat.all.label"));
		@SuppressWarnings("unchecked")
		List<DataFormat> list2 = getHibernateTemplate().findByCriteria(criteria2);
		DataFormat df = DAOUtil.getSingleResult(list2);
		
		DetachedCriteria criteria3 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("descriptionKey", "strategic.business.document.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list3 = getHibernateTemplate().findByCriteria(criteria3);
		BundledKeywordGroup sbdKeyword = DAOUtil.getSingleResult(list3);
		
		InventoryCategory sbd = new InventoryCategory();
		sbd.setNameKey("inventory.compliance.company_confidentiality.strategicBusinessDocument.predefined");
		sbd.setEditable(false);
		sbd.setCategory(company);
	
		Matcher matcherSBD = new Matcher();
		matcherSBD.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgumentSBD = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument = new MatcherArgument();
		nonCascadingArgumentSBD.setArgument(sbdKeyword);
		matcherArgument.setCoupledMatcher(matcherSBD);
		matcherArgument.setCoupledArgument(nonCascadingArgumentSBD);
		List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
		matcherArguments.add(matcherArgument);
		matcherSBD.setMatcherArguments(matcherArguments);
		
		InformationFeature informationFeature = new InformationFeature();
		informationFeature.setThreshold(new Long(10));
		informationFeature.setMatcher(matcherSBD);
		matcherSBD.setCoupledInformationFeature(informationFeature);
		
		InformationDescription informationDescription = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		informationDescription.setDistanceEnabled(true);
		informationDescription.setDistance(2000);
		ifts.add(informationFeature);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey("informationType.compliance.company_confidentiality.strategicBusinessDocuments");
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);

		inventoryItem.setCategory(sbd);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItem);
		sbd.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(company);
		getHibernateTemplate().saveOrUpdate(sbd);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
	}
}
