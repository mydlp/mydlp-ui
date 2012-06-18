package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RDBMSInformationTarget extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4581040878897738334L;
	
	protected RDBMSConnection rdbmsConnection;
	protected String catalogName;
	protected String schemaName;
	protected String tableName;
	protected String columnName;
	protected Boolean currentlyEnumerating;

	@ManyToOne
	@JoinColumn(nullable=false)
	public RDBMSConnection getRdbmsConnection() {
		return rdbmsConnection;
	}
	public void setRdbmsConnection(RDBMSConnection rdbmsConnection) {
		this.rdbmsConnection = rdbmsConnection;
	}
	@Column(nullable=false)
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Column(nullable=false)
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public Boolean getCurrentlyEnumerating() {
		return currentlyEnumerating;
	}
	public void setCurrentlyEnumerating(Boolean currentlyEnumerating) {
		this.currentlyEnumerating = currentlyEnumerating;
	}
}
