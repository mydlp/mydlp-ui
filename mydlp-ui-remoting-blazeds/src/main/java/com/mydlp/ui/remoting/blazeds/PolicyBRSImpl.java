package com.mydlp.ui.remoting.blazeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.EndpointStatusDAO;
import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("policyBRS")
@RemotingDestination
public class PolicyBRSImpl implements PolicyService
{
	
	@Autowired
	@Qualifier("thriftService")
	protected MyDLPUIThriftService thriftService;
	
	@Autowired
	protected EndpointStatusDAO endpointStatusDAO;

	@Override
	public void compilePolicy(Integer policyId) {
		endpointStatusDAO.outOfDateAllEndpoints();
		thriftService.compileFilter(policyId);
	}

}