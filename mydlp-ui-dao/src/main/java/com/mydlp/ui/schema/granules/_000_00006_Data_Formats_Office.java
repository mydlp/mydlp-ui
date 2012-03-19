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
		mts.add(createMTObj("application/vnd.ms-excel.sheet.binary.macroenabled.12"));
		mts.add(createMTObj("application/x-mspublisher"));
		mts.add(createMTObj("application/x-tika-msoffice"));
		mts.add(createMTObj("application/vnd.ms-outlook"));
		mts.add(createMTObj("application/vnd.ms-excel"));
		mts.add(createMTObj("application/vnd.ms-powerpoint"));
		mts.add(createMTObj("application/vnd.visio"));
		mts.add(createMTObj("application/rtf"));
		mts.add(createMTObj("application/msword"));
		mts.add(createMTObj("application/x-tnef"));
		mts.add(createMTObj("application/ms-tnef"));
		mts.add(createMTObj("application/vnd.ms-tnef"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.presentationml.slideshow"));
		mts.add(createMTObj("application/vnd.ms-word.document.macroenabled.12"));
		mts.add(createMTObj("application/vnd.ms-powerpoint.slideshow.macroenabled.12"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.presentationml.template"));
		mts.add(createMTObj("application/vnd.ms-excel.template.macroenabled.12"));
		mts.add(createMTObj("application/vnd.ms-powerpoint.addin.macroenabled.12"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.presentationml.presentation"));
		mts.add(createMTObj("application/x-tika-ooxml"));
		mts.add(createMTObj("application/vnd.ms-word.template.macroenabled.12"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.spreadsheetml.template"));
		mts.add(createMTObj("application/vnd.ms-excel.sheet.macroenabled.12"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
		mts.add(createMTObj("application/vnd.ms-excel.addin.macroenabled.12"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		mts.add(createMTObj("application/vnd.ms-powerpoint.presentation.macroenabled.12"));
		mts.add(createMTObj("application/vnd.openxmlformats-officedocument.wordprocessingml.template"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.spreadsheet-template"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.text-template"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.chart-template"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.presentation"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.image-template"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.text-web"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.presentation"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.text"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.spreadsheet"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.graphics"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.graphics-template"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.image"));
		mts.add(createMTObj("application/vnd.sun.xml.writer"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.formula-template"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.text-master"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.graphics-template"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.graphics"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.text"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.presentation-template"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.presentation-template"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.text-template"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.spreadsheet-template"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.spreadsheet"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.image-template"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.chart"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.image"));
		mts.add(createMTObj("application/vnd.oasis.opendocument.text-web"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.chart-template"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.formula-template"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.chart"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.formula"));
		mts.add(createMTObj("application/x-vnd.oasis.opendocument.text-master"));
		df.setMimeTypes(mts);
		getHibernateTemplate().saveOrUpdate(df);
		
	}

}
