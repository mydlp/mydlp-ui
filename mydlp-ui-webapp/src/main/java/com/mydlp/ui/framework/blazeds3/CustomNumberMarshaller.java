package com.mydlp.ui.framework.blazeds3;

import flex.messaging.io.Java15TypeMarshaller;
import flex.messaging.io.amf.translator.decoder.DecoderFactory;

/**
 *  Custom marshaller for nan to null conversion  
 * 
 *  Imported from http://flexblog.faratasystems.com/2010/01/07/how-to-keep-numeric-null-value-from-turning-into-zeros-in-blazeds
 */
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
