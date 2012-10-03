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

public class _000_00047_GLBA_Name_Personal_Finance_Terms extends AbstractGranule {

	@Override
	protected void callback() {
			
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.finance.glba.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory glba = DAOUtil.getSingleResult(list);
		
		DetachedCriteria criteria2 = 
				DetachedCriteria.forClass(DataFormat.class)
					.add(Restrictions.eq("nameKey", "dataFormat.all.label"));
		@SuppressWarnings("unchecked")
		List<DataFormat> list2 = getHibernateTemplate().findByCriteria(criteria2);
		DataFormat df = DAOUtil.getSingleResult(list2);
		
		DetachedCriteria criteria3 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("descriptionKey", "personal.finance.terms.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list3 = getHibernateTemplate().findByCriteria(criteria3);
		BundledKeywordGroup personalFinanceTerms = DAOUtil.getSingleResult(list3);
		
		DetachedCriteria criteria4 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("descriptionKey", "names.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list4 = getHibernateTemplate().findByCriteria(criteria4);
		BundledKeywordGroup names = DAOUtil.getSingleResult(list4);
		
		Matcher matcherName = new Matcher();
		matcherName.setFunctionName("keyword_group");
	
		Matcher matcherPFT = new Matcher();
		matcherPFT.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgumentNames = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument = new MatcherArgument();
		nonCascadingArgumentNames.setArgument(names);
		matcherArgument.setCoupledMatcher(matcherName);
		matcherArgument.setCoupledArgument(nonCascadingArgumentNames);
		List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
		matcherArguments.add(matcherArgument);
		matcherName.setMatcherArguments(matcherArguments);
		
		NonCascadingArgument nonCascadingArgumentPFT = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument1 = new MatcherArgument();
		nonCascadingArgumentPFT.setArgument(personalFinanceTerms);
		matcherArgument1.setCoupledMatcher(matcherPFT);
		matcherArgument1.setCoupledArgument(nonCascadingArgumentPFT);
		List<MatcherArgument> matcherArguments1 = new ArrayList<MatcherArgument>();
		matcherArguments1.add(matcherArgument1);
		matcherPFT.setMatcherArguments(matcherArguments1);
		
		InformationFeature informationFeatureName = new InformationFeature();
		informationFeatureName.setThreshold(new Long(1));
		informationFeatureName.setMatcher(matcherName);
		matcherName.setCoupledInformationFeature(informationFeatureName);
		
		InformationFeature informationFeaturePFT = new InformationFeature();
		informationFeaturePFT.setThreshold(new Long(1));
		informationFeaturePFT.setMatcher(matcherPFT);
		matcherPFT.setCoupledInformationFeature(informationFeaturePFT);
		
		InformationDescription informationDescription = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		informationDescription.setDistanceEnabled(true);
		informationDescription.setDistance(100);
		ifts.add(informationFeatureName);
		ifts.add(informationFeaturePFT);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey("informationType.compliance.finance.glba.name_personalFinanceTerms");
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);

		inventoryItem.setCategory(glba);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItem);
		glba.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(glba);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
	}
}
