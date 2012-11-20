package com.mydlp.ui.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mydlp.ui.framework.util.ProcessUtil;

@Service("versionService")
public class VersionServiceImpl implements VersionService {

	private static Logger logger = LoggerFactory
			.getLogger(VersionServiceImpl.class);

	protected static final String MYDLP_VERSION = "/usr/sbin/mydlp-version";

	protected String version = null;

	@Override
	public String getVersion() {
		if (version == null)
			generateVersion();

		return version;
	}

	protected void generateVersion() {
		File tmpFile = null;
		String filename = null;
		try {
			tmpFile = File.createTempFile("mydlp-version-", null);
			filename = tmpFile.getCanonicalPath();
		} catch (IOException e) {
			logger.error(
					"Error occurred when creating a temp file to store mydlp version",
					e);
		} finally {
			if (filename == null) {
				if (tmpFile != null && tmpFile.exists()) {
					tmpFile.delete();
				}
				return;
			}
		}

		ProcessUtil.executeCommand(new String[] { MYDLP_VERSION, filename });

		try {
			String fileContent = FileUtils.readFileToString(tmpFile);
			if (fileContent != null && fileContent.length() > 0)
				version = fileContent.trim();
		} catch (FileNotFoundException e) {
			logger.error("Can not find temp file", e);
		} catch (IOException e) {
			logger.error("Can not read or close temp file", e);
		} finally {
			tmpFile.delete();
		}
	}

}
