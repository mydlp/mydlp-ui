package com.mydlp.ui.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mydlp.ui.domain.Revision;

@Service("revisionService")
public class RevisionServiceImpl implements RevisionService {
	
	private static Logger logger = LoggerFactory.getLogger(RevisionServiceImpl.class);
	
	protected static final String MYDLP_REVISION_SAVE = "/usr/sbin/mydlp-revision-save";
	
	protected static final String MYDLP_REVISION_RESTORE = "/usr/sbin/mydlp-revision-restore";
	
	protected void executeCommand(String [] command) {
		if (command == null || command.length == 0) return;
		File executable = new File(command[0]);
		if (!executable.canExecute()) return;
		
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec(command);
		} catch (IOException e) {
			logger.error("Error occured when executing command", command, e);
		}
	}

	@Override
	public void save() {
		executeCommand(new String[]{MYDLP_REVISION_SAVE});
	}

	@Override
	public void restore(Revision revision) {
		executeCommand(new String[]{MYDLP_REVISION_RESTORE, revision.getDumpPath()});
	}

}
