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
		mts.add(createMTObj("audio/32kadpcm"));
		mts.add(createMTObj("audio/3gpp"));
		mts.add(createMTObj("audio/aiff"));
		mts.add(createMTObj("audio/amr"));
		mts.add(createMTObj("audio/amr-wb"));
		mts.add(createMTObj("audio/annodex"));
		mts.add(createMTObj("audio/basic"));
		mts.add(createMTObj("audio/flac"));
		mts.add(createMTObj("audio/g.722.1"));
		mts.add(createMTObj("audio/it"));
		mts.add(createMTObj("audio/l16"));
		mts.add(createMTObj("audio/make"));
		mts.add(createMTObj("audio/make.my.funk"));
		mts.add(createMTObj("audio/mid"));
		mts.add(createMTObj("audio/midi"));
		mts.add(createMTObj("audio/mod"));
		mts.add(createMTObj("audio/mp4a-latm"));
		mts.add(createMTObj("audio/mpa-robust"));
		mts.add(createMTObj("audio/mpeg"));
		mts.add(createMTObj("audio/mpeg3"));
		mts.add(createMTObj("audio/mpegurl"));
		mts.add(createMTObj("audio/nspaudio"));
		mts.add(createMTObj("audio/ogg"));
		mts.add(createMTObj("audio/parityfec"));
		mts.add(createMTObj("audio/prs.sid"));
		mts.add(createMTObj("audio/s3m"));
		mts.add(createMTObj("audio/telephone-event"));
		mts.add(createMTObj("audio/tone"));
		mts.add(createMTObj("audio/tsp-audio"));
		mts.add(createMTObj("audio/tsplayer"));
		mts.add(createMTObj("audio/vnd.cisco.nse"));
		mts.add(createMTObj("audio/vnd.cns.anp1"));
		mts.add(createMTObj("audio/vnd.cns.inf1"));
		mts.add(createMTObj("audio/vnd.digital-winds"));
		mts.add(createMTObj("audio/vnd.everad.plj"));
		mts.add(createMTObj("audio/vnd.lucent.voice"));
		mts.add(createMTObj("audio/vnd.nortel.vbk"));
		mts.add(createMTObj("audio/vnd.nuera.ecelp4800"));
		mts.add(createMTObj("audio/vnd.nuera.ecelp7470"));
		mts.add(createMTObj("audio/vnd.nuera.ecelp9600"));
		mts.add(createMTObj("audio/vnd.octel.sbc"));
		mts.add(createMTObj("audio/vnd.qcelp"));
		mts.add(createMTObj("audio/vnd.rhetorex.32kadpcm"));
		mts.add(createMTObj("audio/vnd.vmx.cvsd"));
		mts.add(createMTObj("audio/voc"));
		mts.add(createMTObj("audio/voxware"));
		mts.add(createMTObj("audio/wav"));
		mts.add(createMTObj("audio/x-adpcm"));
		mts.add(createMTObj("audio/x-aiff"));
		mts.add(createMTObj("audio/x-au"));
		mts.add(createMTObj("audio/x-gsm"));
		mts.add(createMTObj("audio/x-jam"));
		mts.add(createMTObj("audio/x-liveaudio"));
		mts.add(createMTObj("audio/xm"));
		mts.add(createMTObj("audio/x-mid"));
		mts.add(createMTObj("audio/x-midi"));
		mts.add(createMTObj("audio/x-mod"));
		mts.add(createMTObj("audio/x-mpeg"));
		mts.add(createMTObj("audio/x-mpeg-3"));
		mts.add(createMTObj("audio/x-mpegurl"));
		mts.add(createMTObj("audio/x-mpequrl"));
		mts.add(createMTObj("audio/x-ms-wax"));
		mts.add(createMTObj("audio/x-ms-wma"));
		mts.add(createMTObj("audio/x-nspaudio"));
		mts.add(createMTObj("audio/x-pn-realaudio"));
		mts.add(createMTObj("audio/x-pn-realaudio-plugin"));
		mts.add(createMTObj("audio/x-psid"));
		mts.add(createMTObj("audio/x-realaudio"));
		mts.add(createMTObj("audio/x-scpls"));
		mts.add(createMTObj("audio/x-sd2"));
		mts.add(createMTObj("audio/x-twinvq"));
		mts.add(createMTObj("audio/x-twinvq-plugin"));
		mts.add(createMTObj("audio/x-vnd.audioexplosion.mjuicemediafile"));
		mts.add(createMTObj("audio/x-voc"));
		mts.add(createMTObj("audio/x-wav"));
		df.setMimeTypes(mts);
		getHibernateTemplate().saveOrUpdate(df);
	}

}
