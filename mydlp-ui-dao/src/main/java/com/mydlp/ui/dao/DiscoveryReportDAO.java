package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.DiscoveryReport;


public interface DiscoveryReportDAO {

	public List<DiscoveryReport> getDiscoveryReports(List<List<Object>> criteriaList, Integer offset, Integer limit);
	
	public Long getDiscoveryReportCount(List<List<Object>> criteriaList);
	
	public DiscoveryReport save(DiscoveryReport d);
	
	public void remove(DiscoveryReport d);
	
}
