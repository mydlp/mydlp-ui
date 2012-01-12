package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.MIMEType;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00007_Data_Formats_PDF extends AbstractGranule {

	protected MIMEType createMTObj(String mime) {
		MIMEType mt = new MIMEType();
		mt.setMimeType(mime);
		getHibernateTemplate().saveOrUpdate(mt);
		return mt;
	}
	
	@Override
	protected void callback() {
		DataFormat df = new DataFormat();
		df.setNameKey("dataFormat.pdf.label");
		List<MIMEType> mts = new ArrayList<MIMEType>();
		mts.add(createMTObj("application/postscript"));
		mts.add(createMTObj("application/pdf"));
		mts.add(createMTObj("application/eps"));
		mts.add(createMTObj("application/x-eps"));
		mts.add(createMTObj("image/eps"));
		mts.add(createMTObj("image/x-eps"));
		mts.add(createMTObj("application/x-pdf"));
		mts.add(createMTObj("application/acrobat"));
		mts.add(createMTObj("application/vnd.pdf"));
		mts.add(createMTObj("text/pdf"));
		mts.add(createMTObj("text/x-pdf"));
		mts.add(createMTObj("application/vnd.cups-postscript"));
		df.setMimeTypes(mts);
		getHibernateTemplate().saveOrUpdate(df);
	}

}
