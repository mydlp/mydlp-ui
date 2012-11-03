package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.Revision;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN, AuthSecurityRole.ROLE_CLASSIFIER})
public interface RevisionService {

	public List<Revision> getRevisions(Integer offset, Integer limit);
	
	public Long getRevisionCount();
	
	public List<Revision> getNamedRevisions(Integer offset, Integer limit);
	
	public Long getNamedRevisionCount();
	
	public void save();
	
	public void restore(Revision revision);
	
	public void saveEntity(Revision revision);
	
	public Long getRevisionIndex(Revision revision);
	
}