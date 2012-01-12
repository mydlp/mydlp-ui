package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.MIMEType;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00004_Data_Formats extends AbstractGranule {

	@Override
	protected void callback() {
		MIMEType mt = new MIMEType();
		mt.setMimeType("mydlp-internal/all");
		
		DataFormat df = new DataFormat();
		df.setNameKey("dataFormat.all.label");
		List<MIMEType> mts = new ArrayList<MIMEType>();
		mts.add(mt);
		df.setMimeTypes(mts);
		
		getHibernateTemplate().saveOrUpdate(mt);
		getHibernateTemplate().saveOrUpdate(df);
		
	}

}
