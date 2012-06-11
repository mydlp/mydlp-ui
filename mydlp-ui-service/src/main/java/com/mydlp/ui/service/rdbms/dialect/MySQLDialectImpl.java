package com.mydlp.ui.service.rdbms.dialect;

public class MySQLDialectImpl extends AbstactDialect {

	@Override
	public String getDriverClassName() {
		return "com.mysql.jdbc.Driver";
	}
	
	public String getSQLTableNamePrefix() { return "`"; }
	
	public String getSQLTableNameSuffix() { return "`"; }
	
	public String getSQLColumnNamePrefix() { return "`"; }
	
	public String getSQLColumnNameSuffix() { return "`"; }
}
