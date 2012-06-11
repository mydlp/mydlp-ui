package com.mydlp.ui.remoting.blazeds;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.RDBMSConnection;
import com.mydlp.ui.domain.RDBMSInformationTarget;

@Secured(AuthSecurityRole.ROLE_ADMIN)
public interface RDBMSConnectionService {

	public List<RDBMSConnection> getRDBMSConnections();
	
	public AbstractEntity save(AbstractEntity e);
	
	public void remove(AbstractEntity e);

	public String testConnection(RDBMSConnection rdbmsConnection);
	
	public List<Map<String, String>> getTableNames(RDBMSConnection rdbmsConnection, String tableSearchString);
	
	public List<Map<String, String>> getColumnNames(RDBMSConnection rdbmsConnection, String catalogName,
			String schemaName, String tableName, String columnSearchString);
	
	public List<String> getSampleValues(RDBMSInformationTarget rdbmsInformationTarget);
}