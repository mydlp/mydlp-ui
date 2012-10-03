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

public class _000_00040_HIPAA_Names_Common_Diseases extends AbstractGranule {

	@Override
	protected void callback() {
			
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.federal_regulations.hipaa.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory hipaa = DAOUtil.getSingleResult(list);
		
		DetachedCriteria criteria2 = 
				DetachedCriteria.forClass(DataFormat.class)
					.add(Restrictions.eq("nameKey", "dataFormat.all.label"));
		@SuppressWarnings("unchecked")
		List<DataFormat> list2 = getHibernateTemplate().findByCriteria(criteria2);
		DataFormat df = DAOUtil.getSingleResult(list2);
		
		DetachedCriteria criteria3 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("descriptionKey", "names.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list3 = getHibernateTemplate().findByCriteria(criteria3);
		BundledKeywordGroup names = DAOUtil.getSingleResult(list3);
		
		DetachedCriteria criteria4 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("descriptionKey", "common.diseases.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list4 = getHibernateTemplate().findByCriteria(criteria4);
		BundledKeywordGroup commonDiseases = DAOUtil.getSingleResult(list4);
		
		Matcher matcherCD = new Matcher();
		matcherCD.setFunctionName("keyword_group");
	
		Matcher matcherKG = new Matcher();
		matcherKG.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgumentCD = new NonCascadingArgument(); 	
		MatcherArgument matcherArgumentCD = new MatcherArgument();
		nonCascadingArgumentCD.setArgument(commonDiseases);
		matcherArgumentCD.setCoupledMatcher(matcherCD);
		matcherArgumentCD.setCoupledArgument(nonCascadingArgumentCD);
		List<MatcherArgument> matcherArgumentsCD = new ArrayList<MatcherArgument>();
		matcherArgumentsCD.add(matcherArgumentCD);
		matcherCD.setMatcherArguments(matcherArgumentsCD);
		
		NonCascadingArgument nonCascadingArgumentNames = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument = new MatcherArgument();
		nonCascadingArgumentNames.setArgument(names);
		matcherArgument.setCoupledMatcher(matcherKG);
		matcherArgument.setCoupledArgument(nonCascadingArgumentNames);
		List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
		matcherArguments.add(matcherArgument);
		matcherKG.setMatcherArguments(matcherArguments);
		
		InformationFeature informationFeatureCD = new InformationFeature();
		informationFeatureCD.setThreshold(new Long(1));
		informationFeatureCD.setMatcher(matcherCD);
		matcherCD.setCoupledInformationFeature(informationFeatureCD);
		
		InformationFeature informationFeatureNames = new InformationFeature();
		informationFeatureNames.setThreshold(new Long(1));
		informationFeatureNames.setMatcher(matcherKG);
		matcherKG.setCoupledInformationFeature(informationFeatureNames);
		
		InformationDescription informationDescription = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		informationDescription.setDistanceEnabled(true);
		informationDescription.setDistance(1);
		ifts.add(informationFeatureCD);
		ifts.add(informationFeatureNames);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey("informationType.compliance.federal_regulations.hipaa.names_disease");
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);

		inventoryItem.setCategory(hipaa);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItem);
		hipaa.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(hipaa);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
	}
}
