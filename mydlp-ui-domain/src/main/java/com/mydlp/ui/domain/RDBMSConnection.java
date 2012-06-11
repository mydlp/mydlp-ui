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
	protected String loginUsername;
	protected String loginPassword;
	
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
	public String getLoginUsername() {
		return loginUsername;
	}
	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}
	@Column(nullable=false)
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	
}
