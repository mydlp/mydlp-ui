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
import com.mydlp.ui.domain.MatcherParam;
import com.mydlp.ui.domain.RegularExpression;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00015_InformationType_Predefined_Keyword_TopSecret extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.informationTypes.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory documentTypes = DAOUtil.getSingleResult(list);
		
		DetachedCriteria criteria2 = 
				DetachedCriteria.forClass(DataFormat.class)
					.add(Restrictions.eq("nameKey", "dataFormat.all.label"));
		@SuppressWarnings("unchecked")
		List<DataFormat> list2 = getHibernateTemplate().findByCriteria(criteria2);
		DataFormat df = DAOUtil.getSingleResult(list2);
		
		RegularExpression r = new RegularExpression();
		r.setRegex("top secret");
		
		Matcher m = new Matcher();
		m.setFunctionName("keyword");
		r.setMatcher(m);
		List<MatcherParam> mpl = new ArrayList<MatcherParam>();
		mpl.add(r);
		m.setMatcherParams(mpl);
		
		InformationFeature ift = new InformationFeature();
		ift.setWeight(new Long(1));
		ift.setMatcher(m);
		m.setCoupledInformationFeature(ift);
		
		InformationDescription id = new InformationDescription();
		id.setThreshold(new Long(1));
		//ift.setInformationDescription(id);
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		ifts.add(ift);
		id.setFeatures(ifts);
		
		InformationType it = new InformationType();
		it.setInformationDescription(id);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		it.setDataFormats(dfs);
		//id.setInformationType(it);
		
		InventoryItem ii = new InventoryItem();
		ii.setNameKey("informationType.predefined.keyword.topsecret");
		ii.setItem(it);
		it.setCoupledInventoryItem(ii);
		
		ii.setCategory(documentTypes);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(ii);
		documentTypes.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(documentTypes);
		getHibernateTemplate().saveOrUpdate(ii);
		
	}

}
