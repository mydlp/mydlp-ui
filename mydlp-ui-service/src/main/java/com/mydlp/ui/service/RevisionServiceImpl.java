package com.mydlp.ui.service;

import org.springframework.stereotype.Service;

import com.mydlp.ui.domain.Revision;
import com.mydlp.ui.framework.util.ProcessUtil;

@Service("revisionService")
public class RevisionServiceImpl implements RevisionService {
	
	//private static Logger logger = LoggerFactory.getLogger(RevisionServiceImpl.class);
	
	protected static final String MYDLP_REVISION_SAVE = "/usr/sbin/mydlp-revision-save";
	
	protected static final String MYDLP_REVISION_RESTORE = "/usr/sbin/mydlp-revision-restore";

	@Override
	public void save() {
		ProcessUtil.executeCommand(new String[]{MYDLP_REVISION_SAVE});
	}

	@Override
	public void restore(Revision revision) {
		ProcessUtil.executeCommand(new String[]{MYDLP_REVISION_RESTORE, revision.getDumpPath()});
	}

}
