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
		mts.add(createMTObj("text/cache-manifest"));
		mts.add(createMTObj("text/calendar"));
		mts.add(createMTObj("text/css"));
		mts.add(createMTObj("text/csv"));
		mts.add(createMTObj("text/directory"));
		mts.add(createMTObj("text/english"));
		mts.add(createMTObj("text/enriched"));
		mts.add(createMTObj("text/iuls"));
		mts.add(createMTObj("text/mathml"));
		mts.add(createMTObj("text/parityfec"));
		mts.add(createMTObj("text/plain"));
		mts.add(createMTObj("text/tab-separated-values"));
		mts.add(createMTObj("text/uri-list"));
		mts.add(createMTObj("text/x-boo"));
		mts.add(createMTObj("text/x-c++hdr"));
		mts.add(createMTObj("text/x-c++src"));
		mts.add(createMTObj("text/x-chdr"));
		mts.add(createMTObj("text/x-component"));
		mts.add(createMTObj("text/x-crontab"));
		mts.add(createMTObj("text/x-csh"));
		mts.add(createMTObj("text/x-csrc"));
		mts.add(createMTObj("text/x-dsrc"));
		mts.add(createMTObj("text/x-diff"));
		mts.add(createMTObj("text/x-haskell"));
		mts.add(createMTObj("text/x-java"));
		mts.add(createMTObj("text/x-literate-haskell"));
		mts.add(createMTObj("text/x-makefile"));
		mts.add(createMTObj("text/x-moc"));
		mts.add(createMTObj("text/x-pascal"));
		mts.add(createMTObj("text/x-pcs-gcd"));
		mts.add(createMTObj("text/x-perl"));
		mts.add(createMTObj("text/x-python"));
		mts.add(createMTObj("text/x-scala"));
		mts.add(createMTObj("text/x-server-parsed-html"));
		mts.add(createMTObj("text/x-setext"));
		mts.add(createMTObj("text/x-sh"));
		mts.add(createMTObj("text/x-tcl"));
		mts.add(createMTObj("text/x-tex"));
		mts.add(createMTObj("text/x-vcalendar"));
		mts.add(createMTObj("text/x-vcard"));
		df.setMimeTypes(mts);
		getHibernateTemplate().saveOrUpdate(df);
	}

}
