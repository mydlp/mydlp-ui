package com.mydlp.ui.framework.blazeds3;

public class SecureAMFEndpoint extends flex.messaging.endpoints.SecureAMFEndpoint {

	public SecureAMFEndpoint() {
		this(false);
	}

	public SecureAMFEndpoint(boolean enableManagement) {
		super(enableManagement);
		serializerClass = Serializer.class;
	}

}
