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
import com.mydlp.ui.domain.MatcherArgument;
import com.mydlp.ui.domain.RegularExpression;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00061_SensitiveDocuments_Sensitive_Keywords extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.company_confidentiality.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory sensitiveDocuments = DAOUtil.getSingleResult(list);
		
		DetachedCriteria criteria2 = 
				DetachedCriteria.forClass(DataFormat.class)
					.add(Restrictions.eq("nameKey", "dataFormat.all.label"));
		@SuppressWarnings("unchecked")
		List<DataFormat> list2 = getHibernateTemplate().findByCriteria(criteria2);
		DataFormat df = DAOUtil.getSingleResult(list2);
		
		InventoryCategory sensitiveKeywords = new InventoryCategory();
		sensitiveKeywords.setNameKey("inventory.compliance.company_confidentiality.sensitiveKeywords.predefined");
		sensitiveKeywords.setEditable(false);
		sensitiveKeywords.setCategory(sensitiveDocuments);
		
		/*Confidential Keyword*/
		RegularExpression regularExpression = new RegularExpression();
		regularExpression.setRegex("confidental");
		
		Matcher matcher = new Matcher();
		matcher.setFunctionName("keyword");
		MatcherArgument matcherArgument = new MatcherArgument();
		matcherArgument.setCoupledMatcher(matcher);
		matcherArgument.setCoupledArgument(regularExpression);
		List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
		matcherArguments.add(matcherArgument);
		matcher.setMatcherArguments(matcherArguments);
		
		InformationFeature informationFeature = new InformationFeature();
		informationFeature.setThreshold(new Long(6));
		informationFeature.setMatcher(matcher);
		matcher.setCoupledInformationFeature(informationFeature);
		
		InformationDescription informationDescription = new InformationDescription();
		//ift.setInformationDescription(id);
		informationDescription.setDistanceEnabled(true);
		informationDescription.setDistance(5000);
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		ifts.add(informationFeature);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey("informationType.compliance.sensitiveDocuments.sensitiveKeywords.confidental");
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);
		/*End of Confidential Keyword*/
		
		/*Top Secret Keyword*/
		RegularExpression regularExpression1 = new RegularExpression();
		regularExpression1.setRegex("top secret");
		
		Matcher matcher1 = new Matcher();
		matcher1.setFunctionName("keyword");
		MatcherArgument matcherArgument1 = new MatcherArgument();
		matcherArgument1.setCoupledMatcher(matcher1);
		matcherArgument1.setCoupledArgument(regularExpression1);
		List<MatcherArgument> matcherArguments1 = new ArrayList<MatcherArgument>();
		matcherArguments1.add(matcherArgument1);
		matcher1.setMatcherArguments(matcherArguments1);
		
		InformationFeature informationFeature1 = new InformationFeature();
		informationFeature1.setThreshold(new Long(6));
		informationFeature1.setMatcher(matcher1);
		matcher1.setCoupledInformationFeature(informationFeature1);
		
		InformationDescription informationDescription1 = new InformationDescription();
		//ift.setInformationDescription(id);
		informationDescription1.setDistanceEnabled(true);
		informationDescription1.setDistance(5000);
		List<InformationFeature> ifts1 = new ArrayList<InformationFeature>();
		ifts1.add(informationFeature1);
		informationDescription1.setFeatures(ifts1);
		
		InformationType informationType1 = new InformationType();
		informationType1.setInformationDescription(informationDescription1);
		informationType1.setDataFormats(dfs);
		
		InventoryItem inventoryItem1 = new InventoryItem();
		inventoryItem1.setNameKey("informationType.compliance.sensitiveDocuments.sensitiveKeywords.topSecret");
		inventoryItem1.setItem(informationType1);
		informationType1.setCoupledInventoryItem(inventoryItem1);
		/*End of Top Secret Keyword*/
		
		/*Restricted Keyword*/
		RegularExpression regularExpression2 = new RegularExpression();
		regularExpression2.setRegex("restricted");
		
		Matcher matcher2 = new Matcher();
		matcher2.setFunctionName("keyword");
		MatcherArgument matcherArgument2 = new MatcherArgument();
		matcherArgument2.setCoupledMatcher(matcher2);
		matcherArgument2.setCoupledArgument(regularExpression2);
		List<MatcherArgument> matcherArguments2 = new ArrayList<MatcherArgument>();
		matcherArguments2.add(matcherArgument2);
		matcher2.setMatcherArguments(matcherArguments2);
		
		InformationFeature informationFeature2 = new InformationFeature();
		informationFeature2.setThreshold(new Long(6));
		informationFeature2.setMatcher(matcher2);
		matcher2.setCoupledInformationFeature(informationFeature2);
		
		InformationDescription informationDescription2 = new InformationDescription();
		//ift.setInformationDescription(id);
		informationDescription2.setDistanceEnabled(true);
		informationDescription2.setDistance(5000);
		List<InformationFeature> ifts2 = new ArrayList<InformationFeature>();
		ifts2.add(informationFeature2);
		informationDescription2.setFeatures(ifts2);
		
		InformationType informationType2 = new InformationType();
		informationType2.setInformationDescription(informationDescription2);
		informationType2.setDataFormats(dfs);
		
		InventoryItem inventoryItem2 = new InventoryItem();
		inventoryItem2.setNameKey("informationType.compliance.sensitiveDocuments.sensitiveKeywords.restricted");
		inventoryItem2.setItem(informationType2);
		informationType2.setCoupledInventoryItem(inventoryItem2);
		/*End of Restricted Keyword*/
		
		/*Sensitive Keyword*/
		RegularExpression regularExpression3 = new RegularExpression();
		regularExpression3.setRegex("sensitive");
		
		Matcher matcher3 = new Matcher();
		matcher3.setFunctionName("keyword");
		MatcherArgument matcherArgument3 = new MatcherArgument();
		matcherArgument3.setCoupledMatcher(matcher3);
		matcherArgument3.setCoupledArgument(regularExpression3);
		List<MatcherArgument> matcherArguments3 = new ArrayList<MatcherArgument>();
		matcherArguments3.add(matcherArgument3);
		matcher3.setMatcherArguments(matcherArguments3);
		
		InformationFeature informationFeature3 = new InformationFeature();
		informationFeature3.setThreshold(new Long(6));
		informationFeature3.setMatcher(matcher3);
		matcher3.setCoupledInformationFeature(informationFeature3);
		
		InformationDescription informationDescription3 = new InformationDescription();
		//ift.setInformationDescription(id);
		informationDescription3.setDistanceEnabled(true);
		informationDescription3.setDistance(5000);
		List<InformationFeature> ifts3 = new ArrayList<InformationFeature>();
		ifts3.add(informationFeature3);
		informationDescription3.setFeatures(ifts3);
		
		InformationType informationType3 = new InformationType();
		informationType3.setInformationDescription(informationDescription3);
		informationType3.setDataFormats(dfs);
		
		InventoryItem inventoryItem3 = new InventoryItem();
		inventoryItem3.setNameKey("informationType.compliance.sensitiveDocuments.sensitiveKeywords.sensitive");
		inventoryItem3.setItem(informationType3);
		informationType3.setCoupledInventoryItem(inventoryItem3);
		/*End of Sensitive Keyword*/
		
		inventoryItem.setCategory(sensitiveKeywords);
		inventoryItem1.setCategory(sensitiveKeywords);
		inventoryItem2.setCategory(sensitiveKeywords);
		inventoryItem3.setCategory(sensitiveKeywords);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItem);
		dtc.add(inventoryItem1);
		dtc.add(inventoryItem2);
		dtc.add(inventoryItem3);
		sensitiveKeywords.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(sensitiveDocuments);
		getHibernateTemplate().saveOrUpdate(sensitiveKeywords);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
		getHibernateTemplate().saveOrUpdate(inventoryItem1);
		getHibernateTemplate().saveOrUpdate(inventoryItem2);
		getHibernateTemplate().saveOrUpdate(inventoryItem3);
	}

}
