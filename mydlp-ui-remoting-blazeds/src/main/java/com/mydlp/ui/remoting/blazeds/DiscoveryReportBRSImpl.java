package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.DiscoveryReportDAO;
import com.mydlp.ui.domain.DiscoveryReport;


@Service("discoveryReportBRS")
@RemotingDestination
public class DiscoveryReportBRSImpl implements DiscoveryReportService{

	@Autowired
	protected DiscoveryReportDAO discoveryReportDAO;
	
	@Override
	public List<DiscoveryReport> getDiscoveryReports(
			List<List<Object>> criteriaList, Integer offset, Integer count) {
		return discoveryReportDAO.getDiscoveryReports(criteriaList, offset, count);
	}

	@Override
	public Long getDiscoveryReportCount(List<List<Object>> criteriaList) {
		return discoveryReportDAO.getDiscoveryReportCount(criteriaList);
	}

	@Override
	public DiscoveryReport save(DiscoveryReport discoveryReport) {
		return discoveryReportDAO.save(discoveryReport);
	}

	@Override
	public void remove(DiscoveryReport discoveryReport) {
		discoveryReportDAO.remove(discoveryReport);
	}

	
}
