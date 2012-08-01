package com.mydlp.ui.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.apache.commons.io.IOUtils;

public class NIOUtil {
	
	public static ByteBuffer getWholeData(InputStream is) throws IOException {
		return ByteBuffer.wrap(IOUtils.toByteArray(is));
	}

}
