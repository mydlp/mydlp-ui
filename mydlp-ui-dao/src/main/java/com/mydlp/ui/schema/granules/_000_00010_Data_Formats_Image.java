package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.MIMEType;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00010_Data_Formats_Image extends AbstractGranule {

	protected MIMEType createMTObj(String mime) {
		MIMEType mt = new MIMEType();
		mt.setMimeType(mime);
		getHibernateTemplate().saveOrUpdate(mt);
		return mt;
	}
	
	@Override
	protected void callback() {
		DataFormat df = new DataFormat();
		df.setNameKey("dataFormat.image.label");
		List<MIMEType> mts = new ArrayList<MIMEType>();
		mts.add(createMTObj("image/png"));
		mts.add(createMTObj("image/x-icon"));
		mts.add(createMTObj("image/gif"));
		mts.add(createMTObj("image/bmp"));
		mts.add(createMTObj("image/x-xcf"));
		mts.add(createMTObj("image/x-ms-bmp"));
		mts.add(createMTObj("image/vnd.wap.wbmp"));
		mts.add(createMTObj("image/vnd.adobe.photoshop"));
		mts.add(createMTObj("image/tiff"));
		mts.add(createMTObj("image/jpeg"));
		mts.add(createMTObj("image/svg+xml"));
		df.setMimeTypes(mts);
		getHibernateTemplate().saveOrUpdate(df);
	}

}
