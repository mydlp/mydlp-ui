package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.RevisionDAO;
import com.mydlp.ui.domain.Revision;

@Service("revisionBRS")
@RemotingDestination
public class RevisionBRSImpl implements RevisionService
{
	
	@Autowired
	protected RevisionDAO revisionDAO;
	
	@Autowired
	protected com.mydlp.ui.service.RevisionService revisionService;

	@Override
	public List<Revision> getRevisions(Integer offset, Integer limit) {
		return revisionDAO.getRevisions(offset, limit);
	}

	@Override
	public Long getRevisionCount() {
		return revisionDAO.getRevisionCount();
	}

	@Override
	public List<Revision> getNamedRevisions(Integer offset, Integer limit) {
		return revisionDAO.getNamedRevisions(offset, limit);
	}

	@Override
	public Long getNamedRevisionCount() {
		return revisionDAO.getNamedRevisionCount();
	}

	@Override
	public void save() {
		revisionService.save();
	}

	@Override
	public void restore(Revision revision) {
		revisionService.restore(revision);
	}

	@Override
	public void saveEntity(Revision revision) {
		revisionDAO.save(revision);
	}

	@Override
	public Long getRevisionIndex(Revision revision) {
		return revisionDAO.getRevisionIndex(revision);
	}
	

}