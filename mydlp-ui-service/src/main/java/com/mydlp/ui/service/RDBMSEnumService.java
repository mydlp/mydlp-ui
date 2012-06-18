package com.mydlp.ui.service;

import java.util.List;
import java.util.Map;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.RDBMSConnection;
import com.mydlp.ui.domain.RDBMSInformationTarget;


public interface RDBMSEnumService {
	
	public void schedule(Integer rdbmsInformationTargetId, AbstractEntity entity);

	public void enumerate(Integer rdbmsInformationTargetId, AbstractEntity entity);
	
	public String testConnection(RDBMSConnection rdbmsConnection);
	
	public List<Map<String, String>> getTableNames(RDBMSConnection rdbmsConnection, String tableSearchString);
	
	public List<Map<String, String>> getColumnNames(RDBMSConnection rdbmsConnection, String catalogName,
			String schemaName, String tableName, String columnSearchString);
	
	public List<String> getSampleValues(RDBMSInformationTarget rdbmsInformationTarget);
	
}
