package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.EndpointStatusDAO;
import com.mydlp.ui.domain.EndpointStatus;

@Service("endpointStatusBRS")
@RemotingDestination
public class EndpointStatusBRSImpl implements EndpointStatusService
{
	
	@Autowired
	protected EndpointStatusDAO endpointStatusDAO;

	@Override
	public List<EndpointStatus> getEndpointStatuses(Integer offset,
			Integer limit) {
		return endpointStatusDAO.getEndpointStatuses(offset, limit);
	}

	@Override
	public Long getEndpointStatusCount() {
		return endpointStatusDAO.getEndpointStatusCount();
	}
	

}