package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.IncidentLog;
import com.mydlp.ui.domain.IncidentLogFile;
import com.mydlp.ui.domain.IncidentLogFileContent;


public interface IncidentLogDAO {

	public List<IncidentLog> getIncidents();
	
	public IncidentLogFileContent getIncidentContent(Integer id);
	
	public List<IncidentLog> getIncidents(List<List<Object>> criteriaList, Integer offset, Integer limit);
	
	public Long getIncidentCount(List<List<Object>> criteriaList);
	
	public IncidentLogFile geIncidentLogFile(Integer id);
	
	public List<String> getFilenamesForContent(Integer id);
	
}
