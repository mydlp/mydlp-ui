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
import com.mydlp.ui.domain.MIMEType;
import com.mydlp.ui.domain.Matcher;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00004_InformationType_1 extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.documentTypes"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory documentTypes = DAOUtil.getSingleResult(list);
		
		MIMEType mt = new MIMEType();
		mt.setMimeType("text/plain");
		
		DataFormat df = new DataFormat();
		df.setName("Example Data Format");
		List<MIMEType> mts = new ArrayList<MIMEType>();
		mts.add(mt);
		df.setMimeTypes(mts);
		
		Matcher m = new Matcher();
		m.setFunctionName("cc_match");
		
		InformationFeature ift = new InformationFeature();
		ift.setScore(new Long(501));
		ift.setMatcher(m);
		m.setCoupledInformationFeature(ift);
		
		InformationDescription id = new InformationDescription();
		id.setThreshold(new Long(500));
		ift.setInformationDescription(id);
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		ifts.add(ift);
		id.setFeatures(ifts);
		
		InformationType it = new InformationType();
		it.setInformationDescription(id);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		it.setDataFormats(dfs);
		id.setInformationType(it);
		
		InventoryItem ii = new InventoryItem();
		ii.setName("Example Information Type");
		ii.setItem(it);
		it.setCoupledInventoryItem(ii);
		
		ii.setCategory(documentTypes);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(ii);
		documentTypes.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(mt);
		getHibernateTemplate().saveOrUpdate(df);
		getHibernateTemplate().saveOrUpdate(documentTypes);
		getHibernateTemplate().saveOrUpdate(ii);
		
	}

}
