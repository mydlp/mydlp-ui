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

public class _000_00037_HIPAA_CCN_SSN_with_Common_diesease extends AbstractGranule {

	@Override
	protected void callback() {
			
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.federal_regulations.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory federalRegulations = DAOUtil.getSingleResult(list);
		
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
		
		InventoryCategory hipaa = new InventoryCategory();
		hipaa.setNameKey("inventory.compliance.federal_regulations.hipaa.predefined");
		hipaa.setEditable(false);
		hipaa.setCategory(federalRegulations);
		
		/*HIPAA-CCN With Disease or Drug*/
		
		InventoryCategory ccnWithDiseaseOrDrug = new InventoryCategory();
		ccnWithDiseaseOrDrug.setNameKey("inventory.compliance.federal_regulations.hipaa.sensitive.ccn_disease/drug.predefined");
		ccnWithDiseaseOrDrug.setEditable(false);
		ccnWithDiseaseOrDrug.setCategory(hipaa);
		
		Matcher matcherCC = new Matcher();
		matcherCC.setFunctionName("cc");
		
		Matcher matcherCC1 = new Matcher();
		matcherCC1.setFunctionName("cc");
	
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
		
		InformationFeature informationFeatureCC = new InformationFeature();
		informationFeatureCC.setThreshold(new Long(1));
		informationFeatureCC.setMatcher(matcherCC);
		matcherCC.setCoupledInformationFeature(informationFeatureCC);
		
		InformationFeature informationFeatureCC1 = new InformationFeature();
		informationFeatureCC1.setThreshold(new Long(1));
		informationFeatureCC1.setMatcher(matcherCC1);
		matcherCC1.setCoupledInformationFeature(informationFeatureCC1);
		
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
		ifts.add(informationFeatureCC);
		ifts.add(informationFeatureDrug);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey("informationType.compliance.federal_regulations.hipaa.sensitive.ccn_drug");
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);
		
		InformationDescription informationDescription1 = new InformationDescription();
		List<InformationFeature> ifts1 = new ArrayList<InformationFeature>();
		informationDescription1.setDistanceEnabled(true);
		informationDescription1.setDistance(100);	
		ifts1.add(informationFeatureCC1);
		ifts1.add(informationFeatureDisease);
		informationDescription1.setFeatures(ifts1);
		
		InformationType informationType1 = new InformationType();
		informationType1.setInformationDescription(informationDescription1);
		informationType1.setDataFormats(dfs);
		
		InventoryItem inventoryItem1 = new InventoryItem();
		inventoryItem1.setNameKey("informationType.compliance.federal_regulations.hipaa.sensitive.ccn_disease");
		inventoryItem1.setItem(informationType1);
		informationType1.setCoupledInventoryItem(inventoryItem1);
		
