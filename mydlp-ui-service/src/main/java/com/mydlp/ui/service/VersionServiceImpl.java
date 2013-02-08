package com.mydlp.ui.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

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

	protected static final String MYDLP_WIN_AGENT_VERSION_FILE = "/usr/share/mydlp/endpoint/win/latest.txt";
	
	protected String version = null;
	protected String windowsAgentVersion = null;
	
	@PostConstruct
	public void init() {
		generateWindowsAgentVersion();
		generateVersion();
	}

	@Override
	public String getVersion() {
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

	@Override
	public String getWindowsAgentVersion() {
		return windowsAgentVersion;
	}
		
	protected void generateWindowsAgentVersion() {
		try {
			String fileContent = FileUtils.readFileToString(new File(MYDLP_WIN_AGENT_VERSION_FILE));
			if (fileContent != null && fileContent.length() > 0)
				windowsAgentVersion = fileContent.trim();
		} catch (IOException e) {
			logger.error("Can not read windows agent version file", e);
		}
		
	}

}
