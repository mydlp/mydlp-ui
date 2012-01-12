package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.MIMEType;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00008_Data_Formats_Plain_Text extends AbstractGranule {

	protected MIMEType createMTObj(String mime) {
		MIMEType mt = new MIMEType();
		mt.setMimeType(mime);
		getHibernateTemplate().saveOrUpdate(mt);
		return mt;
	}
	
	@Override
	protected void callback() {
		DataFormat df = new DataFormat();
		df.setNameKey("dataFormat.plain_text.label");
		List<MIMEType> mts = new ArrayList<MIMEType>();
		mts.add(createMTObj("application/xhtml+xml"));
		mts.add(createMTObj("application/vnd.pwg-xhtml-print+xml"));
		mts.add(createMTObj("text/html"));
		mts.add(createMTObj("application/atom+xml"));
		mts.add(createMTObj("application/atomcat+xml"));
		mts.add(createMTObj("application/atomserv+xml"));
		mts.add(createMTObj("application/beep+xml"));
		mts.add(createMTObj("application/davmount+xml"));
		mts.add(createMTObj("application/docbook+xml"));
		mts.add(createMTObj("application/rdf+xml"));
		mts.add(createMTObj("application/rss+xml"));
		mts.add(createMTObj("application/xhtml+xml"));
		mts.add(createMTObj("application/xml"));
		mts.add(createMTObj("application/xml-dtd"));
		mts.add(createMTObj("application/vnd.wap.wbxml"));
		mts.add(createMTObj("image/svg+xml"));
		mts.add(createMTObj("model/x3d+xml"));

		df.setMimeTypes(mts);
		getHibernateTemplate().saveOrUpdate(df);
	}

}
