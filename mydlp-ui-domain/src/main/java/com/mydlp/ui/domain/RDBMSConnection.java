package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RDBMSConnection extends AbstractNamedEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7390299366840383434L;
	
	public static final String DB_TYPE_MYSQL="mysql";
	public static final String DB_TYPE_POSTGRESQL="postgresql";
	public static final String DB_TYPE_ORACLE="oracle";
	public static final String DB_TYPE_DB2="db2";
	public static final String DB_TYPE_DB2_AS400="db2-as400";
	
	protected String dbType;
	protected String jdbcUrl;
	protected String username;
	protected String password;
	
	@Column(nullable=false)
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	@Column(nullable=false)
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	@Column(nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
