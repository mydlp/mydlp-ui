package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.IncidentLog;
import com.mydlp.ui.domain.IncidentLogFile;


public interface IncidentLogDAO {

	public List<IncidentLog> getIncidents();
	
	public List<IncidentLog> getIncidents(List<List<Object>> criteriaList, Integer offset, Integer limit);
	
	public Long getIncidentCount(List<List<Object>> criteriaList);
	
	public IncidentLogFile geIncidentLogFile(Integer id);
}
