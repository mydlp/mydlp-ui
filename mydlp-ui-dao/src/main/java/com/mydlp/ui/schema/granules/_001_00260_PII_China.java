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
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.domain.Matcher;
import com.mydlp.ui.domain.MatcherArgument;
import com.mydlp.ui.domain.NonCascadingArgument;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00260_PII_China extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.personal_information.china.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory piiChina = DAOUtil.getSingleResult(list);
		
		DetachedCriteria criteria2 = 
				DetachedCriteria.forClass(DataFormat.class)
					.add(Restrictions.eq("nameKey", "dataFormat.all.label"));
		@SuppressWarnings("unchecked")
		List<DataFormat> list2 = getHibernateTemplate().findByCriteria(criteria2);
		DataFormat df = DAOUtil.getSingleResult(list2);
		
		DetachedCriteria criteria0 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", "chinese.addressTerms.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list0 = getHibernateTemplate().findByCriteria(criteria0);
		BundledKeywordGroup keywordGroupChineseAddressTerms = DAOUtil.getSingleResult(list0);
		
		DetachedCriteria criteria3 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", "chinese.lastnames.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list3 = getHibernateTemplate().findByCriteria(criteria3);
		BundledKeywordGroup keywordGroupChineseLastNames = DAOUtil.getSingleResult(list3);
		
		DetachedCriteria criteria4 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", "cities.china.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list4 = getHibernateTemplate().findByCriteria(criteria4);
		BundledKeywordGroup keywordGroupChineseCities = DAOUtil.getSingleResult(list4);
		
		DetachedCriteria criteria5 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", "regions.china.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list5 = getHibernateTemplate().findByCriteria(criteria5);
		BundledKeywordGroup keywordGroupChineseRegions = DAOUtil.getSingleResult(list5);
		
		DetachedCriteria criteria6 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", "cities.hongkong.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list6 = getHibernateTemplate().findByCriteria(criteria6);
		BundledKeywordGroup keywordGroupHongkongCities = DAOUtil.getSingleResult(list6);
		
		DetachedCriteria criteria7 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", "regions.hongkong.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list7 = getHibernateTemplate().findByCriteria(criteria7);
		BundledKeywordGroup keywordGroupHongkongRegions = DAOUtil.getSingleResult(list7);
		
		// Chinese Name with Lastname
		{
			Matcher matcherCN = new Matcher();
			matcherCN.setFunctionName("chinese_name");
			
			Matcher matcherKGChineseLastnames = new Matcher();
			matcherKGChineseLastnames.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentChineseLastNames = new NonCascadingArgument(); 	
			MatcherArgument matcherArgument = new MatcherArgument();
			nonCascadingArgumentChineseLastNames.setArgument(keywordGroupChineseLastNames);
			matcherArgument.setCoupledMatcher(matcherKGChineseLastnames);
			matcherArgument.setCoupledArgument(nonCascadingArgumentChineseLastNames);
			List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
			matcherArguments.add(matcherArgument);
			matcherKGChineseLastnames.setMatcherArguments(matcherArguments);
			
			InformationFeature informationFeatureCN = new InformationFeature();
			informationFeatureCN.setThreshold(new Long(1));
			informationFeatureCN.setMatcher(matcherCN);
			matcherCN.setCoupledInformationFeature(informationFeatureCN);
			
			
			InformationFeature informationFeatureCLN = new InformationFeature();
			informationFeatureCLN.setThreshold(new Long(1));
			informationFeatureCLN.setMatcher(matcherKGChineseLastnames);
			matcherKGChineseLastnames.setCoupledInformationFeature(informationFeatureCLN);
			
			InformationDescription informationDescription = new InformationDescription();
			List<InformationFeature> ifts = new ArrayList<InformationFeature>();
			informationDescription.setDistanceEnabled(true);
			informationDescription.setDistance(8);
			ifts.add(informationFeatureCN);
			ifts.add(informationFeatureCLN);
			informationDescription.setFeatures(ifts);
			
			InformationType informationType = new InformationType();
			informationType.setInformationDescription(informationDescription);
			List<DataFormat> dfs = new ArrayList<DataFormat>();
			dfs.add(df);
			informationType.setDataFormats(dfs);
			
			InventoryItem inventoryItem = new InventoryItem();
			inventoryItem.setNameKey("inventory.compliance.personal_information.china.chinese_name_lastname");
			inventoryItem.setItem(informationType);
			informationType.setCoupledInventoryItem(inventoryItem);
			
			inventoryItem.setCategory(piiChina);
			
			getHibernateTemplate().saveOrUpdate(inventoryItem);
		}
		
		// Chinese Address with Name
		{
			Matcher matcherCN = new Matcher();
			matcherCN.setFunctionName("chinese_name");

			InformationFeature informationFeatureCN = new InformationFeature();
			informationFeatureCN.setThreshold(new Long(1));
			informationFeatureCN.setMatcher(matcherCN);
			matcherCN.setCoupledInformationFeature(informationFeatureCN);
			
			Matcher matcherKGChineseLastnames = new Matcher();
			matcherKGChineseLastnames.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentChineseLastNames = new NonCascadingArgument(); 
			{
				MatcherArgument matcherArgument = new MatcherArgument();
				nonCascadingArgumentChineseLastNames.setArgument(keywordGroupChineseLastNames);
				matcherArgument.setCoupledMatcher(matcherKGChineseLastnames);
				matcherArgument.setCoupledArgument(nonCascadingArgumentChineseLastNames);
				List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
				matcherArguments.add(matcherArgument);
				matcherKGChineseLastnames.setMatcherArguments(matcherArguments);
			}
			
			InformationFeature informationFeatureCLN = new InformationFeature();
			informationFeatureCLN.setThreshold(new Long(1));
			informationFeatureCLN.setMatcher(matcherKGChineseLastnames);
			matcherKGChineseLastnames.setCoupledInformationFeature(informationFeatureCLN);
			
			Matcher matcherKGChineseCities = new Matcher();
			matcherKGChineseCities.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentChineseCities = new NonCascadingArgument(); 	
			{
				MatcherArgument matcherArgument = new MatcherArgument();
				nonCascadingArgumentChineseCities.setArgument(keywordGroupChineseCities);
				matcherArgument.setCoupledMatcher(matcherKGChineseCities);
				matcherArgument.setCoupledArgument(nonCascadingArgumentChineseCities);
				List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
				matcherArguments.add(matcherArgument);
				matcherKGChineseCities.setMatcherArguments(matcherArguments);
			}
		
			InformationFeature informationFeatureCC = new InformationFeature();
			informationFeatureCC.setThreshold(new Long(1));
			informationFeatureCC.setMatcher(matcherKGChineseCities);
			matcherKGChineseCities.setCoupledInformationFeature(informationFeatureCC);
			
			Matcher matcherKGChineseRegions = new Matcher();
			matcherKGChineseRegions.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentChineseRegions = new NonCascadingArgument(); 	
			{
				MatcherArgument matcherArgument = new MatcherArgument();
				nonCascadingArgumentChineseRegions.setArgument(keywordGroupChineseRegions);
				matcherArgument.setCoupledMatcher(matcherKGChineseRegions);
				matcherArgument.setCoupledArgument(nonCascadingArgumentChineseRegions);
				List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
				matcherArguments.add(matcherArgument);
				matcherKGChineseRegions.setMatcherArguments(matcherArguments);
			}
			
			InformationFeature informationFeatureCR = new InformationFeature();
			informationFeatureCR.setThreshold(new Long(1));
			informationFeatureCR.setMatcher(matcherKGChineseRegions);
			matcherKGChineseRegions.setCoupledInformationFeature(informationFeatureCR);
			
			Matcher matcherKGChineseAddressTerms = new Matcher();
			matcherKGChineseAddressTerms.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentChineseAddressTerms = new NonCascadingArgument(); 	
			{
				MatcherArgument matcherArgument = new MatcherArgument();
				nonCascadingArgumentChineseAddressTerms.setArgument(keywordGroupChineseAddressTerms);
				matcherArgument.setCoupledMatcher(matcherKGChineseAddressTerms);
				matcherArgument.setCoupledArgument(nonCascadingArgumentChineseAddressTerms);
				List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
				matcherArguments.add(matcherArgument);
				matcherKGChineseAddressTerms.setMatcherArguments(matcherArguments);
			}
			
			InformationFeature informationFeatureCAT = new InformationFeature();
			informationFeatureCAT.setThreshold(new Long(1));
			informationFeatureCAT.setMatcher(matcherKGChineseAddressTerms);
			matcherKGChineseAddressTerms.setCoupledInformationFeature(informationFeatureCAT);
			
			InformationDescription informationDescription = new InformationDescription();
			List<InformationFeature> ifts = new ArrayList<InformationFeature>();
			informationDescription.setDistanceEnabled(true);
			informationDescription.setDistance(24);
			ifts.add(informationFeatureCN);
			ifts.add(informationFeatureCLN);
			ifts.add(informationFeatureCC);
			ifts.add(informationFeatureCR);
			ifts.add(informationFeatureCAT);
			informationDescription.setFeatures(ifts);
			
			InformationType informationType = new InformationType();
			informationType.setInformationDescription(informationDescription);
			List<DataFormat> dfs = new ArrayList<DataFormat>();
			dfs.add(df);
			informationType.setDataFormats(dfs);
			
			InventoryItem inventoryItem = new InventoryItem();
			inventoryItem.setNameKey("inventory.compliance.personal_information.china.chinese_address_name");
			inventoryItem.setItem(informationType);
			informationType.setCoupledInventoryItem(inventoryItem);
			
			inventoryItem.setCategory(piiChina);
			
			getHibernateTemplate().saveOrUpdate(inventoryItem);
		}
		
		// Hong Kong Address with Name
		{
			Matcher matcherCN = new Matcher();
			matcherCN.setFunctionName("chinese_name");

			InformationFeature informationFeatureCN = new InformationFeature();
			informationFeatureCN.setThreshold(new Long(1));
			informationFeatureCN.setMatcher(matcherCN);
			matcherCN.setCoupledInformationFeature(informationFeatureCN);
			
			Matcher matcherKGChineseLastnames = new Matcher();
			matcherKGChineseLastnames.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentChineseLastNames = new NonCascadingArgument(); 
			{
				MatcherArgument matcherArgument = new MatcherArgument();
				nonCascadingArgumentChineseLastNames.setArgument(keywordGroupChineseLastNames);
				matcherArgument.setCoupledMatcher(matcherKGChineseLastnames);
				matcherArgument.setCoupledArgument(nonCascadingArgumentChineseLastNames);
				List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
				matcherArguments.add(matcherArgument);
				matcherKGChineseLastnames.setMatcherArguments(matcherArguments);
			}
			
			InformationFeature informationFeatureCLN = new InformationFeature();
			informationFeatureCLN.setThreshold(new Long(1));
			informationFeatureCLN.setMatcher(matcherKGChineseLastnames);
			matcherKGChineseLastnames.setCoupledInformationFeature(informationFeatureCLN);
			
			Matcher matcherKGHongkongCities = new Matcher();
			matcherKGHongkongCities.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentHongkongCities = new NonCascadingArgument(); 	
			{
				MatcherArgument matcherArgument = new MatcherArgument();
				nonCascadingArgumentHongkongCities.setArgument(keywordGroupHongkongCities);
				matcherArgument.setCoupledMatcher(matcherKGHongkongCities);
				matcherArgument.setCoupledArgument(nonCascadingArgumentHongkongCities);
				List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
				matcherArguments.add(matcherArgument);
				matcherKGHongkongCities.setMatcherArguments(matcherArguments);
			}
			
			InformationFeature informationFeatureHC = new InformationFeature();
			informationFeatureHC.setThreshold(new Long(1));
			informationFeatureHC.setMatcher(matcherKGHongkongCities);
			matcherKGHongkongCities.setCoupledInformationFeature(informationFeatureHC);
			
			Matcher matcherKGHongkongRegions = new Matcher();
			matcherKGHongkongRegions.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentHongkongRegions = new NonCascadingArgument(); 	
			{
				MatcherArgument matcherArgument = new MatcherArgument();
				nonCascadingArgumentHongkongRegions.setArgument(keywordGroupHongkongRegions);
				matcherArgument.setCoupledMatcher(matcherKGHongkongRegions);
				matcherArgument.setCoupledArgument(nonCascadingArgumentHongkongRegions);
				List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
				matcherArguments.add(matcherArgument);
				matcherKGHongkongRegions.setMatcherArguments(matcherArguments);
			}
			
			InformationFeature informationFeatureHR = new InformationFeature();
			informationFeatureHR.setThreshold(new Long(1));
			informationFeatureHR.setMatcher(matcherKGHongkongRegions);
			matcherKGHongkongRegions.setCoupledInformationFeature(informationFeatureHR);
			
			Matcher matcherKGChineseAddressTerms = new Matcher();
			matcherKGChineseAddressTerms.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentChineseAddressTerms = new NonCascadingArgument(); 	
			{
				MatcherArgument matcherArgument = new MatcherArgument();
				nonCascadingArgumentChineseAddressTerms.setArgument(keywordGroupChineseAddressTerms);
				matcherArgument.setCoupledMatcher(matcherKGChineseAddressTerms);
				matcherArgument.setCoupledArgument(nonCascadingArgumentChineseAddressTerms);
				List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
				matcherArguments.add(matcherArgument);
				matcherKGChineseAddressTerms.setMatcherArguments(matcherArguments);
			}
			
			InformationFeature informationFeatureCAT = new InformationFeature();
			informationFeatureCAT.setThreshold(new Long(1));
			informationFeatureCAT.setMatcher(matcherKGChineseAddressTerms);
			matcherKGChineseAddressTerms.setCoupledInformationFeature(informationFeatureCAT);
			
			InformationDescription informationDescription = new InformationDescription();
			List<InformationFeature> ifts = new ArrayList<InformationFeature>();
			informationDescription.setDistanceEnabled(true);
			informationDescription.setDistance(24);
			ifts.add(informationFeatureCN);
			ifts.add(informationFeatureCLN);
			ifts.add(informationFeatureHC);
			ifts.add(informationFeatureHR);
			ifts.add(informationFeatureCAT);
			informationDescription.setFeatures(ifts);
			
			InformationType informationType = new InformationType();
			informationType.setInformationDescription(informationDescription);
			List<DataFormat> dfs = new ArrayList<DataFormat>();
			dfs.add(df);
			informationType.setDataFormats(dfs);
			
			InventoryItem inventoryItem = new InventoryItem();
			inventoryItem.setNameKey("inventory.compliance.personal_information.china.hongkong_address_name");
			inventoryItem.setItem(informationType);
			informationType.setCoupledInventoryItem(inventoryItem);
			
			inventoryItem.setCategory(piiChina);
			
			getHibernateTemplate().saveOrUpdate(inventoryItem);
		}
		
	}
}
