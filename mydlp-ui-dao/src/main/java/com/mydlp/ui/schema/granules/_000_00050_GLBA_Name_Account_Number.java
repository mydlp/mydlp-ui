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

public class _000_00050_GLBA_Name_Account_Number extends AbstractGranule {

	
	protected InventoryItem generateNameWithAccountNumbers(DataFormat df, BundledKeywordGroup names, String account)
	{
		Matcher matcherName = new Matcher();
		matcherName.setFunctionName("keyword_group");
		
		String matcherKey = account + "_digit";
		Matcher accountMacther = new Matcher();
		accountMacther.setFunctionName(matcherKey);
		
		NonCascadingArgument nonCascadingArgumentNames = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument = new MatcherArgument();
		nonCascadingArgumentNames.setArgument(names);
		matcherArgument.setCoupledMatcher(matcherName);
		matcherArgument.setCoupledArgument(nonCascadingArgumentNames);
		List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
		matcherArguments.add(matcherArgument);
		matcherName.setMatcherArguments(matcherArguments);
		
		InformationFeature informationFeatureName = new InformationFeature();
		informationFeatureName.setThreshold(new Long(1));
		informationFeatureName.setMatcher(matcherName);
		matcherName.setCoupledInformationFeature(informationFeatureName);
		
		InformationFeature informationFeature10 = new InformationFeature();
		informationFeature10.setThreshold(new Long(1));
		informationFeature10.setMatcher(accountMacther);
		accountMacther.setCoupledInformationFeature(informationFeature10);
		
		InformationDescription informationDescription = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		informationDescription.setDistanceEnabled(true);
		informationDescription.setDistance(100);
		ifts.add(informationFeatureName);
		ifts.add(informationFeature10);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		String nameKey = "informationType.compliance.finance.glba.name_" + account + "DigitAccountNumber";

		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey(nameKey);
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);
		
		return inventoryItem;
	}
	
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
		
		DetachedCriteria criteria4 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("descriptionKey", "names.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list4 = getHibernateTemplate().findByCriteria(criteria4);
		BundledKeywordGroup names = DAOUtil.getSingleResult(list4);

		InventoryItem ii10 = generateNameWithAccountNumbers(df, names, "ten");
		InventoryItem ii9 = generateNameWithAccountNumbers(df, names, "nine");
		InventoryItem ii58 = generateNameWithAccountNumbers(df, names, "fe");
		
		ii10.setCategory(glba);
		ii9.setCategory(glba);
		ii58.setCategory(glba);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(ii10);
		dtc.add(ii9);
		dtc.add(ii58);
		glba.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(glba);
		getHibernateTemplate().saveOrUpdate(ii10);
		getHibernateTemplate().saveOrUpdate(ii9);
		getHibernateTemplate().saveOrUpdate(ii58);
	}
}
