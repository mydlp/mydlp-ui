package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.RegularExpressionGroupDAO;
import com.mydlp.ui.domain.RegularExpressionGroup;


@Service("regularExpressionGroupBRS")
@RemotingDestination
public class RegularExpressionGroupBRSImpl implements RegularExpressionGroupService {

	@Autowired
	protected RegularExpressionGroupDAO regularExpressionGroupDAO;

	@Override
	public List<RegularExpressionGroup> getRegularExpressions() {
		return regularExpressionGroupDAO.getRegularExpressionGroups();
	}

	@Override
	public RegularExpressionGroup save(RegularExpressionGroup r) {
		return regularExpressionGroupDAO.save(r);
	}
	
}
