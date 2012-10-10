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

public class _000_00053_SensitiveDocuments_SOX extends AbstractGranule {

	protected InventoryItem generateInventoryItem(String queryString, String message, DataFormat df, Long threshold, int distance)
	{
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", queryString));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list = getHibernateTemplate().findByCriteria(criteria);
		BundledKeywordGroup keywordGroup = DAOUtil.getSingleResult(list);
		
		Matcher matcher = new Matcher();
		matcher.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgument10K = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument = new MatcherArgument();
		nonCascadingArgument10K.setArgument(keywordGroup);
		matcherArgument.setCoupledMatcher(matcher);
		matcherArgument.setCoupledArgument(nonCascadingArgument10K);
		List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
		matcherArguments.add(matcherArgument);
		matcher.setMatcherArguments(matcherArguments);
		
		InformationFeature informationFeature = new InformationFeature();
		informationFeature.setThreshold(threshold);
		informationFeature.setMatcher(matcher);
		matcher.setCoupledInformationFeature(informationFeature);
		
		InformationDescription informationDescription = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		informationDescription.setDistanceEnabled(true);
		informationDescription.setDistance(distance);
		ifts.add(informationFeature);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey(message);
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);
		
		return inventoryItem;
	}
	
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
		
		InventoryCategory sox = new InventoryCategory();
		sox.setNameKey("inventory.compliance.company_confidentiality.sox.predefined");
		sox.setEditable(false);
		sox.setCategory(finance);
		
		InventoryCategory category10k = new InventoryCategory();
		category10k.setNameKey("inventory.compliance.company_confidentiality.sox.10k.predefined");
		category10k.setEditable(false);
		category10k.setCategory(sox);
		
		InventoryCategory category10q = new InventoryCategory();
		category10q.setNameKey("inventory.compliance.company_confidentiality.sox.10q.predefined");
		category10q.setEditable(false);
		category10q.setCategory(sox);
		
		InventoryItem inventoryItem = generateInventoryItem("sox.10k.coverPage.keywordList", 
										"informationType.compliance.finance.sox.coverPage.10k", df, new Long(6), 1500);
		InventoryItem inventoryItem1 = generateInventoryItem("sox.10k.contentPage.keywordList", 
										"informationType.compliance.finance.sox.contentPage.10k", df, new Long(12), 3500);
		InventoryItem inventoryItem2 = generateInventoryItem("sox.10k.performanceGraph.keywordList", 
										"informationType.compliance.finance.sox.performanceGraph.10k", df, new Long(2), 200);
		InventoryItem inventoryItem3 = generateInventoryItem("sox.10k.statementPage.keywordList", 
										"informationType.compliance.finance.sox.statement.10k", df, new Long(3), 250);
		InventoryItem inventoryItem4 = generateInventoryItem("sox.10k.dataPage.keywordList", 
										"informationType.compliance.finance.sox.data.10k", df, new Long(3), 500);
		
		InventoryItem inventoryItemq = generateInventoryItem("sox.10q.coverPage.keywordList", 
										"informationType.compliance.finance.sox.coverPage.10q", df, new Long(5), 1500);
		InventoryItem inventoryItemq1 = generateInventoryItem("sox.10q.contentPage.keywordList", 
										"informationType.compliance.finance.sox.contentPage.10q", df, new Long(5), 3000);
		InventoryItem inventoryItemq2 = generateInventoryItem("sox.10q.balancePage.keywordList", 
										"informationType.compliance.finance.sox.balance.10q", df, new Long(6), 1500);
		InventoryItem inventoryItemq3 = generateInventoryItem("sox.10q.information.keywordList", 
										"informationType.compliance.finance.sox.information.10q", df, new Long(4), 2000);
		
		inventoryItem.setCategory(category10k);
		inventoryItem1.setCategory(category10k);
		inventoryItem2.setCategory(category10k);
		inventoryItem3.setCategory(category10k);
		inventoryItem4.setCategory(category10k);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItem);
		dtc.add(inventoryItem1);
		dtc.add(inventoryItem2);
		dtc.add(inventoryItem3);
		dtc.add(inventoryItem4);
		category10k.setChildren(dtc);
		
		inventoryItemq.setCategory(category10q);
		inventoryItemq1.setCategory(category10q);
		inventoryItemq2.setCategory(category10q);
		inventoryItemq3.setCategory(category10q);
		List<InventoryBase> dtc1 = new ArrayList<InventoryBase>();
		dtc1.add(inventoryItemq);
		dtc1.add(inventoryItemq1);
		dtc1.add(inventoryItemq2);
		dtc1.add(inventoryItemq3);
		category10q.setChildren(dtc1);
		
		getHibernateTemplate().saveOrUpdate(finance);
		getHibernateTemplate().saveOrUpdate(sox);
		getHibernateTemplate().saveOrUpdate(category10k);
		getHibernateTemplate().saveOrUpdate(category10q);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
		getHibernateTemplate().saveOrUpdate(inventoryItem1);
		getHibernateTemplate().saveOrUpdate(inventoryItem2);
		getHibernateTemplate().saveOrUpdate(inventoryItem3);
		getHibernateTemplate().saveOrUpdate(inventoryItem4);
		getHibernateTemplate().saveOrUpdate(inventoryItemq);
		getHibernateTemplate().saveOrUpdate(inventoryItemq1);
		getHibernateTemplate().saveOrUpdate(inventoryItemq2);
		getHibernateTemplate().saveOrUpdate(inventoryItemq3);
	}
}
