package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.dphibernate.serialization.annotations.NeverSerialize;

@Entity
public class DocumentDatabase extends Argument {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3670016092569543643L;
	
	protected List<DocumentDatabaseFileEntry> fileEntries;
	protected List<DocumentDatabaseRDBMSEntry> rdbmsEntries;
	protected RDBMSInformationTarget rdbmsInformationTarget;
	protected List<DocumentDatabaseRemoteStorage> documentDatabaseRemoteStorages;

	@OneToMany(cascade={CascadeType.ALL})
	public List<DocumentDatabaseFileEntry> getFileEntries() {
		return fileEntries;
	}

	public void setFileEntries(List<DocumentDatabaseFileEntry> fileEntries) {
		this.fileEntries = fileEntries;
	}
	
	@NeverSerialize
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	public List<DocumentDatabaseRDBMSEntry> getRdbmsEntries() {
		return rdbmsEntries;
	}

	public void setRdbmsEntries(List<DocumentDatabaseRDBMSEntry> rdbmsEntries) {
		this.rdbmsEntries = rdbmsEntries;
	}

	@OneToOne(cascade={CascadeType.ALL})
	public RDBMSInformationTarget getRdbmsInformationTarget() {
		return rdbmsInformationTarget;
	}

	public void setRdbmsInformationTarget(
			RDBMSInformationTarget rdbmsInformationTarget) {
		this.rdbmsInformationTarget = rdbmsInformationTarget;
	}

	@ManyToMany(cascade={CascadeType.ALL})
	public List<DocumentDatabaseRemoteStorage> getDocumentDatabaseRemoteStorages() {
		return documentDatabaseRemoteStorages;
	}

	public void setDocumentDatabaseRemoteStorages(
			List<DocumentDatabaseRemoteStorage> documentDatabaseRemoteStorages) {
		this.documentDatabaseRemoteStorages = documentDatabaseRemoteStorages;
	}

	
	
}
