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

public class _000_00052_SensitiveDocuments_SOX extends AbstractGranule {

	@Override
	protected void callback() {
			
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.finance.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory finance = DAOUtil.getSingleResult(list);
		
		DetachedCriteria criteria2 = 
				DetachedCriteria.forClass(DataFormat.class)
					.add(Restrictions.eq("nameKey", "dataFormat.all.label"));
		@SuppressWarnings("unchecked")
		List<DataFormat> list2 = getHibernateTemplate().findByCriteria(criteria2);
		DataFormat df = DAOUtil.getSingleResult(list2);
		
		DetachedCriteria criteria3 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("descriptionKey", "sox.10k.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list3 = getHibernateTemplate().findByCriteria(criteria3);
		BundledKeywordGroup sox10k = DAOUtil.getSingleResult(list3);
		
		DetachedCriteria criteria4 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("descriptionKey", "sox.10q.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list4 = getHibernateTemplate().findByCriteria(criteria4);
		BundledKeywordGroup sox10q = DAOUtil.getSingleResult(list4);
		
		InventoryCategory sox = new InventoryCategory();
		sox.setNameKey("inventory.compliance.company_confidentiality.sox.predefined");
		sox.setEditable(false);
		sox.setCategory(finance);
	
		Matcher matcher10K = new Matcher();
		matcher10K.setFunctionName("keyword_group");
		
		Matcher matcher10Q = new Matcher();
		matcher10Q.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgument10Q = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument = new MatcherArgument();
		nonCascadingArgument10Q.setArgument(sox10q);
		matcherArgument.setCoupledMatcher(matcher10Q);
		matcherArgument.setCoupledArgument(nonCascadingArgument10Q);
		List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
		matcherArguments.add(matcherArgument);
		matcher10Q.setMatcherArguments(matcherArguments);
		
		NonCascadingArgument nonCascadingArgument10K = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument1 = new MatcherArgument();
		nonCascadingArgument10K.setArgument(sox10k);
		matcherArgument1.setCoupledMatcher(matcher10K);
		matcherArgument1.setCoupledArgument(nonCascadingArgument10K);
		List<MatcherArgument> matcherArguments1 = new ArrayList<MatcherArgument>();
		matcherArguments1.add(matcherArgument1);
		matcher10K.setMatcherArguments(matcherArguments1);
		
		InformationFeature informationFeature = new InformationFeature();
		informationFeature.setThreshold(new Long(1));//must be revised according to the 10K keyword list
		informationFeature.setMatcher(matcher10K);
		matcher10K.setCoupledInformationFeature(informationFeature);
		
		InformationFeature informationFeature1 = new InformationFeature();
		informationFeature1.setThreshold(new Long(1));//must be revised according to the 10K keyword list
		informationFeature1.setMatcher(matcher10Q);
		matcher10Q.setCoupledInformationFeature(informationFeature);
		
		InformationDescription informationDescription = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		informationDescription.setDistanceEnabled(true);
		informationDescription.setDistance(75);
		ifts.add(informationFeature);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey("informationType.compliance.company_confidentiality.sox.10k");
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);
		
		InformationDescription informationDescription1 = new InformationDescription();
		List<InformationFeature> ifts1 = new ArrayList<InformationFeature>();
		informationDescription1.setDistanceEnabled(true);
		informationDescription1.setDistance(75);//must be revised according to the 10K keyword list
		ifts1.add(informationFeature1);
		informationDescription1.setFeatures(ifts1);
		
		InformationType informationType1 = new InformationType();
		informationType1.setInformationDescription(informationDescription1);
		informationType1.setDataFormats(dfs);//must be revised according to the 10K keyword list
		
		InventoryItem inventoryItem1 = new InventoryItem();
		inventoryItem1.setNameKey("informationType.compliance.company_confidentiality.sox.10q");
		inventoryItem1.setItem(informationType1);
		informationType1.setCoupledInventoryItem(inventoryItem1);
		
		inventoryItem.setCategory(sox);
		inventoryItem1.setCategory(sox);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItem);
		dtc.add(inventoryItem1);
		sox.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(finance);
		getHibernateTemplate().saveOrUpdate(sox);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
		getHibernateTemplate().saveOrUpdate(inventoryItem1);
	}
}
