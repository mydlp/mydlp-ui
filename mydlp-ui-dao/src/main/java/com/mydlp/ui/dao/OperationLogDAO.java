package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.OperationLog;


public interface OperationLogDAO {

	public List<OperationLog> getOperationLogs(List<List<Object>> criteriaList, Integer offset, Integer limit);
	
	public Long getOperationLogCount(List<List<Object>> criteriaList);
	
}
