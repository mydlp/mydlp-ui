package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.domain.ADDomainItem;
import com.mydlp.ui.domain.AbstractEntity;

public interface ADDomainDAO {
	
	public ADDomain saveDomain(ADDomain domain);
	
	public ADDomainItem saveDomainItem(ADDomainItem domainItem);
	
	public void remove(AbstractEntity domainObj);
	
	public ADDomainItem findByDistinguishedName(String distinguishedName);
	
	public List<ADDomain> getADDomains();
}
