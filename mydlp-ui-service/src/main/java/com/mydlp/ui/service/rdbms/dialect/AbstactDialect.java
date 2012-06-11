package com.mydlp.ui.service.rdbms.dialect;


public abstract class AbstactDialect {
	
	public abstract String getDriverClassName();
	
	public String getSQLTableNamePrefix() { return ""; }
	
	public String getSQLTableNameSuffix() { return ""; }
	
	public String getSQLColumnNamePrefix() { return ""; }
	
	public String getSQLColumnNameSuffix() { return ""; }
	
	public String getValuePrefix() { return "\""; }
	
	public String getValueSuffix() { return "\""; }
}
