package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.BundledKeywordGroupDAO;
import com.mydlp.ui.domain.BundledKeywordGroup;



@Service("bundledKeywordGroupBRS")
@RemotingDestination
public class BundledKeywordGroupBRSImpl implements BundledKeywordGroupService {

	@Autowired
	protected BundledKeywordGroupDAO bundledKeywordGroupDAO;

	@Override
	public List<BundledKeywordGroup> getBundledKeywordGroups() {
		return bundledKeywordGroupDAO.getBundledKeywordGroups();
	}

	
}
