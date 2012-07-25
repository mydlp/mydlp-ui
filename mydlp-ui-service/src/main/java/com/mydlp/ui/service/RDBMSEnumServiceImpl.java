package com.mydlp.ui.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.mydlp.ui.dao.DocumentDatabaseDAO;
import com.mydlp.ui.dao.GenericDAO;
import com.mydlp.ui.dao.RDBMSConnectionDAO;
import com.mydlp.ui.dao.RegularExpressionGroupDAO;
import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.RDBMSConnection;
import com.mydlp.ui.domain.RDBMSEnumeratedValue;
import com.mydlp.ui.domain.RDBMSInformationTarget;
import com.mydlp.ui.domain.RegularExpressionGroup;
import com.mydlp.ui.service.EnumMasterService.EnumJob;
import com.mydlp.ui.service.rdbms.dialect.AbstactDialect;
import com.mydlp.ui.service.rdbms.dialect.MySQLDialectImpl;
import com.mydlp.ui.service.rdbms.proxy.RDBMSObjectEnumProxy;

@Service("rdbmsEnumService")
public class RDBMSEnumServiceImpl implements RDBMSEnumService {

	private static Logger logger = LoggerFactory
			.getLogger(RDBMSEnumServiceImpl.class);

	@Autowired
	@Qualifier("policyTransactionTemplate")
	protected TransactionTemplate transactionTemplate;
	
	@Autowired
    protected ApplicationContext context;
	
	@Autowired
	protected RDBMSConnectionDAO rdbmsConnectionDAO;
	
	@Autowired
	protected EnumMasterService enumMasterService;
	
	@Autowired
	protected DocumentDatabaseDAO documentDatabaseDAO;
	
	@Autowired
	protected RegularExpressionGroupDAO regularExpressionGroupDAO;
	
	@Autowired
	protected GenericDAO genericDAO;
	
	public class RDBMSEnumJob extends EnumJob {

		protected RDBMSEnumService rdbmsEnumService;
		
		protected Integer rdbmsInformationTargetId;

		protected AbstractEntity entity;
		
		@Override
		public void enumerateNow() {
			rdbmsEnumService.enumerate(rdbmsInformationTargetId, entity);
		}

		@Override
		public String toString() {
			return "RDBMSEnumService_" + rdbmsInformationTargetId + "_" + entity.getClass().getCanonicalName() + "_" + entity.getId();
		}
		
	}
	
	@Scheduled(cron="0 0 4 * * ?")
	public void dailySchedule() {
		dailyScheduleDocumentDatabases();
		dailyScheduleRegularExpressionGroups();
	}
	
	protected void dailyScheduleDocumentDatabases() {
		for (DocumentDatabase dd: documentDatabaseDAO.getDocumentDatabasesWithRDBMS())
			if (dd.getRdbmsInformationTarget() != null && dd.getRdbmsInformationTarget().getId() != null)
				schedule(dd.getRdbmsInformationTarget().getId(), dd);
	}
	
	protected void dailyScheduleRegularExpressionGroups() {
		for (RegularExpressionGroup reg: regularExpressionGroupDAO.getRegularExpressionGroupsWithRDBMS())
			if (reg.getRdbmsInformationTarget() != null && reg.getRdbmsInformationTarget().getId() != null)
				schedule(reg.getRdbmsInformationTarget().getId(), reg);
	}
	
	public void schedule(Integer enumRdbmsInformationTargetId, AbstractEntity enumEntity) {
		RDBMSEnumJob rdbmsEnumJob = new RDBMSEnumJob();
		rdbmsEnumJob.rdbmsEnumService = this;
		rdbmsEnumJob.rdbmsInformationTargetId = enumRdbmsInformationTargetId;
		rdbmsEnumJob.entity = enumEntity;
		enumMasterService.schedule(rdbmsEnumJob);
	}
	
	public void enumerate(final Integer rdbmsInformationTargetId, final AbstractEntity entity) {
		enumerateFun(rdbmsInformationTargetId, entity);
	}
	
