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

public class _000_00048_GLBA_Names_with_Sensitive_diesease_drug extends AbstractGranule {

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
					.add(Restrictions.eq("descriptionKey", "sensitive.drugs.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list3 = getHibernateTemplate().findByCriteria(criteria3);
		BundledKeywordGroup keywordGroupDrugNames = DAOUtil.getSingleResult(list3);
		
		DetachedCriteria criteria4 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("descriptionKey", "sensitive.diseases.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list4 = getHibernateTemplate().findByCriteria(criteria4);
		BundledKeywordGroup keywordGroupDiseaseNames = DAOUtil.getSingleResult(list4);
		
		DetachedCriteria criteria5 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("descriptionKey", "names.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list5 = getHibernateTemplate().findByCriteria(criteria5);
		BundledKeywordGroup names = DAOUtil.getSingleResult(list5);
		
		InventoryCategory nameWithDiseaseOrDrug = new InventoryCategory();
		nameWithDiseaseOrDrug.setNameKey("inventory.compliance.finance.glba.name_disease_drug.predefined");
		nameWithDiseaseOrDrug.setEditable(false);
		nameWithDiseaseOrDrug.setCategory(glba);
		
		Matcher matcherNames = new Matcher();
		matcherNames.setFunctionName("keyword_group");
		
		Matcher matcherNames1 = new Matcher();
		matcherNames1.setFunctionName("keyword_group");
	
		Matcher matcherKG = new Matcher();
		matcherKG.setFunctionName("keyword_group");
		
		Matcher matcherKG1 = new Matcher();
		matcherKG1.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgumentDrug = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument = new MatcherArgument();
		nonCascadingArgumentDrug.setArgument(keywordGroupDrugNames);
		matcherArgument.setCoupledMatcher(matcherKG);
		matcherArgument.setCoupledArgument(nonCascadingArgumentDrug);
		List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
		matcherArguments.add(matcherArgument);
		matcherKG.setMatcherArguments(matcherArguments);
		
		NonCascadingArgument nonCascadingArgumentDisease = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument1 = new MatcherArgument();
		nonCascadingArgumentDisease.setArgument(keywordGroupDiseaseNames);
		matcherArgument1.setCoupledMatcher(matcherKG1);
		matcherArgument1.setCoupledArgument(nonCascadingArgumentDisease);
		List<MatcherArgument> matcherArguments1 = new ArrayList<MatcherArgument>();
		matcherArguments1.add(matcherArgument1);
		matcherKG1.setMatcherArguments(matcherArguments1);
		
		NonCascadingArgument nonCascadingArgumentNames = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument2 = new MatcherArgument();
		nonCascadingArgumentNames.setArgument(names);
		matcherArgument2.setCoupledMatcher(matcherNames);
		matcherArgument2.setCoupledArgument(nonCascadingArgumentNames);
		List<MatcherArgument> matcherArguments2 = new ArrayList<MatcherArgument>();
		matcherArguments2.add(matcherArgument2);
		matcherNames.setMatcherArguments(matcherArguments2);
		
		NonCascadingArgument nonCascadingArgumentNames1 = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument3 = new MatcherArgument();
		nonCascadingArgumentNames1.setArgument(names);
		matcherArgument3.setCoupledMatcher(matcherNames1);
		matcherArgument3.setCoupledArgument(nonCascadingArgumentNames1);
		List<MatcherArgument> matcherArguments3 = new ArrayList<MatcherArgument>();
		matcherArguments3.add(matcherArgument3);
		matcherNames1.setMatcherArguments(matcherArguments3);
		
		InformationFeature informationFeatureNames = new InformationFeature();
		informationFeatureNames.setThreshold(new Long(1));
		informationFeatureNames.setMatcher(matcherNames);
		matcherNames.setCoupledInformationFeature(informationFeatureNames);
		
		InformationFeature informationFeatureNames1 = new InformationFeature();
		informationFeatureNames1.setThreshold(new Long(1));
		informationFeatureNames1.setMatcher(matcherNames1);
		matcherNames1.setCoupledInformationFeature(informationFeatureNames1);
		
		InformationFeature informationFeatureDrug = new InformationFeature();
		informationFeatureDrug.setThreshold(new Long(1));
		informationFeatureDrug.setMatcher(matcherKG);
		matcherKG.setCoupledInformationFeature(informationFeatureDrug);
		
		InformationFeature informationFeatureDisease = new InformationFeature();
		informationFeatureDisease.setThreshold(new Long(1));
		informationFeatureDisease.setMatcher(matcherKG1);
		matcherKG1.setCoupledInformationFeature(informationFeatureDisease);
		
		InformationDescription informationDescription = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		informationDescription.setDistanceEnabled(true);
		informationDescription.setDistance(100);
		ifts.add(informationFeatureNames);
		ifts.add(informationFeatureDrug);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey("informationType.compliance.finance.glba.sensitive.name_drug");
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);
		
		InformationDescription informationDescription1 = new InformationDescription();
		List<InformationFeature> ifts1 = new ArrayList<InformationFeature>();
		informationDescription1.setDistanceEnabled(true);
		informationDescription1.setDistance(100);	
		ifts1.add(informationFeatureNames1);
		ifts1.add(informationFeatureDisease);
		informationDescription1.setFeatures(ifts1);
		
		InformationType informationType1 = new InformationType();
		informationType1.setInformationDescription(informationDescription1);
		informationType1.setDataFormats(dfs);
		
		InventoryItem inventoryItem1 = new InventoryItem();
		inventoryItem1.setNameKey("informationType.compliance.finance.glba.sensitive.name_disease");
		inventoryItem1.setItem(informationType1);
		informationType1.setCoupledInventoryItem(inventoryItem1);
		
		inventoryItem.setCategory(nameWithDiseaseOrDrug);
		inventoryItem1.setCategory(nameWithDiseaseOrDrug);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItem);
		dtc.add(inventoryItem1);
		nameWithDiseaseOrDrug.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(glba);
		getHibernateTemplate().saveOrUpdate(nameWithDiseaseOrDrug);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
		getHibernateTemplate().saveOrUpdate(inventoryItem1);
		//getHibernateTemplate().saveOrUpdate(nonCascadingArgument);
	}
}
