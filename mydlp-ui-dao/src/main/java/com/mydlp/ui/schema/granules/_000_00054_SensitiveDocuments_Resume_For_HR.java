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

public class _000_00054_SensitiveDocuments_Resume_For_HR extends AbstractGranule {

	@Override
	protected void callback() {
			
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.company_confidentiality.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory company = DAOUtil.getSingleResult(list);
		
		DetachedCriteria criteria2 = 
				DetachedCriteria.forClass(DataFormat.class)
					.add(Restrictions.eq("nameKey", "dataFormat.all.label"));
		@SuppressWarnings("unchecked")
		List<DataFormat> list2 = getHibernateTemplate().findByCriteria(criteria2);
		DataFormat df = DAOUtil.getSingleResult(list2);
		
		DetachedCriteria criteria3 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", "resume.for.hr.cv.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list3 = getHibernateTemplate().findByCriteria(criteria3);
		BundledKeywordGroup resumeForHR = DAOUtil.getSingleResult(list3);
		
		InventoryCategory hr = new InventoryCategory();
		hr.setNameKey("inventory.compliance.company_confidentiality.resumeForHR.predefined");
		hr.setEditable(false);
		hr.setCategory(company);
	
		Matcher matcher = new Matcher();
		matcher.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgument = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument = new MatcherArgument();
		nonCascadingArgument.setArgument(resumeForHR);
		matcherArgument.setCoupledMatcher(matcher);
		matcherArgument.setCoupledArgument(nonCascadingArgument);
		List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
		matcherArguments.add(matcherArgument);
		matcher.setMatcherArguments(matcherArguments);
		
		InformationFeature informationFeature = new InformationFeature();
		informationFeature.setThreshold(new Long(10));
		informationFeature.setMatcher(matcher);
		matcher.setCoupledInformationFeature(informationFeature);
		
		InformationDescription informationDescription = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		informationDescription.setDistanceEnabled(true);
		informationDescription.setDistance(2000);
		ifts.add(informationFeature);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey("informationType.compliance.company_confidentiality.resumeForHR.CV");
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);
		
		inventoryItem.setCategory(hr);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItem);
		hr.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(company);
		getHibernateTemplate().saveOrUpdate(hr);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
	}
}
