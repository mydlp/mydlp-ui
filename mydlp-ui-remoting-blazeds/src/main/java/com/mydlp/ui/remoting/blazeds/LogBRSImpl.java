package com.mydlp.ui.remoting.blazeds;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.IncidentLogDAO;
import com.mydlp.ui.dao.OperationLogDAO;
import com.mydlp.ui.domain.AuthUser;
import com.mydlp.ui.domain.IncidentLog;
import com.mydlp.ui.domain.IncidentLogFileContent;
import com.mydlp.ui.domain.OperationLog;
import com.mydlp.ui.service.LogExportService;
import com.mydlp.ui.service.SolrService;
import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("logBRS")
@RemotingDestination
public class LogBRSImpl implements LogService
{
	@Autowired
	protected IncidentLogDAO incidentLogDAO;
	
	@Autowired
	protected OperationLogDAO operationLogDAO;
	
	@Autowired 
	protected SolrService solrService;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected MyDLPUIThriftService myDLPUIThriftService;
	
	@Autowired
	protected LogExportService logExportService;

	@Override
	public List<IncidentLog> getLogs(List<List<Object>> criteriaList, Integer offset, Integer count) {
		AuthUser currentUser = userService.getCurrentUser();
		return incidentLogDAO.getIncidents(currentUser, criteriaList, offset, count);
	}

	@Override
	public Long getLogCount(List<List<Object>> criteriaList) {
		AuthUser currentUser = userService.getCurrentUser();
		return incidentLogDAO.getIncidentCount(currentUser, criteriaList);
	}

	protected List<IncidentLogFileContent> loadLogs(List<Integer> ids) {
		List<IncidentLogFileContent> returnList = new ArrayList<IncidentLogFileContent>(ids.size());
		for (Integer id : ids) {
			IncidentLogFileContent l = incidentLogDAO.getIncidentContent(id);
			returnList.add(l);
		}
		return returnList;
	}
	
	@Override
	public List<IncidentLogFileContent> getLogContents(String searchStr, Integer offset,
			Integer count) {
		return loadLogs(solrService.queryContent(searchStr, offset, count));
	}

	@Override
	public Long getLogContentCount(String searchStr) {
		return solrService.queryContentCount(searchStr);
	}

	@Override
	public List<String> getFilenamesForContent(Integer id) {
		return incidentLogDAO.getFilenamesForContent(id);
	}

	@Override
	public void requeueIncident(IncidentLog log) {
		myDLPUIThriftService.requeueIncident(log.getId());
	}

	@Override
	public String exportExcel(List<List<Object>> criteriaList) {
		AuthUser currentUser = userService.getCurrentUser();
		return logExportService.exportExcel(currentUser, criteriaList);
	}

	@Override
	public List<OperationLog> getDiscoveryOperationLogs(
			List<List<Object>> criteriaList, Integer offset, Integer count) {
		return operationLogDAO.getOperationLogs(criteriaList, offset, count);
	}

	@Override
	public Long getDiscoveryOperationLogCount(List<List<Object>> criteriaList) {
		return operationLogDAO.getOperationLogCount(criteriaList);
	}

}