package com.mydlp.ui.framework.blazeds3;

import java.io.OutputStream;

import flex.messaging.io.MessageIOConstants;
import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.AmfMessageSerializer;
import flex.messaging.io.amf.AmfTrace;

public class Serializer extends AmfMessageSerializer {

	@Override
	public void initialize(SerializationContext context, OutputStream out,
			AmfTrace trace) {
		amfOut = new AMF0Output(context);
		amfOut.setOutputStream(out);
		amfOut.setAvmPlus(version >= MessageIOConstants.AMF3);

		debugTrace = trace;
		isDebug = trace != null;
		amfOut.setDebugTrace(debugTrace);
	}
}
