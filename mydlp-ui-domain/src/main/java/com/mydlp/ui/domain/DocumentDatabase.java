package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class DocumentDatabase extends MatcherParam {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3670016092569543643L;
	
	protected List<DocumentDatabaseFileEntry> fileEntries;

	@OneToMany(cascade={CascadeType.ALL})
	public List<DocumentDatabaseFileEntry> getFileEntries() {
		return fileEntries;
	}

	public void setFileEntries(List<DocumentDatabaseFileEntry> fileEntries) {
		this.fileEntries = fileEntries;
	}

}
