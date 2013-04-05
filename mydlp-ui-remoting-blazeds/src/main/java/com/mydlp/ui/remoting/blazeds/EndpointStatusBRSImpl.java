package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.EndpointDAO;
import com.mydlp.ui.dao.EndpointStatusDAO;
import com.mydlp.ui.domain.EndpointStatus;
import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("endpointStatusBRS")
@RemotingDestination
public class EndpointStatusBRSImpl implements EndpointStatusService
{
	
	@Autowired
	protected EndpointStatusDAO endpointStatusDAO;
	
	@Autowired
	protected EndpointDAO endpointDAO;
	
	@Autowired
	protected MyDLPUIThriftService thriftService;

	@Override
	public List<EndpointStatus> getEndpointStatuses(String searchString, Integer offset,
			Integer limit) {
		return endpointStatusDAO.getEndpointStatuses(searchString, offset, limit);
	}

	@Override
	public Long getEndpointStatusCount(String searchString) {
		return endpointStatusDAO.getEndpointStatusCount(searchString);
	}

	@Override
	public Long getEndpointOnlineCount(String searchString) {
		return endpointStatusDAO.getEndpointOnlineCount(searchString);
	}

	@Override
	public Long getEndpointOfflineCount(String searchString) {
		return endpointStatusDAO.getEndpointOfflineCount(searchString);
	}

	@Override
	public Long getEndpointNotUpToDateCount(String searchString) {
		return endpointStatusDAO.getEndpointNotUpToDateCount(searchString);
	}

	@Override
	public Boolean truncateEndpointStatus() {
		return endpointStatusDAO.truncateEndpointStatus();
	}
	
}