package com.mydlp.ui.framework.blazeds3;

import flex.messaging.io.SerializationContext;

public class AMF0Output extends flex.messaging.io.amf.Amf0Output {

	public AMF0Output(SerializationContext context) {
		super(context);
	}

	@Override
	protected void createAMF3Output() {
		avmPlusOutput = new AMF3Output(context);
		avmPlusOutput.setOutputStream(out);
		avmPlusOutput.setDebugTrace(trace);
	}
}