	protected String getIdentifier(RDBMSInformationTarget rdbmsInformationTarget, Connection connection) 
			throws SQLException {
		ResultSet rs = null;
		String identifier = null;
		try {
			rs = connection.getMetaData().getPrimaryKeys(rdbmsInformationTarget.getCatalogName(),
					rdbmsInformationTarget.getSchemaName(), 
					rdbmsInformationTarget.getTableName());
			if (rs.next()) 
			{
				identifier = rs.getString("COLUMN_NAME");
				if (rs.next()) // currently we do not support more than one pk; 
					return null;
			}
			return identifier;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null && ! rs.isClosed())
				rs.close();
		}
		
	}
	
	public void enumerateFun(final Integer rdbmsInformationTargetId, final AbstractEntity entity) {
		final RDBMSObjectEnumProxy enumProxy = getEnumProxy(entity);
		if (enumProxy == null) return ;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			final RDBMSInformationTarget rdbmsInformationTarget = 
					transactionTemplate.execute(new TransactionCallback<RDBMSInformationTarget>() {
						@Override
						public RDBMSInformationTarget doInTransaction(TransactionStatus arg0) {
							genericDAO.merge(entity);
							rdbmsConnectionDAO.startProcess(rdbmsInformationTargetId);
							return rdbmsConnectionDAO.getInformationTargetById(rdbmsInformationTargetId);
						}
					});
			
			connection = getSQLConnection(rdbmsInformationTarget.getRdbmsConnection());
			final String identifier = getIdentifier(rdbmsInformationTarget, connection);
			Boolean incrementalEnum = (identifier != null);
			if (!incrementalEnum) {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus arg0) {
						rdbmsConnectionDAO.deleteValues(rdbmsInformationTarget);
					}
				});
				enumProxy.truncate(rdbmsInformationTarget, entity);
			}
			statement = connection.createStatement();
			rs = getValues(rdbmsInformationTarget, statement, identifier);
			while (rs.next ()) {
				Object obj = rs.getObject(1);
				String stringValue = obj.toString();
				
				Boolean isValid = enumProxy.isValid(rdbmsInformationTarget, entity, stringValue);
				if (!isValid)
					continue;
				final int stringHashCode = stringValue.hashCode();
				
				Boolean valueIsAlreadyStored = 
						transactionTemplate.execute(new TransactionCallback<Boolean>() {
							@Override
							public Boolean doInTransaction(TransactionStatus arg0) {
								return rdbmsConnectionDAO.hasValue(rdbmsInformationTarget, stringHashCode);
							}
						});
				
				if (!incrementalEnum && valueIsAlreadyStored) continue;
				
				RDBMSEnumeratedValue ev = null;
				String idValue = null;
				if (incrementalEnum) {
					Object idObj = rs.getObject(2);
					idValue = idObj.toString();
					final String idValueF = idValue;
					ev = transactionTemplate.execute(new TransactionCallback<RDBMSEnumeratedValue>() {
						@Override
						public RDBMSEnumeratedValue doInTransaction(
								TransactionStatus arg0) {
							return rdbmsConnectionDAO.getValue(rdbmsInformationTarget, idValueF);
						}
					});
					
					if (ev == null && valueIsAlreadyStored) continue;
					if (ev != null && ev.getHashCode() == stringHashCode) continue; // we already have this value
					valueIsAlreadyStored = transactionTemplate.execute(new TransactionCallback<Boolean>() {
						@Override
						public Boolean doInTransaction(TransactionStatus arg0) {
							return rdbmsConnectionDAO.hasOtherValue(
									rdbmsInformationTarget, stringHashCode, idValueF);
						}
					});
				}
				
				if (valueIsAlreadyStored && ev != null && ev.getId() != null)
				{
					final RDBMSEnumeratedValue evF = ev;
					transactionTemplate.execute(new TransactionCallbackWithoutResult() {
						@Override
						protected void doInTransactionWithoutResult(TransactionStatus arg0) {
							rdbmsConnectionDAO.remove(evF);
						}
					});
					enumProxy.delete(rdbmsInformationTarget, entity, idValue);
					continue;
				}
				
				if (ev == null) {
					ev = new RDBMSEnumeratedValue();
					ev.setInformationTarget(rdbmsInformationTarget);
					ev.setOriginalId(idValue);
				}
				
				ev.setHashCode(stringHashCode);
				if (enumProxy.shouldStoreValue())
					ev.setString(stringValue);
				{
					final RDBMSEnumeratedValue evF = ev;
					transactionTemplate.execute(new TransactionCallbackWithoutResult() {
						@Override
						protected void doInTransactionWithoutResult(TransactionStatus arg0) {
							rdbmsConnectionDAO.save(evF);
						}
					});
				}
				enumProxy.save(rdbmsInformationTarget, entity, idValue, stringValue);
			}
		} catch (ClassNotFoundException e) {
			logger.error("Probably JDBC driver is not found", e);
		} catch (SQLException e) {
			logger.error("An error occured during establishing connection and getting results of query", e);
		} finally {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					rdbmsConnectionDAO.finalizeProcess(rdbmsInformationTargetId);
				}
			});			
			try {
				if (connection != null && ! connection.isClosed() )
					connection.close();
				if (statement != null && ! statement.isClosed() )
					statement.close();
				if (rs != null && ! rs.isClosed() )
					rs.close();
			} catch (SQLException e) {
				logger.error("Error occurred when closing sql objects", e);
			}
		}
		
	}
	
	protected RDBMSObjectEnumProxy getEnumProxy(AbstractEntity entity) {
		if (entity instanceof RegularExpressionGroup) {
			return (RDBMSObjectEnumProxy) context.getBean("regularExpressionGroupEnumProxy");
		} else if (entity instanceof DocumentDatabase) {
			return (RDBMSObjectEnumProxy) context.getBean("documentDatabaseEnumProxy");
		}
		
		return null;
	}

	protected AbstactDialect getDialect(
			RDBMSInformationTarget rdbmsInformationTarget) {
		return getDialect(rdbmsInformationTarget.getRdbmsConnection());
	}

	protected AbstactDialect getDialect(RDBMSConnection rdbmsConnection) {
		if (rdbmsConnection.getDbType().equals(RDBMSConnection.DB_TYPE_MYSQL)) {
			return new MySQLDialectImpl();
		}
		return null;
	}

	protected Connection getSQLConnection(RDBMSConnection connectionObj)
			throws ClassNotFoundException, SQLException {
		AbstactDialect dialect = getDialect(connectionObj);
		if (dialect == null) return null;

		Class.forName(dialect.getDriverClassName());
		return DriverManager.getConnection(connectionObj.getJdbcUrl(),
				connectionObj.getLoginUsername(), connectionObj.getLoginPassword());
	}

	protected ResultSet getValues(
			RDBMSInformationTarget rdbmsInformationTarget,
			Statement statement, String identifier) throws SQLException {
		AbstactDialect dialect = getDialect(rdbmsInformationTarget);
		if (dialect == null) return null;

		String query = "SELECT " + 
				dialect.getSQLColumnNamePrefix() +
				rdbmsInformationTarget.getColumnName() + 
				dialect.getSQLColumnNameSuffix();
		
		if (identifier != null)
			query += "," + dialect.getSQLColumnNamePrefix() +
					identifier + 
					dialect.getSQLColumnNameSuffix(); 
			
		query += " FROM " +
				dialect.getSQLTableNamePrefix() +
				rdbmsInformationTarget.getTableName() +
				dialect.getSQLTableNameSuffix();
		
		statement.executeQuery(query);
		try {
			return statement.getResultSet();
		} catch (Exception e) {
			logger.error("Error occured when getting result set", e);
			return null;
		}
	}

	@Override
	public String testConnection(RDBMSConnection rdbmsConnection) {
		Connection connection = null;
		try {
			connection = getSQLConnection(rdbmsConnection);
			return "OK";
		} catch (Throwable e) {
			return e.toString();
		} finally {
			try {
				if (connection != null && ! connection.isClosed())
					connection.close();
			} catch (Exception e) {
				logger.error("Error occured when closing test connection", e);
			}
		}
	}

	@Override
	public List<Map<String, String>> getTableNames(RDBMSConnection rdbmsConnection,
			String tableSearchString) {
		List<Map<String, String>> returnList = new LinkedList<Map<String, String>>();
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = getSQLConnection(rdbmsConnection);
			rs = connection.getMetaData().getTables(null, null, "%" + tableSearchString + "%", null);
			while (rs.next ()) {
				Map<String, String> rowResults = new HashMap<String, String>();
				rowResults.put("schema", rs.getString("TABLE_SCHEM"));
				rowResults.put("catalog", rs.getString("TABLE_CAT"));
				rowResults.put("name", rs.getString("TABLE_NAME"));
				rowResults.put("type", rs.getString("TABLE_TYPE"));
				returnList.add(rowResults);
				if (returnList.size() == 10) break;
			}
		} catch (ClassNotFoundException e) {
			logger.error("Probably JDBC driver is not found", e);
		} catch (SQLException e) {
			logger.error("An error occured during establishing connection and getting table names", e);
		} finally {
			try {
				if (connection != null && ! connection.isClosed() )
					connection.close();
				if (rs != null && ! rs.isClosed() )
					rs.close();
			} catch (SQLException e) {
				logger.error("Error occurred when closing sql objects", e);
			}
		}
		return returnList;
	}

	@Override
	public List<Map<String, String>> getColumnNames(RDBMSConnection rdbmsConnection, String catalogName,
			String schemaName, String tableName, String columnSearchString) {
		List<Map<String, String>> returnList = new LinkedList<Map<String, String>>();
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = getSQLConnection(rdbmsConnection);
			rs = connection.getMetaData().getColumns(catalogName, schemaName, tableName, "%" + columnSearchString + "%");
			while (rs.next ()) {
				Map<String, String> rowResults = new HashMap<String, String>();
				rowResults.put("name", rs.getString("COLUMN_NAME"));
				rowResults.put("type", rs.getString("TYPE_NAME"));
				returnList.add(rowResults);
				if (returnList.size() == 10) break;
			}
		} catch (ClassNotFoundException e) {
			logger.error("Probably JDBC driver is not found", e);
		} catch (SQLException e) {
			logger.error("An error occured during establishing connection and getting column names", e);
		} finally {
			try {
				if (connection != null && ! connection.isClosed() )
					connection.close();
				if (rs != null && ! rs.isClosed() )
					rs.close();
			} catch (SQLException e) {
				logger.error("Error occurred when closing sql objects", e);
			}
		}
		return returnList;
	}

	@Override
	public List<String> getSampleValues(
			RDBMSInformationTarget rdbmsInformationTarget) {
		List<String> returnList = new LinkedList<String>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = getSQLConnection(rdbmsInformationTarget.getRdbmsConnection());
			statement = connection.createStatement();
			rs = getValues(rdbmsInformationTarget, statement, null);
			while (rs.next ()) {
				Object obj = rs.getObject(1);
				String stringValue = obj.toString();
				returnList.add(stringValue);
				if (returnList.size() == 10) break;
			}
		} catch (ClassNotFoundException e) {
			logger.error("Probably JDBC driver is not found", e);
		} catch (SQLException e) {
			logger.error("An error occured during establishing connection and getting sample values", e);
		} finally {
			try {
				if (connection != null && ! connection.isClosed() )
					connection.close();
				if (statement != null && ! statement.isClosed() )
					statement.close();
				if (rs != null && ! rs.isClosed() )
					rs.close();
			} catch (SQLException e) {
				logger.error("Error occurred when closing sql objects", e);
			}
		}
		return returnList;
	}
}
