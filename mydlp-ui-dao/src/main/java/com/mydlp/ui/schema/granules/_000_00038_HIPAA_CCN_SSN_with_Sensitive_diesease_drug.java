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

public class _000_00038_HIPAA_CCN_SSN_with_Sensitive_diesease_drug extends AbstractGranule {

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
		
		DetachedCriteria criteria4 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("descriptionKey", "common.diseases.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list4 = getHibernateTemplate().findByCriteria(criteria4);
		BundledKeywordGroup keywordGroupDiseaseNames = DAOUtil.getSingleResult(list4);
		
		/*HIPAA-CCN With Common Disease*/
		
		Matcher matcherCC = new Matcher();
		matcherCC.setFunctionName("cc");
	
		Matcher matcherKG = new Matcher();
		matcherKG.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgumentDisease = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument = new MatcherArgument();
		nonCascadingArgumentDisease.setArgument(keywordGroupDiseaseNames);
		matcherArgument.setCoupledMatcher(matcherKG);
		matcherArgument.setCoupledArgument(nonCascadingArgumentDisease);
		List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
		matcherArguments.add(matcherArgument);
		matcherKG.setMatcherArguments(matcherArguments);
		
		InformationFeature informationFeatureCC = new InformationFeature();
		informationFeatureCC.setThreshold(new Long(1));
		informationFeatureCC.setMatcher(matcherCC);
		matcherCC.setCoupledInformationFeature(informationFeatureCC);
		
		InformationFeature informationFeatureDisease = new InformationFeature();
		informationFeatureDisease.setThreshold(new Long(1));
		informationFeatureDisease.setMatcher(matcherKG);
		matcherKG.setCoupledInformationFeature(informationFeatureDisease);
		
		InformationDescription informationDescription = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		informationDescription.setDistanceEnabled(false);
		informationDescription.setDistance(75);
		ifts.add(informationFeatureCC);
		ifts.add(informationFeatureDisease);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey("informationType.compliance.federal_regulations.hipaa.common.ccn_disease");
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);

		/*End of HIPAA-CCN With Disease or Drug*/
		
		/*HIPAA-SSN With Common Disease*/
		Matcher matcherSSN = new Matcher();
		matcherSSN.setFunctionName("ssn");

		Matcher matcherKG1 = new Matcher();
		matcherKG1.setFunctionName("keyword_group");

		NonCascadingArgument nonCascadingArgumentDiseaseSSN = new NonCascadingArgument(); 	
		MatcherArgument matcherArgumentSSN = new MatcherArgument();
		nonCascadingArgumentDiseaseSSN.setArgument(keywordGroupDiseaseNames);
		matcherArgumentSSN.setCoupledMatcher(matcherKG1);
		matcherArgumentSSN.setCoupledArgument(nonCascadingArgumentDiseaseSSN);
		List<MatcherArgument> matcherArgumentsSSN = new ArrayList<MatcherArgument>();
		matcherArgumentsSSN.add(matcherArgumentSSN);
		matcherKG1.setMatcherArguments(matcherArgumentsSSN);

		InformationFeature informationFeatureSSN = new InformationFeature();
		informationFeatureSSN.setThreshold(new Long(1));
		informationFeatureSSN.setMatcher(matcherSSN);
		matcherSSN.setCoupledInformationFeature(informationFeatureSSN);
		
		InformationFeature informationFeatureDiseaseSSN = new InformationFeature();
		informationFeatureDiseaseSSN.setThreshold(new Long(1));
		informationFeatureDiseaseSSN.setMatcher(matcherKG1);
		matcherKG1.setCoupledInformationFeature(informationFeatureDiseaseSSN);
		
		InformationDescription informationDescriptionSSN = new InformationDescription();
		List<InformationFeature> iftsSSN = new ArrayList<InformationFeature>();
		informationDescriptionSSN.setDistanceEnabled(false);
		informationDescriptionSSN.setDistance(75);
		iftsSSN.add(informationFeatureSSN);
		iftsSSN.add(informationFeatureDiseaseSSN);
		informationDescriptionSSN.setFeatures(iftsSSN);
		
		InformationType informationTypeSSN = new InformationType();
		informationTypeSSN.setInformationDescription(informationDescriptionSSN);
		informationTypeSSN.setDataFormats(dfs);
		
		InventoryItem inventoryItemSSN = new InventoryItem();
		inventoryItemSSN.setNameKey("informationType.compliance.federal_regulations.hipaa.common.ssn_disease");
		inventoryItemSSN.setItem(informationTypeSSN);
		informationTypeSSN.setCoupledInventoryItem(inventoryItemSSN);
				
		/*End of HIPAA-SSN With Disease or Drug*/
		
		inventoryItemSSN.setCategory(hipaa);
		inventoryItem.setCategory(hipaa);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItemSSN);
		dtc.add(inventoryItem);
		hipaa.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(hipaa);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
		getHibernateTemplate().saveOrUpdate(inventoryItemSSN);
		//getHibernateTemplate().saveOrUpdate(nonCascadingArgument);
	}
}
