package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.IncidentLogDAO;
import com.mydlp.ui.domain.IncidentLog;

@Service("logBRS")
@RemotingDestination
public class LogBRSImpl implements LogService
{
	@Autowired
	protected IncidentLogDAO incidentLogDAO;

	@Override
	public List<IncidentLog> getLogs() {
		return incidentLogDAO.getIncidents();
	}

	@Override
	public List<IncidentLog> getLogs(List<List<Object>> criteriaList, Integer offset, Integer count) {
		return incidentLogDAO.getIncidents(criteriaList, offset, count);
	}

	@Override
	public Long getLogCount(List<List<Object>> criteriaList) {
		return incidentLogDAO.getIncidentCount(criteriaList);
	}

}