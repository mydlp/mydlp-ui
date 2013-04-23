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

public class _001_00270_PII_Taiwan extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.personal_information.taiwan.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory piiTaiwan = DAOUtil.getSingleResult(list);
		
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
					.add(Restrictions.eq("nameKey", "taiwanese.lastnames.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list3 = getHibernateTemplate().findByCriteria(criteria3);
		BundledKeywordGroup keywordGroupTaiwaneseLastNames = DAOUtil.getSingleResult(list3);
		
		DetachedCriteria criteria4 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", "cities.taiwan.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list4 = getHibernateTemplate().findByCriteria(criteria4);
		BundledKeywordGroup keywordGroupTaiwaneseCities = DAOUtil.getSingleResult(list4);
		
		DetachedCriteria criteria5 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", "regions.taiwan.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list5 = getHibernateTemplate().findByCriteria(criteria5);
		BundledKeywordGroup keywordGroupTaiwaneseRegions = DAOUtil.getSingleResult(list5);
		
		// Taiwanese Name with Lastname
		{
			Matcher matcherCN = new Matcher();
			matcherCN.setFunctionName("chinese_name");
			
			Matcher matcherKGTaiwaneseLastnames = new Matcher();
			matcherKGTaiwaneseLastnames.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentTaiwaneseLastNames = new NonCascadingArgument(); 	
			MatcherArgument matcherArgument = new MatcherArgument();
			nonCascadingArgumentTaiwaneseLastNames.setArgument(keywordGroupTaiwaneseLastNames);
			matcherArgument.setCoupledMatcher(matcherKGTaiwaneseLastnames);
			matcherArgument.setCoupledArgument(nonCascadingArgumentTaiwaneseLastNames);
			List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
			matcherArguments.add(matcherArgument);
			matcherKGTaiwaneseLastnames.setMatcherArguments(matcherArguments);
			
			InformationFeature informationFeatureCN = new InformationFeature();
			informationFeatureCN.setThreshold(new Long(1));
			informationFeatureCN.setMatcher(matcherCN);
			matcherCN.setCoupledInformationFeature(informationFeatureCN);
			
			
			InformationFeature informationFeatureCLN = new InformationFeature();
			informationFeatureCLN.setThreshold(new Long(1));
			informationFeatureCLN.setMatcher(matcherKGTaiwaneseLastnames);
			matcherKGTaiwaneseLastnames.setCoupledInformationFeature(informationFeatureCLN);
			
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
			inventoryItem.setNameKey("inventory.compliance.personal_information.taiwan.taiwanese_name_lastname");
			inventoryItem.setItem(informationType);
			informationType.setCoupledInventoryItem(inventoryItem);
			
			inventoryItem.setCategory(piiTaiwan);
			
			getHibernateTemplate().saveOrUpdate(inventoryItem);
		}
		
		// Taiwanese Address with Name
		{
			Matcher matcherCN = new Matcher();
			matcherCN.setFunctionName("chinese_name");

			InformationFeature informationFeatureCN = new InformationFeature();
			informationFeatureCN.setThreshold(new Long(1));
			informationFeatureCN.setMatcher(matcherCN);
			matcherCN.setCoupledInformationFeature(informationFeatureCN);
			
			Matcher matcherKGTaiwaneseLastnames = new Matcher();
			matcherKGTaiwaneseLastnames.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentTaiwaneseLastNames = new NonCascadingArgument(); 
			{
				MatcherArgument matcherArgument = new MatcherArgument();
				nonCascadingArgumentTaiwaneseLastNames.setArgument(keywordGroupTaiwaneseLastNames);
				matcherArgument.setCoupledMatcher(matcherKGTaiwaneseLastnames);
				matcherArgument.setCoupledArgument(nonCascadingArgumentTaiwaneseLastNames);
				List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
				matcherArguments.add(matcherArgument);
				matcherKGTaiwaneseLastnames.setMatcherArguments(matcherArguments);
			}
			
			InformationFeature informationFeatureCLN = new InformationFeature();
			informationFeatureCLN.setThreshold(new Long(1));
			informationFeatureCLN.setMatcher(matcherKGTaiwaneseLastnames);
			matcherKGTaiwaneseLastnames.setCoupledInformationFeature(informationFeatureCLN);
			
			Matcher matcherKGTaiwaneseCities = new Matcher();
			matcherKGTaiwaneseCities.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentTaiwaneseCities = new NonCascadingArgument(); 	
			{
				MatcherArgument matcherArgument = new MatcherArgument();
				nonCascadingArgumentTaiwaneseCities.setArgument(keywordGroupTaiwaneseCities);
				matcherArgument.setCoupledMatcher(matcherKGTaiwaneseCities);
				matcherArgument.setCoupledArgument(nonCascadingArgumentTaiwaneseCities);
				List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
				matcherArguments.add(matcherArgument);
				matcherKGTaiwaneseCities.setMatcherArguments(matcherArguments);
			}
			
			InformationFeature informationFeatureCC = new InformationFeature();
			informationFeatureCC.setThreshold(new Long(1));
			informationFeatureCC.setMatcher(matcherKGTaiwaneseCities);
			matcherKGTaiwaneseCities.setCoupledInformationFeature(informationFeatureCC);
			
			Matcher matcherKGTaiwaneseRegions = new Matcher();
			matcherKGTaiwaneseRegions.setFunctionName("keyword_group");
			
			NonCascadingArgument nonCascadingArgumentTaiwaneseRegions = new NonCascadingArgument(); 	
			{
				MatcherArgument matcherArgument = new MatcherArgument();
				nonCascadingArgumentTaiwaneseRegions.setArgument(keywordGroupTaiwaneseRegions);
				matcherArgument.setCoupledMatcher(matcherKGTaiwaneseRegions);
				matcherArgument.setCoupledArgument(nonCascadingArgumentTaiwaneseRegions);
				List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
				matcherArguments.add(matcherArgument);
				matcherKGTaiwaneseRegions.setMatcherArguments(matcherArguments);
			}
			
			InformationFeature informationFeatureCR = new InformationFeature();
			informationFeatureCR.setThreshold(new Long(1));
			informationFeatureCR.setMatcher(matcherKGTaiwaneseRegions);
			matcherKGTaiwaneseRegions.setCoupledInformationFeature(informationFeatureCR);
			
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
			inventoryItem.setNameKey("inventory.compliance.personal_information.taiwan.taiwanese_address_name");
			inventoryItem.setItem(informationType);
			informationType.setCoupledInventoryItem(inventoryItem);
			
			inventoryItem.setCategory(piiTaiwan);
			
			getHibernateTemplate().saveOrUpdate(inventoryItem);
		}
	}
}
