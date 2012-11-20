package com.mydlp.ui.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("versionService")
public class VersionServiceImpl implements VersionService {

	private static Logger logger = LoggerFactory
			.getLogger(VersionServiceImpl.class);

	protected static final String MYDLP_VERSION = "/usr/sbin/mydlp-version";

	protected String version = null;

	protected void executeCommand(String[] command) {
		if (command == null || command.length == 0)
			return;
		File executable = new File(command[0]);
		if (!executable.canExecute())
			return;

		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec(command);
		} catch (IOException e) {
			logger.error("Error occured when executing command", command, e);
		}
	}

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
			tmpFile = File.createTempFile("mydlp-version", null);
			filename = tmpFile.getCanonicalPath();
		} catch (IOException e) {
			logger.error("Error occurred when creating a temp file to store mydlp version",	e);
		} finally {
			if (filename == null)
			{
				if (tmpFile != null && tmpFile.exists())
				{
					tmpFile.delete();
				}
				return ;
			}
		}
		
		executeCommand(new String[] { MYDLP_VERSION, filename });

		try {
			BufferedReader br = new BufferedReader(new FileReader(tmpFile));
			String line = br.readLine();
			version = line.trim();
			br.close();
		} catch (FileNotFoundException e) {
			logger.error("Can not find temp file", e);
		} catch (IOException e) {
			logger.error("Can not read or close temp file", e);
		} finally {
			tmpFile.delete();
		}
	}

}
