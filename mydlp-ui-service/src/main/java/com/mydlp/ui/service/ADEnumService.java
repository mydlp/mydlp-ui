package com.mydlp.ui.service;

import com.mydlp.ui.domain.ADDomain;


public interface ADEnumService {

	public void schedule(Integer domainId);
	
	public void enumerate(Integer domainId);
	
	public String testConnection(ADDomain adDomain);
	
}
