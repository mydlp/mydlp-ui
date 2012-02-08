package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.MIMEType;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00009_Data_Formats_HTML_XML extends AbstractGranule {

	protected MIMEType createMTObj(String mime) {
		MIMEType mt = new MIMEType();
		mt.setMimeType(mime);
		getHibernateTemplate().saveOrUpdate(mt);
		return mt;
	}
	
	@Override
	protected void callback() {
		DataFormat df = new DataFormat();
		df.setNameKey("dataFormat.html_xml.label");
		List<MIMEType> mts = new ArrayList<MIMEType>();
		mts.add(createMTObj("application/xml"));
		mts.add(createMTObj("application/x-fictionbook+xml"));
		df.setMimeTypes(mts);
		getHibernateTemplate().saveOrUpdate(df);
	}

}
