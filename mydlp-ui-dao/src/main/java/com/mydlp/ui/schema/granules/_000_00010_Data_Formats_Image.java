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
		mts.add(createMTObj("image/bmp"));
		mts.add(createMTObj("image/cgm"));
		mts.add(createMTObj("image/cmu-raster"));
		mts.add(createMTObj("image/fif"));
		mts.add(createMTObj("image/florian"));
		mts.add(createMTObj("image/g3fax"));
		mts.add(createMTObj("image/gif"));
		mts.add(createMTObj("image/ief"));
		mts.add(createMTObj("image/jpeg"));
		mts.add(createMTObj("image/jutvision"));
		mts.add(createMTObj("image/naplps"));
		mts.add(createMTObj("image/pcx"));
		mts.add(createMTObj("image/pict"));
		mts.add(createMTObj("image/pjpeg"));
		mts.add(createMTObj("image/png"));
		mts.add(createMTObj("image/prs.btif"));
		mts.add(createMTObj("image/prs.pti"));
		mts.add(createMTObj("image/svg+xml"));
		mts.add(createMTObj("image/tiff"));
		mts.add(createMTObj("image/vasa"));
		mts.add(createMTObj("image/vnd.cns.inf2"));
		mts.add(createMTObj("image/vnd.djvu"));
		mts.add(createMTObj("image/vnd.dwg"));
		mts.add(createMTObj("image/vnd.dxf"));
		mts.add(createMTObj("image/vnd.fastbidsheet"));
		mts.add(createMTObj("image/vnd.fpx"));
		mts.add(createMTObj("image/vnd.fst"));
		mts.add(createMTObj("image/vnd.fujixerox.edmics-mmr"));
		mts.add(createMTObj("image/vnd.fujixerox.edmics-rlc"));
		mts.add(createMTObj("image/vnd.mix"));
		mts.add(createMTObj("image/vnd.net-fpx"));
		mts.add(createMTObj("image/vnd.rn-realflash"));
		mts.add(createMTObj("image/vnd.rn-realpix"));
		mts.add(createMTObj("image/vnd.svf"));
		mts.add(createMTObj("image/vnd.wap.wbmp"));
		mts.add(createMTObj("image/vnd.xiff"));
		mts.add(createMTObj("image/xbm"));
		mts.add(createMTObj("image/x-canon-cr2"));
		mts.add(createMTObj("image/x-canon-crw"));
		mts.add(createMTObj("image/x-cmu-raster"));
		mts.add(createMTObj("image/x-coreldraw"));
		mts.add(createMTObj("image/x-coreldrawpattern"));
		mts.add(createMTObj("image/x-coreldrawtemplate"));
		mts.add(createMTObj("image/x-corelphotopaint"));
		mts.add(createMTObj("image/x-dwg"));
		mts.add(createMTObj("image/x-epson-erf"));
		mts.add(createMTObj("image/x-icon"));
		mts.add(createMTObj("image/x-jg"));
		mts.add(createMTObj("image/x-jng"));
		mts.add(createMTObj("image/x-jpeg"));
		mts.add(createMTObj("image/x-jps"));
		mts.add(createMTObj("image/x-ms-bmp"));
		mts.add(createMTObj("image/x-niff"));
		mts.add(createMTObj("image/x-nikon-nef"));
		mts.add(createMTObj("image/x-olympus-orf"));
		mts.add(createMTObj("image/x-pcx"));
		mts.add(createMTObj("image/x-photoshop"));
		mts.add(createMTObj("image/x-pict"));
		mts.add(createMTObj("image/xpm"));
		mts.add(createMTObj("image/x-portable-anymap"));
		mts.add(createMTObj("image/x-portable-bitmap"));
		mts.add(createMTObj("image/x-portable-graymap"));
		mts.add(createMTObj("image/x-portable-pixmap"));
		mts.add(createMTObj("image/x-quicktime"));
		mts.add(createMTObj("image/x-rgb"));
		mts.add(createMTObj("image/x-tiff"));
		mts.add(createMTObj("image/x-windows-bmp"));
		mts.add(createMTObj("image/x-xbitmap"));
		mts.add(createMTObj("image/x-xbm"));
		mts.add(createMTObj("image/x-xpixmap"));
		mts.add(createMTObj("image/x-xwd"));
		mts.add(createMTObj("image/x-xwindowdump"));
		df.setMimeTypes(mts);
		getHibernateTemplate().saveOrUpdate(df);
	}

}
