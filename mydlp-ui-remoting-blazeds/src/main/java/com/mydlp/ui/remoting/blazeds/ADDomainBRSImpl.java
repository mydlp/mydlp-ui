package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.ADDomainDAO;
import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.domain.ADDomainRoot;
import com.mydlp.ui.service.ADEnumService;

@Service("adDomainBRS")
@RemotingDestination(serviceAdapter="dpHibernateRemotingAdapter")
public class ADDomainBRSImpl implements ADDomainService
{
	
	@Autowired
	protected ADDomainDAO adDomainDAO;
	
	@Autowired
	protected ADEnumService adEnumService;

	@Override
	public void schedule(Integer domainId) {
		adEnumService.enumerate(domainId);
	}

	@Override
	public ADDomain save(ADDomain domain) {
		if (domain == null) return null;
		
		if (domain.getId() != null) {
			if (domain.getRoot() != null){
				ADDomainRoot persistentRoot = adDomainDAO.getDomainRoot(domain.getId());
				domain.setRoot(persistentRoot);
			}
		}
		
		domain = (ADDomain) adDomainDAO.merge(domain);
		return domain;
	}

	@Override
	public List<ADDomain> getADDomains() {
		return adDomainDAO.getADDomains();
	}

	@Override
	public String testConnection(ADDomain adDomain) {
		return adEnumService.testConnection(adDomain);
	}
	

}