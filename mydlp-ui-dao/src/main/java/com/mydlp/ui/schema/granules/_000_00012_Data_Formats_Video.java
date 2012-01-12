package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.MIMEType;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00012_Data_Formats_Video extends AbstractGranule {

	protected MIMEType createMTObj(String mime) {
		MIMEType mt = new MIMEType();
		mt.setMimeType(mime);
		getHibernateTemplate().saveOrUpdate(mt);
		return mt;
	}
	
	@Override
	protected void callback() {
		DataFormat df = new DataFormat();
		df.setNameKey("dataFormat.video.label");
		List<MIMEType> mts = new ArrayList<MIMEType>();
		mts.add(createMTObj("video/3gpp"));
		mts.add(createMTObj("video/animaflex"));
		mts.add(createMTObj("video/annodex"));
		mts.add(createMTObj("video/avi"));
		mts.add(createMTObj("video/avs-video"));
		mts.add(createMTObj("video/dl"));
		mts.add(createMTObj("video/dv"));
		mts.add(createMTObj("video/fli"));
		mts.add(createMTObj("video/gl"));
		mts.add(createMTObj("video/mp4"));
		mts.add(createMTObj("video/mp4v-es"));
		mts.add(createMTObj("video/mpeg"));
		mts.add(createMTObj("video/msvideo"));
		mts.add(createMTObj("video/ogg"));
		mts.add(createMTObj("video/parityfec"));
		mts.add(createMTObj("video/pointer"));
		mts.add(createMTObj("video/quicktime"));
		mts.add(createMTObj("video/vdo"));
		mts.add(createMTObj("video/vivo"));
		mts.add(createMTObj("video/vnd.fvt"));
		mts.add(createMTObj("video/vnd.motorola.video"));
		mts.add(createMTObj("video/vnd.motorola.videop"));
		mts.add(createMTObj("video/vnd.mpegurl"));
		mts.add(createMTObj("video/vnd.mts"));
		mts.add(createMTObj("video/vnd.nokia.interleaved-multimedia"));
		mts.add(createMTObj("video/vnd.rn-realvideo"));
		mts.add(createMTObj("video/vnd.vivo"));
		mts.add(createMTObj("video/vosaic"));
		mts.add(createMTObj("video/x-amt-demorun"));
		mts.add(createMTObj("video/x-amt-showrun"));
		mts.add(createMTObj("video/x-atomic3d-feature"));
		mts.add(createMTObj("video/x-dl"));
		mts.add(createMTObj("video/x-dv"));
		mts.add(createMTObj("video/x-fli"));
		mts.add(createMTObj("video/x-flv"));
		mts.add(createMTObj("video/x-gl"));
		mts.add(createMTObj("video/x-isvideo"));
		mts.add(createMTObj("video/x-la-asf"));
		mts.add(createMTObj("video/x-matroska"));
		mts.add(createMTObj("video/x-mng"));
		mts.add(createMTObj("video/x-motion-jpeg"));
		mts.add(createMTObj("video/x-mpeg"));
		mts.add(createMTObj("video/x-mpeq2a"));
		mts.add(createMTObj("video/x-ms-asf"));
		mts.add(createMTObj("video/x-ms-asf-plugin"));
		mts.add(createMTObj("video/x-msvideo"));
		mts.add(createMTObj("video/x-ms-wm"));
		mts.add(createMTObj("video/x-ms-wmv"));
		mts.add(createMTObj("video/x-ms-wmx"));
		mts.add(createMTObj("video/x-ms-wvx"));
		mts.add(createMTObj("video/x-qtc"));
		mts.add(createMTObj("video/x-scm"));
		mts.add(createMTObj("video/x-sgi-movie"));
		df.setMimeTypes(mts);
		getHibernateTemplate().saveOrUpdate(df);
	}

}
