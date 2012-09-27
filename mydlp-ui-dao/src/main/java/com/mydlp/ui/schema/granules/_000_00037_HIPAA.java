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

public class _000_00037_HIPAA extends AbstractGranule {

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
					.add(Restrictions.eq("descriptionKey", "drugName.descriptionKey"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list3 = getHibernateTemplate().findByCriteria(criteria3);
		BundledKeywordGroup argD = DAOUtil.getSingleResult(list3);
		
		InventoryCategory hipaa = new InventoryCategory();
		hipaa.setNameKey("inventory.compliance.federal_regulations.hipaa.predefined");
		hipaa.setEditable(false);
		hipaa.setCategory(federalRegulations);
		
		Matcher matcherCC = new Matcher();
		matcherCC.setFunctionName("cc");
	
		Matcher matcherKG = new Matcher();
		matcherKG.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgument = new NonCascadingArgument(); 	
		MatcherArgument ma = new MatcherArgument();
		nonCascadingArgument.setArgument(argD);
		ma.setCoupledMatcher(matcherKG);
		ma.setCoupledArgument(nonCascadingArgument);
		List<MatcherArgument> mas = new ArrayList<MatcherArgument>();
		mas.add(ma);
		matcherKG.setMatcherArguments(mas);
		
		InformationFeature ift = new InformationFeature();
		ift.setThreshold(new Long(1));
		ift.setMatcher(matcherCC);
		matcherCC.setCoupledInformationFeature(ift);
		
		InformationFeature ift1 = new InformationFeature();
		ift1.setThreshold(new Long(1));
		ift1.setMatcher(matcherKG);
		matcherKG.setCoupledInformationFeature(ift1);
		
		InformationDescription id = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		id.setDistanceEnabled(false);
		id.setDistance(75);
		ifts.add(ift);
		ifts.add(ift1);
		id.setFeatures(ifts);
		
		InformationType it = new InformationType();
		it.setInformationDescription(id);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		it.setDataFormats(dfs);
		
		InventoryItem ii = new InventoryItem();
		ii.setNameKey("informationType.compliance.federal_regulations.hipaa.ccn_drug");
		ii.setItem(it);
		it.setCoupledInventoryItem(ii);
		
		ii.setCategory(hipaa);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(ii);
		hipaa.setChildren(dtc);
		
		
		getHibernateTemplate().saveOrUpdate(federalRegulations);
		getHibernateTemplate().saveOrUpdate(hipaa);
		getHibernateTemplate().saveOrUpdate(ii);
		//getHibernateTemplate().saveOrUpdate(nonCascadingArgument);
	}
}
