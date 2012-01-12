package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.MIMEType;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00006_Data_Formats_Office extends AbstractGranule {

	public MIMEType createMTObj(String mime) {
		MIMEType mt = new MIMEType();
		mt.setMimeType(mime);
		getHibernateTemplate().saveOrUpdate(mt);
		return mt;
	}
	
	@Override
	protected void callback() {
		DataFormat df = new DataFormat();
		df.setNameKey("dataFormat.office.label");
		List<MIMEType> mts = new ArrayList<MIMEType>();
		mts.add(createMTObj("application/msword"));
		mts.add(createMTObj("application/msaccess"));
		mts.add(createMTObj("application/word"));
		mts.add(createMTObj("application/mspowerpoint"));
		mts.add(createMTObj("application/powerpoint"));
		mts.add(createMTObj("application/vnd.ms-powerpoint"));
		mts.add(createMTObj("application/x-mspowerpoint"));
		mts.add(createMTObj("application/mspowerpoint"));
		mts.add(createMTObj("application/rtf"));
		mts.add(createMTObj("application/x-rtf"));
		mts.add(createMTObj("application/rtf"));
		mts.add(createMTObj("text/richtext"));
		mts.add(createMTObj("text/rtf"));
		mts.add(createMTObj("application/msword"));
		mts.add(createMTObj("application/excel"));
		mts.add(createMTObj("application/vnd.ms-excel"));
		mts.add(createMTObj("application/x-excel"));
		mts.add(createMTObj("application/x-msexcel"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.wordprocessingml.template"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.spreadsheetml.template"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.presentationml.presentation"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.presentationml.template"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.presentationml.slideshow"));
		mts.add(createMTObj("application/vnd.sun.xml.calc"));
		mts.add(createMTObj("application/vnd.sun.xml.calc.template"));
		mts.add(createMTObj("application/vnd.sun.xml.draw"));
		mts.add(createMTObj("application/vnd.sun.xml.draw.template"));
		mts.add(createMTObj("application/vnd.sun.xml.impress"));
		mts.add(createMTObj("application/vnd.sun.xml.impress.template"));
		mts.add(createMTObj("application/vnd.sun.xml.math"));
		mts.add(createMTObj("application/vnd.sun.xml.writer"));
		mts.add(createMTObj("application/vnd.sun.xml.writer.global"));
		mts.add(createMTObj("application/vnd.sun.xml.writer.template"));
		df.setMimeTypes(mts);
		getHibernateTemplate().saveOrUpdate(df);
		
	}

}
