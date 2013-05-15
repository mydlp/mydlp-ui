package com.mydlp.ui.framework.blazeds3;

public class AMFEndpoint extends flex.messaging.endpoints.AMFEndpoint {

	public AMFEndpoint() {
		this(false);
	}

	public AMFEndpoint(boolean enableManagement) {
		super(enableManagement);
		serializerClass = Serializer.class;
	}

}
