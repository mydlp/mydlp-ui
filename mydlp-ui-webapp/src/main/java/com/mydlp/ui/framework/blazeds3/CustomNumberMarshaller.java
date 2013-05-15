package com.mydlp.ui.framework.blazeds3;

import flex.messaging.io.Java15TypeMarshaller;
import flex.messaging.io.amf.translator.decoder.DecoderFactory;


public class CustomNumberMarshaller extends Java15TypeMarshaller {

	private static final NumberDecoder numberDecoder = new NumberDecoder();

	@SuppressWarnings("rawtypes") 
	public Object convert(Object source, Class desiredClass) {
		if (DecoderFactory.isNumber(desiredClass)) {
			return numberDecoder.decodeObject(source, desiredClass);
		} else {
			return super.convert(source, desiredClass);
		}
	}
	
}
