package com.mydlp.ui.remoting.blazeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("discoveryBRS")
@RemotingDestination
public class DiscoveryBRSImpl implements DiscoveryService
{
	
	@Autowired
	@Qualifier("thriftService")
	protected MyDLPUIThriftService thriftService;

	@Override
	public void startDiscoveryOnDemand(Integer ruleId) {
		
		thriftService.startDiscoveryOnDemand(ruleId);
	}

	@Override
	public void stopDiscoveryOnDemand(Integer ruleId) {
		thriftService.stopDiscoveryOnDemand(ruleId);
	}

	@Override
	public void pauseDiscoveryOnDemand(Integer ruleId) {
		thriftService.pauseDiscoveryOnDemand(ruleId);
	}
	

}