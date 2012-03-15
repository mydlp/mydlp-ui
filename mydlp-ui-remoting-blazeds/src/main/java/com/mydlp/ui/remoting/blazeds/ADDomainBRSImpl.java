package com.mydlp.ui.remoting.blazeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.ADDomainDAO;
import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.service.ADEnumService;

@Service("adDomainBRS")
@RemotingDestination
public class ADDomainBRSImpl implements ADDomainService
{
	
	@Autowired
	protected ADDomainDAO adDomainDAO;
	
	@Autowired
	protected ADEnumService adEnumService;

	@Override
	public void enumerate(ADDomain domain) {
		adEnumService.enumerate(domain);
	}

	@Override
	public ADDomain save(ADDomain domain) {
		domain = adDomainDAO.saveDomain(domain);
		enumerate(domain);
		return domain;
	}
	

}