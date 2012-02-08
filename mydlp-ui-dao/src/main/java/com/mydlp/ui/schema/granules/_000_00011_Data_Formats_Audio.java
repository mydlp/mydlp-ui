package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.MIMEType;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00011_Data_Formats_Audio extends AbstractGranule {

	protected MIMEType createMTObj(String mime) {
		MIMEType mt = new MIMEType();
		mt.setMimeType(mime);
		getHibernateTemplate().saveOrUpdate(mt);
		return mt;
	}
	
	@Override
	protected void callback() {
		DataFormat df = new DataFormat();
		df.setNameKey("dataFormat.audio.label");
		List<MIMEType> mts = new ArrayList<MIMEType>();
		mts.add(createMTObj("audio/x-aiff"));
		mts.add(createMTObj("audio/x-wav"));
		mts.add(createMTObj("audio/basic"));
		mts.add(createMTObj("audio/midi"));
		mts.add(createMTObj("application/x-midi"));
		mts.add(createMTObj("audio/mpeg"));
		mts.add(createMTObj("video/x-flv"));
		df.setMimeTypes(mts);
		getHibernateTemplate().saveOrUpdate(df);
	}

}