		inventoryItem.setCategory(ccnWithDiseaseOrDrug);
		inventoryItem1.setCategory(ccnWithDiseaseOrDrug);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItem);
		dtc.add(inventoryItem1);
		ccnWithDiseaseOrDrug.setChildren(dtc);

		/*End of HIPAA-CCN With Disease or Drug*/
		
		/*HIPAA-SSN With Disease or Drug*/
		
		InventoryCategory ssnWithDiseaseOrDrug = new InventoryCategory();
		ssnWithDiseaseOrDrug.setNameKey("inventory.compliance.federal_regulations.hipaa.sensitive.ssn_disease/drug.predefined");
		ssnWithDiseaseOrDrug.setEditable(false);
		ssnWithDiseaseOrDrug.setCategory(hipaa);
		
		Matcher matcherSSN = new Matcher();
		matcherSSN.setFunctionName("ssn");
		
		Matcher matcherSSN1 = new Matcher();
		matcherSSN1.setFunctionName("ssn");
	
		Matcher matcherKG2 = new Matcher();
		matcherKG2.setFunctionName("keyword_group");
		
		Matcher matcherKG3 = new Matcher();
		matcherKG3.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgumentDrugSSN = new NonCascadingArgument(); 	
		MatcherArgument matcherArgumentSSN = new MatcherArgument();
		nonCascadingArgumentDrugSSN.setArgument(keywordGroupDrugNames);
		matcherArgumentSSN.setCoupledMatcher(matcherKG2);
		matcherArgumentSSN.setCoupledArgument(nonCascadingArgumentDrugSSN);
		List<MatcherArgument> matcherArgumentsSSN = new ArrayList<MatcherArgument>();
		matcherArgumentsSSN.add(matcherArgumentSSN);
		matcherKG2.setMatcherArguments(matcherArgumentsSSN);
		
		NonCascadingArgument nonCascadingArgumentDiseaseSSN = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument1SSN = new MatcherArgument();
		nonCascadingArgumentDiseaseSSN.setArgument(keywordGroupDiseaseNames);
		matcherArgument1SSN.setCoupledMatcher(matcherKG3);
		matcherArgument1SSN.setCoupledArgument(nonCascadingArgumentDiseaseSSN);
		List<MatcherArgument> matcherArguments1SSN = new ArrayList<MatcherArgument>();
		matcherArguments1SSN.add(matcherArgument1SSN);
		matcherKG3.setMatcherArguments(matcherArguments1SSN);
		
		InformationFeature informationFeatureSSN = new InformationFeature();
		informationFeatureSSN.setThreshold(new Long(1));
		informationFeatureSSN.setMatcher(matcherSSN);
		matcherSSN.setCoupledInformationFeature(informationFeatureSSN);
		
		InformationFeature informationFeatureSSN1 = new InformationFeature();
		informationFeatureSSN1.setThreshold(new Long(1));
		informationFeatureSSN1.setMatcher(matcherSSN1);
		matcherSSN1.setCoupledInformationFeature(informationFeatureSSN1);
		
		InformationFeature informationFeatureDrugSSN = new InformationFeature();
		informationFeatureDrugSSN.setThreshold(new Long(1));
		informationFeatureDrugSSN.setMatcher(matcherKG2);
		matcherKG2.setCoupledInformationFeature(informationFeatureDrugSSN);
		
		InformationFeature informationFeatureDiseaseSSN = new InformationFeature();
		informationFeatureDiseaseSSN.setThreshold(new Long(1));
		informationFeatureDiseaseSSN.setMatcher(matcherKG3);
		matcherKG3.setCoupledInformationFeature(informationFeatureDiseaseSSN);
		
		InformationDescription informationDescriptionSSN = new InformationDescription();
		List<InformationFeature> iftsSSN = new ArrayList<InformationFeature>();
		informationDescriptionSSN.setDistanceEnabled(true);
		informationDescriptionSSN.setDistance(100);
		iftsSSN.add(informationFeatureSSN);
		iftsSSN.add(informationFeatureDrugSSN);
		informationDescriptionSSN.setFeatures(iftsSSN);
		
		InformationType informationTypeSSN = new InformationType();
		informationTypeSSN.setInformationDescription(informationDescriptionSSN);
		informationTypeSSN.setDataFormats(dfs);
		
		InventoryItem inventoryItemSSN = new InventoryItem();
		inventoryItemSSN.setNameKey("informationType.compliance.federal_regulations.hipaa.sensitive.ssn_drug");
		inventoryItemSSN.setItem(informationTypeSSN);
		informationTypeSSN.setCoupledInventoryItem(inventoryItemSSN);
		
		InformationDescription informationDescriptionSSN1 = new InformationDescription();
		List<InformationFeature> iftsSSN1 = new ArrayList<InformationFeature>();
		informationDescriptionSSN1.setDistanceEnabled(true);
		informationDescriptionSSN1.setDistance(100);	
		iftsSSN1.add(informationFeatureSSN1);
		iftsSSN1.add(informationFeatureDiseaseSSN);
		informationDescriptionSSN1.setFeatures(iftsSSN1);
		
		InformationType informationTypeSSN1 = new InformationType();
		informationTypeSSN1.setInformationDescription(informationDescriptionSSN1);
		informationTypeSSN1.setDataFormats(dfs);
		
		InventoryItem inventoryItemSSN1 = new InventoryItem();
		inventoryItemSSN1.setNameKey("informationType.compliance.federal_regulations.hipaa.sensitive.ssn_disease");
		inventoryItemSSN1.setItem(informationTypeSSN1);
		informationTypeSSN1.setCoupledInventoryItem(inventoryItemSSN1);
		
		inventoryItemSSN.setCategory(ssnWithDiseaseOrDrug);
		inventoryItemSSN1.setCategory(ssnWithDiseaseOrDrug);
		List<InventoryBase> dtcSSN = new ArrayList<InventoryBase>();
		dtcSSN.add(inventoryItemSSN);
		dtcSSN.add(inventoryItemSSN1);
		ssnWithDiseaseOrDrug.setChildren(dtcSSN);
		
		/*End of HIPAA-SSN With Disease or Drug*/
		
		
		
		getHibernateTemplate().saveOrUpdate(federalRegulations);
		getHibernateTemplate().saveOrUpdate(hipaa);
		getHibernateTemplate().saveOrUpdate(ccnWithDiseaseOrDrug);
		getHibernateTemplate().saveOrUpdate(ssnWithDiseaseOrDrug);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
		getHibernateTemplate().saveOrUpdate(inventoryItem1);
		getHibernateTemplate().saveOrUpdate(inventoryItemSSN);
		getHibernateTemplate().saveOrUpdate(inventoryItemSSN1);
		//getHibernateTemplate().saveOrUpdate(nonCascadingArgument);
	}
}
