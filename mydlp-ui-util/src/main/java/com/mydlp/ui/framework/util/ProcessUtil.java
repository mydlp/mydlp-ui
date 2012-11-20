package com.mydlp.ui.framework.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessUtil {

	private static Logger logger = LoggerFactory
			.getLogger(ProcessUtil.class);
	
	public static void executeCommand(String[] command) {
		if (command == null || command.length == 0)
			return;
		File executable = new File(command[0]);
		if (!executable.canExecute())
			return;

		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (IOException e) {
			logger.error("Error occured when executing command", command, e);
		} catch (InterruptedException e) {
			logger.error("Executed process hasd been interrupted", command, e);
		}
	}
	
}
