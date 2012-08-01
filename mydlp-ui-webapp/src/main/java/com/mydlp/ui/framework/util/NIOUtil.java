package com.mydlp.ui.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.apache.commons.io.IOUtils;

public class NIOUtil {
	
	public static ByteBuffer getWholeData(InputStream is) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		IOUtils.copy(is, os);
		return ByteBuffer.wrap(os.toByteArray());
	}

}
