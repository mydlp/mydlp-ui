package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.Revision;


public interface RevisionDAO {

	public List<Revision> getRevisions(Integer offset, Integer limit);
	
	public Long getRevisionCount();
	
	public List<Revision> getNamedRevisions(Integer offset, Integer limit);
	
	public Long getNamedRevisionCount();
	
	public void save(Revision revision);
	
	public Long getRevisionIndex(Revision revision);
	
}
