package com.mydlp.ui.remoting.blazeds;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.RDBMSConnectionDAO;
import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.RDBMSConnection;
import com.mydlp.ui.domain.RDBMSInformationTarget;
import com.mydlp.ui.service.RDBMSEnumService;


@Service("rdbmsConnectionBRS")
@RemotingDestination
public class RDBMSConnectionBRSImpl implements RDBMSConnectionService {

	@Autowired
	protected RDBMSConnectionDAO rdbmsConnectionDAO;
	
	@Autowired
	protected RDBMSEnumService rdbmsEnumService;
	
	@Override
	public List<RDBMSConnection> getRDBMSConnections() {
		return rdbmsConnectionDAO.getRDBMSConnections();
	}

	@Override
	public AbstractEntity save(AbstractEntity e) {
		return rdbmsConnectionDAO.save(e);
	}

	@Override
	public void remove(AbstractEntity e) {
		rdbmsConnectionDAO.remove(e);
	}

	@Override
	public String testConnection(RDBMSConnection rdbmsConnection) {
		return rdbmsEnumService.testConnection(rdbmsConnection);
	}

	@Override
	public List<Map<String, String>> getTableNames(
			RDBMSConnection rdbmsConnection, String tableSearchString) {
		return rdbmsEnumService.getTableNames(rdbmsConnection, tableSearchString);
	}

	@Override
	public List<Map<String, String>> getColumnNames(
			RDBMSConnection rdbmsConnection, String catalogName,
			String schemaName, String tableName, String columnSearchString) {
		return rdbmsEnumService.getColumnNames(rdbmsConnection, catalogName, schemaName, tableName, columnSearchString);
	}

	@Override
	public List<String> getSampleValues(
			RDBMSInformationTarget rdbmsInformationTarget) {
		return rdbmsEnumService.getSampleValues(rdbmsInformationTarget);
	}

	
}
