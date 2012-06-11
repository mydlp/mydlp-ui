package com.mydlp.ui.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.mydlp.ui.dao.RDBMSConnectionDAO;
import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.RDBMSConnection;
import com.mydlp.ui.domain.RDBMSEnumeratedValue;
import com.mydlp.ui.domain.RDBMSInformationTarget;
import com.mydlp.ui.domain.RegularExpressionGroup;
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

	@Async
	public void enumerate(final RDBMSInformationTarget rdbmsInformationTarget, final AbstractEntity entity) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				enumerateFun(rdbmsInformationTarget, entity);
			}
		});
	}
	
	protected String getIdentifier(RDBMSInformationTarget rdbmsInformationTarget, Connection connection) 
			throws SQLException {
		ResultSet rs = null;
		String identifier = null;
		try {
			rs = connection.getMetaData().getPrimaryKeys(rdbmsInformationTarget.getCategoryName(),
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
	
	public void enumerateFun(RDBMSInformationTarget rdbmsInformationTarget, AbstractEntity entity) {
		RDBMSObjectEnumProxy enumProxy = getEnumProxy(entity);
		if (enumProxy == null) return ;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = getSQLConnection(rdbmsInformationTarget.getRdbmsConnection());
			String identifier = getIdentifier(rdbmsInformationTarget, connection);
			Boolean incrementalEnum = (identifier != null);
			System.out.println("incrementalEnum: " + incrementalEnum);
			if (!incrementalEnum)
				rdbmsConnectionDAO.deleteValues(rdbmsInformationTarget);
			statement = connection.createStatement();
			rs = getValues(rdbmsInformationTarget, statement, identifier);
			while (rs.next ()) {
				Object obj = rs.getObject(1);
				String stringValue = obj.toString();
				
				Boolean isValid = enumProxy.handle(rdbmsInformationTarget, entity, stringValue);
				if (!isValid)
					continue;
				int stringHashCode = stringValue.hashCode();
				
				Boolean valueIsAlreadyStored = rdbmsConnectionDAO.hasValue(rdbmsInformationTarget, stringHashCode);
				
				if (!incrementalEnum && valueIsAlreadyStored) continue;
				
				RDBMSEnumeratedValue ev = null;
				String idValue = null;
				if (incrementalEnum) {
					Object idObj = rs.getObject(2);
					idValue = idObj.toString();
					ev = rdbmsConnectionDAO.getValue(rdbmsInformationTarget, idValue);
					System.out.println("if exist check before");
					if (ev != null && ev.getHashCode() == stringHashCode) continue; // we already have this value
					System.out.println("if exist check after");
					valueIsAlreadyStored = rdbmsConnectionDAO.hasOtherValue(
							rdbmsInformationTarget, stringHashCode, idValue);
				}
				
				if (valueIsAlreadyStored && ev.getId() != null)
				{
					rdbmsConnectionDAO.remove(ev);
					continue;
				}
				
				if (ev == null) {
					ev = new RDBMSEnumeratedValue();
					ev.setInformationTarget(rdbmsInformationTarget);
					ev.setOriginalId(idValue);
					System.out.println("new object");
				}
				
				ev.setHashCode(stringHashCode);
				if (enumProxy.shouldStoreValue())
					ev.setString(stringValue);
				System.out.println("object save");
				rdbmsConnectionDAO.save(ev);
			}
		} catch (ClassNotFoundException e) {
			logger.error("Probably JDBC driver is not found", e);
		} catch (SQLException e) {
			logger.error("An error occured during establishing connection and getting results of query", e);
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
		
	}
	
	protected RDBMSObjectEnumProxy getEnumProxy(AbstractEntity entity) {
		if (entity instanceof RegularExpressionGroup) {
			return (RDBMSObjectEnumProxy) context.getBean("regularExpressionGroupEnumProxy");
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
				connectionObj.getUsername(), connectionObj.getPassword());
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
}
